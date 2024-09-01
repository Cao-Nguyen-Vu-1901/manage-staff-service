package com.manage_staff.service.imp;

import com.manage_staff.dto.request.AuthenticationRequest;
import com.manage_staff.dto.request.IntrospectRequest;
import com.manage_staff.dto.request.LogoutRequest;
import com.manage_staff.dto.request.RefreshRequest;
import com.manage_staff.dto.response.AuthenticationResponse;
import com.manage_staff.dto.response.IntrospectResponse;
import com.manage_staff.entity.InvalidatedToken;
import com.manage_staff.entity.Staff;
import com.manage_staff.exception.AppException;
import com.manage_staff.exception.ErrorCode;
import com.manage_staff.repository.InvalidatedTokenRepository;
import com.manage_staff.repository.StaffRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    StaffRepository staffRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;

    @NonFinal
    private int loginFailed = 0;

    @NonFinal
    private int totalLoginFailed = 0;

    @NonFinal
    private Date loginFailedTime;

    @Value("${time.fail-number}")
    @NonFinal
    private int failedNumber;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;
        try {
            verifyToken(token, false);
        } catch (AppException e) {
            isValid = false;
        }
        return IntrospectResponse.builder().valid(isValid).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        if(totalLoginFailed == 2)
            staffRepository.updateStatusById(false,request.getUsername());

        if(loginFailed == failedNumber){

            Date now = Date.from(Instant.now()); // Thêm 25 giây vào date1

            long differenceInMillis = now.getTime() - loginFailedTime.getTime();

            long differenceInSeconds = differenceInMillis / 1000;
            log.info("Time {}", differenceInSeconds);

            if(differenceInSeconds >= 25){
                totalLoginFailed++;
                loginFailed = 0;
            }
        }

        if(loginFailed < failedNumber){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

            var staff = staffRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

            if(!staff.isStatus())
                throw new AppException(ErrorCode.ACCOUNT_IS_LOCKED);

            boolean authenticated = passwordEncoder.matches(request.getPassword(), staff.getPassword());
            if (!authenticated) {
                loginFailed++;
                log.info("login fail {}", loginFailed);
                if(loginFailed == 1) loginFailedTime = new Date();
                throw new AppException(ErrorCode.PASSWORD_IS_NOT_CORRECT);
            }

            var token = generateToken(staff);
            return AuthenticationResponse.builder().token(token).build();
        }else{
            throw new AppException(ErrorCode.LOGIN_WAIT);
        }
    }

    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getToken(), true);

        var jit = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();

        invalidatedTokenRepository.save(invalidatedToken);
        var username = signedJWT.getJWTClaimsSet().getSubject();
        var user = staffRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        var token = generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(request.getToken(), true);

            String jtt = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken =
                    InvalidatedToken.builder().id(jtt).expiryTime(expiryTime).build();
            invalidatedTokenRepository.save(invalidatedToken);
        } catch (AppException e) {
            log.info("Token already expired");
        }
    }

    private String generateToken(Staff staff) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(staff.getUsername())
                .issuer("nv.com")
                .issueTime(new Date())
                .expirationTime(
                        new Date(Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", builderScope(staff))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token ", e);
            throw new RuntimeException(e);
        }
    }

    private String builderScope(Staff staff) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (!CollectionUtils.isEmpty(staff.getRoles())) {
            staff.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions().forEach(
                            permission -> stringJoiner.add(permission.getName()));
            });
        }
        return stringJoiner.toString();
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        // Tại vì token mới được generate chỉ có thời gian hay expiryTime là 1h nhưng time refresh
        // lại là 2h nên phải lấy issue time + 2h để check
        Date experyDate = (isRefresh) ? new Date(signedJWT.getJWTClaimsSet().getIssueTime()
                .toInstant().plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);
        if (!verified && experyDate.after(new Date())) throw new AppException(ErrorCode.UNAUTHENTICATED);

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }
}
