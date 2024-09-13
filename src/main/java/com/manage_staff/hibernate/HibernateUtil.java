package com.manage_staff.hibernate;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;

import com.manage_staff.entity.*;

public class HibernateUtil {
    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();
        Properties pros = new Properties();
        pros.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        pros.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        pros.put(Environment.URL, "jdbc:mysql://localhost:3309/manage_staff");
        pros.put(Environment.USER, "root");
        pros.put(Environment.PASS, "190103");

        conf.setProperties(pros);
        conf.addAnnotatedClass(Benefit.class);
        conf.addAnnotatedClass(Certification.class);
        conf.addAnnotatedClass(Department.class);
        conf.addAnnotatedClass(LeaveDay.class);
        conf.addAnnotatedClass(Payroll.class);
        conf.addAnnotatedClass(Permission.class);
        conf.addAnnotatedClass(Position.class);
        conf.addAnnotatedClass(RewardDiscipline.class);
        conf.addAnnotatedClass(Role.class);
        conf.addAnnotatedClass(SocialInsurance.class);
        conf.addAnnotatedClass(Staff.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties())
                .build();
        FACTORY = conf.buildSessionFactory(registry);
    }

    @Bean
    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
}
