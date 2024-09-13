package com.manage_staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepository extends JpaRepository<com.manage_staff.entity.InvalidatedToken, String> {}
