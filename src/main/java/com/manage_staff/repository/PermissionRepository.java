package com.manage_staff.repository;

import com.manage_staff.entity.Benefit;
import com.manage_staff.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
    List<Permission> findAllByNameLike(String name);
}
