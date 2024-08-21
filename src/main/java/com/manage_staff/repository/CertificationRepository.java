package com.manage_staff.repository;

import com.manage_staff.entity.Benefit;
import com.manage_staff.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, String> {
    List<Certification> findAllByNameLike(String name);
}
