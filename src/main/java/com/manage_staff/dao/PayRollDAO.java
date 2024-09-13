package com.manage_staff.dao;

import java.math.BigDecimal;
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

import com.manage_staff.entity.Payroll;
import com.manage_staff.hibernate.HibernateUtil;

@Repository
public class PayRollDAO {
    private final SessionFactory sessionFactory = HibernateUtil.getFACTORY();

    public Page<Payroll> paging(
            String column, String value, int currentPage, int pageSize, String orderBy, String sortBy) {
        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Payroll> query = builder.createQuery(Payroll.class);
            Root<Payroll> root = query.from(Payroll.class);
            query.select(root);

            Predicate predicate = null;
            Pageable pageable = null;

            if (value != null) {
                value = value.trim();
                try {
                    int num = Integer.parseInt(value);
                    predicate = builder.equal(root.get(column).as(Integer.class), num);
                } catch (Exception e) {
                    try {
                        BigDecimal decimal = new BigDecimal(value);
                        predicate = builder.equal(root.get(column).as(BigDecimal.class), decimal);
                    } catch (Exception ex) {
                        predicate = builder.like(root.get(column).as(String.class), "%" + value + "%");
                    }
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

            Query<Payroll> payrollQuery = session.createQuery(query);
            long totalItems = payrollQuery.getResultList().size();
            payrollQuery.setFirstResult(currentPage == 1 ? 0 : (currentPage - 1) * pageSize);
            payrollQuery.setMaxResults(pageSize);

            List<Payroll> payrolls = payrollQuery.getResultList();

            return new PageImpl<>(payrolls, pageable, totalItems);
        }
    }
}
