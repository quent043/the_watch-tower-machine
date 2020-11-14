package com.watchtower_machine.controller;

import com.watchtower_machine.entity.DetailSpot;

import java.io.Serializable;
import java.util.List;

public interface IController<K extends Serializable, T> {

    public void createObject (T entity);

    public String updateObject (K id, T newData);

    public String deleteObject (K id);

    public T readObjectById (K id);

    public List<T> readAll ();
}
