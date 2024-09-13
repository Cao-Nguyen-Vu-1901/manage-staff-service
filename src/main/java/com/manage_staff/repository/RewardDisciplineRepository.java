package com.manage_staff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage_staff.entity.RewardDiscipline;

@Repository
public interface RewardDisciplineRepository extends JpaRepository<RewardDiscipline, String> {
    List<RewardDiscipline> findAllByNameLike(String name);
}
