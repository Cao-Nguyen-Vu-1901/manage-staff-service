package com.manage_staff.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage_staff.entity.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, String> {
    List<Position> findAllByNameLike(String name);

    Optional<Position> findByName(String name);
    boolean existsByName(String name);
}
