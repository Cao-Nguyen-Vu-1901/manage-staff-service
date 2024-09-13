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

import com.manage_staff.entity.Certification;
import com.manage_staff.hibernate.HibernateUtil;

@Repository
public class CertificationDAO {

    private final SessionFactory sessionFactory = HibernateUtil.getFACTORY();

    public Page<Certification> paging(
            String column, String value, int currentPage, int pageSize, String orderBy, String sortBy) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Certification> query = builder.createQuery(Certification.class);
            Root<Certification> root = query.from(Certification.class);
            query.select(root);

            Predicate predicate;
            Pageable pageable;
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
                                ? Sort.by(sortBy).descending()
                                : Sort.by(sortBy).ascending());
            } else {
                pageable = PageRequest.of(currentPage / pageSize, pageSize);
            }

            Query<Certification> certificationQuery = session.createQuery(query);
            long totalItems = certificationQuery.getResultList().size();
            certificationQuery.setFirstResult(currentPage == 1 ? 0 : (currentPage - 1) * pageSize);
            certificationQuery.setMaxResults(pageSize);

            List<Certification> certifications = certificationQuery.getResultList();

            return new PageImpl<>(certifications, pageable, totalItems);
        }
    }
}
