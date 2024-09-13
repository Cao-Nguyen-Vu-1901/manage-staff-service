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

import com.manage_staff.entity.LeaveDay;
import com.manage_staff.hibernate.HibernateUtil;

@Repository
public class LeaveDayDAO {

    private final SessionFactory sessionFactory = HibernateUtil.getFACTORY();

    public Page<LeaveDay> paging(
            String column, String value, int currentPage, int pageSize, String orderBy, String sortBy) {
        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<LeaveDay> query = builder.createQuery(LeaveDay.class);
            Root<LeaveDay> root = query.from(LeaveDay.class);

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
                        "DESC".equalsIgnoreCase(orderBy)
                                ? Sort.by(orderBy).descending()
                                : Sort.by(orderBy).ascending());

            } else {
                pageable = PageRequest.of(currentPage / pageSize, pageSize);
            }

            Query<LeaveDay> leaveDayQuery = session.createQuery(query);
            long totalItems = leaveDayQuery.getResultList().size();
            leaveDayQuery.setFirstResult(currentPage == 1 ? 0 : (currentPage - 1) * pageSize);
            leaveDayQuery.setMaxResults(pageSize);

            List<LeaveDay> leaveDays = leaveDayQuery.getResultList();

            return new PageImpl<>(leaveDays, pageable, totalItems);
        }
    }
}
