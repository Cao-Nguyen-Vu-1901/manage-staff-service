package com.manage_staff.repository;

import com.manage_staff.entity.Benefit;
import com.manage_staff.entity.RewardDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardDisciplineRepository extends JpaRepository<RewardDiscipline, String> {
    List<RewardDiscipline> findAllByNameLike(String name);
}
