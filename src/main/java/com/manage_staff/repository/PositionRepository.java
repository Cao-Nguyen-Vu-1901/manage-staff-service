package com.manage_staff.repository;

import com.manage_staff.entity.Benefit;
import com.manage_staff.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, String> {
    List<Position> findAllByNameLike(String name);

}
