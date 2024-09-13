package com.manage_staff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage_staff.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> findAllByNameLike(String name);

    List<Role> findAllByName(String name);
}
