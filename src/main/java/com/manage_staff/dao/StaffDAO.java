package com.manage_staff.dao;

import com.manage_staff.entity.Staff;
import com.manage_staff.hibernate.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StaffDAO {

    SessionFactory entityManager = HibernateUtil.getFACTORY();

    public Page<Staff> paging(String column, String value, int limitP, int offsetP, String sortBy, String orderBy) {
        try (Session session = entityManager.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Staff> query = builder.createQuery(Staff.class);
            Root<Staff> root = query.from(Staff.class);
            query.select(root);
            Predicate predicate  ;

            if(value!= null){
                try{
                    value = value.trim();
                    int search = Integer.parseInt(value);

                    predicate = builder.equal(root.get(column).as(Integer.class),  search);
                    if(search > 9)
                        predicate = builder.like(root.get(column).as(String.class), "%" + value + "%");
                }catch (Exception e){
                    try{
                        LocalDate date = LocalDate.parse(value);
                        predicate = builder.equal(root.get(column).as(LocalDate.class), date);
                    }catch (Exception ex){
                        predicate = builder.like(root.get(column).as(String.class), "%" + value + "%");

                    }
                }
                query.where(predicate);
                if (sortBy != null) {
                    if ("DESC".equalsIgnoreCase(orderBy)) {
                        query.orderBy(builder.desc(root.get(sortBy)));
                    } else {
                        query.orderBy(builder.asc(root.get(sortBy)));
                    }
                }
            }

            Query<Staff> q = session.createQuery(query);
            long totalRecords = q.getResultList().size();

            q.setFirstResult(offsetP == 1 ? 0 : (offsetP - 1) * limitP);
            q.setMaxResults(limitP);


            List<Staff> staff = q.getResultList(); // Lấy danh sách kết quả


            // Tạo đối tượng Page
            Pageable pageable = null;

            if(sortBy != null){
                pageable = PageRequest.of(offsetP / limitP, limitP,
                        "DESC".equalsIgnoreCase(orderBy) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
            }else {
                pageable = PageRequest.of(offsetP / limitP, limitP);
            }
            return new PageImpl<>(staff, pageable, totalRecords);
        }

    }
    public Long countByColumn(Session session, String columnName, String value) {
        String hql = "SELECT COUNT(s) FROM Staff s WHERE s." + columnName + " LIKE :value";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("value", value);
        return query.getSingleResult();
    }

}
