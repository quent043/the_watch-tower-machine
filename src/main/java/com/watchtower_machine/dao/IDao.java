package com.watchtower_machine.dao;

import com.watchtower_machine.entity.DetailSpot;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IDao<K extends Serializable, T> {

    int create(T entity);

    List<T> selectAll();

    Optional<T> selectById(K id);

    void updateById(K id, T newData);

    int delete (K id);
}