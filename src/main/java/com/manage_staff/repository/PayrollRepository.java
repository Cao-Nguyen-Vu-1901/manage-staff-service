package com.manage_staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage_staff.entity.Payroll;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, String> {}
