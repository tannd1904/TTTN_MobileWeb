package com.tannd.commercemanager.services;

import java.util.List;

public interface AbstractService<D, E> {

    E save(E entity);

    D save();

    List<D> save(List<D> dtos);

    D findById(long id);

    void delete(long id);

    List<D> findAll();

    D findById(Object id);

    E findEntityById(Object id);

    void delete(Object dto);
}
