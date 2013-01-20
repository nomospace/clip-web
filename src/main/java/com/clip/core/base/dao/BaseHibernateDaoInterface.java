package com.clip.core.base.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseHibernateDaoInterface<E, PK extends Serializable> {

    public List<E> getAll();

    public E get(PK id);

    public E load(PK id);

    public boolean exists(PK id);

    public E add(E object);

    List<E> find(final String queryString, final Object... values);

    public void remove(PK id);

    void remove(String hql, Object[] params);

    public void remove(List<E> list);

    public void remove(E deleteOne);

    void refresh(Object object);

    void removeBatch(Class<E> clazz, Serializable[] ids);

    public boolean batchSave(List<E> list);

    public boolean autoBatchSave(List<E> list);

    public E autoSave(E object);

    public boolean update(E entity);

}
