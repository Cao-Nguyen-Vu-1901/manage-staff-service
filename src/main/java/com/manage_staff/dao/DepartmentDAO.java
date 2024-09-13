package com.manage_staff.dao;

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

import com.manage_staff.entity.Department;
import com.manage_staff.hibernate.HibernateUtil;

@Repository
public class DepartmentDAO {
    private final SessionFactory entityManager = HibernateUtil.getFACTORY();

    public Page<Department> paging(
            String column, String value, int currentPage, int pageSize, String orderBy, String sortBy) {
        try (Session session = entityManager.openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Department> query = builder.createQuery(Department.class);
            Root<Department> departmentRoot = query.from(Department.class);
            query.select(departmentRoot);
            Pageable pageable = null;
            if (value != null) {

                value = value.trim();
                Predicate predicate = builder.like(departmentRoot.get(column).as(String.class), "%" + value + "%");
                query.where(predicate);
            }
            if (orderBy != null) {
                if ("DESC".equalsIgnoreCase(sortBy)) {
                    query.orderBy(builder.desc(departmentRoot.get(orderBy)));
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

            Query<Department> departmentQuery = session.createQuery(query);
            long totalItems = departmentQuery.getResultList().size();
            departmentQuery.setFirstResult(currentPage == 1 ? 0 : (currentPage - 1) * pageSize);
            departmentQuery.setMaxResults(pageSize);
            List<Department> departments = departmentQuery.getResultList();

            return new PageImpl<>(departments, pageable, totalItems);
        }
    }
}
