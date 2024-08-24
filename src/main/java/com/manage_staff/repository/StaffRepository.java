package com.manage_staff.repository;

import com.manage_staff.entity.Benefit;
import com.manage_staff.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {
    List<Staff> findAllByNameLike(String name);
    Staff findByUsername( String username);


}
