package com.manage_staff.dao;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import com.manage_staff.entity.RewardDiscipline;
import com.manage_staff.hibernate.HibernateUtil;

@Repository
public class RewardDisciplineDAO {
    private final SessionFactory sessionFactory = HibernateUtil.getFACTORY();

    public Page<RewardDiscipline> paging(
            String column, String value, int currentPage, int pageSize, String orderBy, String sortBy) {
        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RewardDiscipline> query = builder.createQuery(RewardDiscipline.class);
            Root<RewardDiscipline> root = query.from(RewardDiscipline.class);
            query.select(root);

            Predicate predicate = null;
            Pageable pageable = null;

            if (value != null) {
                value = value.trim();

                try {
                    LocalDate date = LocalDate.parse(value);
                    predicate = builder.equal(root.get(column).as(LocalDate.class), date);
                } catch (Exception e) {
                    predicate = builder.like(root.get(column).as(String.class), "%" + value + "%");
                }
                query.where(predicate);
            }
            if (orderBy != null) {
                if ("DESC".equalsIgnoreCase(sortBy)) {
                    query.orderBy(builder.desc(root.get(orderBy)));
                }
                pageable = PageRequest.of(
                        currentPage / pageSize,
                        pageSize,
                        "DESC".equalsIgnoreCase(sortBy)
                                ? Sort.by(orderBy).descending()
                                : Sort.by(orderBy).ascending());
            } else {
                pageable = PageRequest.of(currentPage / pageSize, pageSize);
            }

            Query<RewardDiscipline> rewardDisciplineQuery = session.createQuery(query);
            long totalItems = rewardDisciplineQuery.getResultList().size();

            rewardDisciplineQuery.setFirstResult(currentPage == 1 ? 0 : (currentPage - 1) * pageSize);
            rewardDisciplineQuery.setMaxResults(pageSize);

            List<RewardDiscipline> rewardDisciplines = rewardDisciplineQuery.getResultList();

            return new PageImpl<>(rewardDisciplines, pageable, totalItems);
        }
    }
}
