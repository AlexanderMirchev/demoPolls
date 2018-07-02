package com.example.demoPolls.Repositories.base;

import java.util.List;

//Repository interface
public interface GenericRepository<T> {
    List<T> getAll();

    T getById(int id);

    T create(T entity);

    T update(T entity);
}
