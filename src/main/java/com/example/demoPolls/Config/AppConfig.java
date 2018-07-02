package com.example.demoPolls.Config;

import com.example.demoPolls.Entities.Answer;
import com.example.demoPolls.Entities.Poll;
import com.example.demoPolls.Entities.User;
import com.example.demoPolls.Repositories.HibernateRepository;
import com.example.demoPolls.Repositories.base.GenericRepository;
import com.example.demoPolls.Validators.UserValidator;
import com.example.demoPolls.Validators.base.Validator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    @Autowired
    GenericRepository<Poll> providePollsGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<Poll> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(Poll.class);

        return repo;
    }

    @Bean
    @Autowired
    GenericRepository<Answer> provideAnswerGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<Answer> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(Answer.class);

        return repo;
    }

    @Bean
    @Autowired
    GenericRepository<User> provideUsersGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<User> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(User.class);

        return repo;
    }

    @Bean
    SessionFactory provideSessionFactory() {
        return HibernateUtils.getSessionFactory();
    }

    @Bean
    Validator<User> provideUserValidator() { return new UserValidator();}

}

