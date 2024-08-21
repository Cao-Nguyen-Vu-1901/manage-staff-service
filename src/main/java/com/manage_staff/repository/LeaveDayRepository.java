package com.manage_staff.repository;

import com.manage_staff.entity.LeaveDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveDayRepository extends JpaRepository<LeaveDay, String> {
    List<LeaveDay> findAllByNameLike(String name);
}
