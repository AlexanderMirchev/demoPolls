package com.example.demoPolls.Config;

import com.example.demoPolls.Entities.Answer;
import com.example.demoPolls.Entities.Poll;
import com.example.demoPolls.Entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Configuration;

public class HibernateUtils {
    static SessionFactory sessionFactory;

    static {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration()
            .configure();

        configuration.addAnnotatedClass(Poll.class);
        configuration.addAnnotatedClass(Answer.class);
        configuration.addAnnotatedClass(User.class);

        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

        serviceRegistryBuilder.applySettings(configuration.getProperties());
        StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
