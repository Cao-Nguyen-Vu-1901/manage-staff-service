package com.manage_staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage_staff.entity.SocialInsurance;

@Repository
public interface SocialInsuranceRepository extends JpaRepository<SocialInsurance, String> {}
