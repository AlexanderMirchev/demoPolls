package com.example.demoPolls.Repositories;

import com.example.demoPolls.Repositories.base.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

//Repository implementation: connecting with the sql
//Simple object operations
@Repository
public class HibernateRepository<T> implements GenericRepository<T> {
    private final SessionFactory sessionFactory;
    private Class<T> entityClass;

    public HibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //Returns a list of all entities
    @Override
    public List<T> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<T> criteriaQuery = builder.createQuery(getEntityClass());
        criteriaQuery.from(getEntityClass());

        List<T> entities = session.createQuery(criteriaQuery)
            .getResultList();

        transaction.commit();
        session.close();

        return entities;
    }

    //Returns a single entity by id
    @Override
    public T getById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<T> criteriaQuery = builder.createQuery(getEntityClass());

        T entity = session.get(getEntityClass(), id);

        transaction.commit();
        session.close();

        return entity;
    }

    //Add to sql
    @Override
    public T create(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session
            .save(entity);

        transaction.commit();
        session.close();
        return entity;
    }

    //Update entity information
    @Override
    public T update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();


        session
                .merge(entity);


        transaction.commit();
        session.close();

        return entity;
    }


    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}
