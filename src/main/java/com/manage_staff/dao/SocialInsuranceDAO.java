package com.manage_staff.dao;

import com.manage_staff.entity.SocialInsurance;
import com.manage_staff.hibernate.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Repository
public class SocialInsuranceDAO {
    private final SessionFactory sessionFactory = HibernateUtil.getFACTORY();

    public Page<SocialInsurance> paging( String column, String value, int currentPage,
                                         int pageSize, String orderBy, String sortBy){
        try(Session session = sessionFactory.openSession()){

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<SocialInsurance> query = builder.createQuery(SocialInsurance.class);
            Root<SocialInsurance> root = query.from(SocialInsurance.class);
            query.select(root);

            Predicate predicate = null;
            Pageable pageable = null;
            if(value != null){
                value = value.trim();
                try{
                    LocalDate date = LocalDate.parse(value);
                    predicate = builder.equal(root.get(column).as(LocalDate.class),date);
                }catch (Exception e){
                    predicate = builder.like(root.get(column).as(String.class), "%" + value + "%");
                }
                query.where(predicate);
            }
            if(orderBy != null){
                if("DESC".equalsIgnoreCase(sortBy)){
                    query.orderBy(builder.desc(root.get(orderBy)));
                }
                pageable = PageRequest.of(currentPage/ pageSize, pageSize,
                        "DESC".equalsIgnoreCase(sortBy) ? Sort.by(orderBy).descending()
                                                        : Sort.by(orderBy).ascending());
            }else {
                pageable = PageRequest.of(currentPage/ pageSize, pageSize);
            }

            Query<SocialInsurance> socialInsuranceQuery = session.createQuery(query);
            long totalItems = socialInsuranceQuery.getResultList().size();

            socialInsuranceQuery.setFirstResult(currentPage == 1 ? 0 : (currentPage - 1) * pageSize);
            socialInsuranceQuery.setMaxResults(pageSize);

            List<SocialInsurance> socialInsurances = socialInsuranceQuery.getResultList();

            return new PageImpl<>(socialInsurances,pageable,totalItems);

        }
    }
}
