package com.manage_staff.repository;

import com.manage_staff.entity.Benefit;
import com.manage_staff.entity.Staff;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {
    List<Staff> findAllByNameLike(String name);
    Staff findByUsername( String username);

    Page<Staff> findAllByNameLike(Pageable pageable ,String name);
    Page<Staff> findAllByDob(Pageable pageable, LocalDate dob);
    Page<Staff> findAllByGender(Pageable pageable, String gender);
    Page<Staff> findAllByEmailLike(Pageable pageable, String email);
    Page<Staff> findAllByPhoneNumberLike(Pageable pageable, String phoneNumber);
    Page<Staff> findAllByAddressLike(Pageable pageable, String address);
    Page<Staff> findAllByStatus(Pageable pageable, boolean status);
    Page<Staff> findAllByPromotionDateAfter(Pageable pageable, LocalDate date);
    Page<Staff> findAllByPromotionDate(Pageable pageable, LocalDate date);
    Page<Staff> findAllByCreateDate(Pageable pageable, LocalDate date);

//    @Query("select st from Staff st where :column = : value order by :orderBy DESC LIMIT :limitP offset :offsetP")
//    List<Staff> paging(@Param("column") String column,@Param("value,") String value,
//                       @Param("limitP") int limitP,@Param("offsetP") int offsetP,
//                       @Param("sortBy") String sortBy, @Param("orderBy") String orderBy);



}
