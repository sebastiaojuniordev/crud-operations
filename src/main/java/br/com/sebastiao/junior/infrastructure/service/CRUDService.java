package br.com.sebastiao.junior.infrastructure.service;

import java.util.List;

public interface CRUDService<T, I> {

    T insert(T entity);

    T update(T entity);

    void delete(I idEntity);

    void delete(List<T> entitys);

    T find(I primaryKey);

    List<T> find(List<I> primaryKeys);

    List<T> findAll();
}
