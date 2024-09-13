package com.manage_staff.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.manage_staff.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {
    List<Staff> findAllByNameLike(String name);

    Optional<Staff> findByUsername(String username);

    Page<Staff> findAllByNameLike(Pageable pageable, String name);

    Page<Staff> findAllByDob(Pageable pageable, LocalDate dob);

    Page<Staff> findAllByGender(Pageable pageable, String gender);

    Page<Staff> findAllByEmailLike(Pageable pageable, String email);

    Page<Staff> findAllByPhoneNumberLike(Pageable pageable, String phoneNumber);

    Page<Staff> findAllByAddressLike(Pageable pageable, String address);

    Page<Staff> findAllByStatus(Pageable pageable, boolean status);

    Page<Staff> findAllByPromotionDateAfter(Pageable pageable, LocalDate date);

    Page<Staff> findAllByPromotionDate(Pageable pageable, LocalDate date);

    Page<Staff> findAllByCreateDate(Pageable pageable, LocalDate date);

    @Query("SELECT st FROM Staff st WHERE st.name = :value ORDER BY st.id DESC")
    List<Staff> findByName(@Param("value") String value, Pageable pageable);
    //    @Query("SELECT COUNT(s) FROM Staff s WHERE s.:columnName   like :valuep")
    //    List<Staff> findByName(@Param("valuep") String valuep, @Param("columnName") String columnName, Pageable
    // pageable);

    @Transactional
    @Modifying
    @Query("update Staff set status = :status where username = :username")
    void updateStatusById(@Param("status") boolean status, @Param("username") String username);
}
