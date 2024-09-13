package com.manage_staff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage_staff.entity.Certification;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, String> {
    List<Certification> findAllByNameLike(String name);
}
