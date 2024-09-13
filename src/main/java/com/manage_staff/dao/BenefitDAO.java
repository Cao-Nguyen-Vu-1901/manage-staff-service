package com.manage_staff.dao;

import java.math.BigDecimal;
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
import org.springframework.stereotype.Service;

import com.manage_staff.entity.Benefit;
import com.manage_staff.hibernate.HibernateUtil;

@Service
public class BenefitDAO {
    SessionFactory entityManager = HibernateUtil.getFACTORY();

    public Page<Benefit> paging(
            String column, String value, int currentPage, int pageSize, String orderBy, String sortBy) {
        try (Session session = entityManager.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Benefit> query = builder.createQuery(Benefit.class);
            Root<Benefit> root = query.from(Benefit.class);
            query.select(root);

            Predicate predicate = null;
            if (value != null) {
                value = value.trim();
                try {
                    LocalDate date = LocalDate.parse(value);
                    predicate = builder.equal(root.get(column).as(LocalDate.class), date);
                } catch (Exception e) {
                    try {
                        BigDecimal money = new BigDecimal(value);
                        predicate = builder.equal(root.get(column).as(BigDecimal.class), money);
                    } catch (Exception ex) {
                        predicate = builder.like(root.get(column).as(String.class), "%" + value + "%");
                    }
                }
                query.where(predicate);
            }

            Pageable pageable = null;
            if (sortBy != null) {
                if ("DESC".equalsIgnoreCase(orderBy)) {
                    query.orderBy(builder.desc(root.get(sortBy)));
                }
                pageable = PageRequest.of(
                        currentPage / pageSize,
                        pageSize,
                        "DESC".equalsIgnoreCase(orderBy)
                                ? Sort.by(sortBy).descending()
                                : Sort.by(sortBy).ascending());
            } else {
                pageable = PageRequest.of(currentPage / pageSize, pageSize);
            }
            Query<Benefit> q = session.createQuery(query);
            long totalItems = q.getResultList().size();

            q.setFirstResult(currentPage == 1 ? 0 : (currentPage - 1) * pageSize);
            q.setMaxResults(pageSize);

            List<Benefit> benefits = q.getResultList();
            return new PageImpl<>(benefits, pageable, totalItems);
        }
    }
}
