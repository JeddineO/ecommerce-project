package com.app.boutique.dao;

public interface IDAO<T> {
    boolean save(T object);
    boolean update(T object);
    boolean delete(T object);
    T findById(int motif);
}
