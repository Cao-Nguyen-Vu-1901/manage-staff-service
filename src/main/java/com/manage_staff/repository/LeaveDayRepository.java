package com.manage_staff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage_staff.entity.LeaveDay;

@Repository
public interface LeaveDayRepository extends JpaRepository<LeaveDay, String> {
    List<LeaveDay> findAllByNameLike(String name);
}
