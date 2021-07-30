package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.exception.ResourceNotFoundException;
import com.tannd.commercemanager.maper.AbstractMapper;
import com.tannd.commercemanager.maper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.services.AbstractService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AbstractServiceImpl<R extends JpaRepository, M extends AbstractMapper, D , E>
    implements AbstractService<D, E> {

    protected R repository;
    protected M mapper;
    protected D dto;
    protected E entity;

    public R getRepository() {
        return repository;
    }

    public M getMapper() {
        return mapper;
    }

    public void initRepository(R repository) {
        this.repository = repository;
    }

    public void initMapper(M mapper) {
        this.mapper = mapper;
    }

    public CycleAvoidingMappingContext getCycleAvoidingMappingContext() {
        return new CycleAvoidingMappingContext();
    }

    protected D getDto() {
        if (getMapper() == null) {
            // TODO BUG new Throwable("Can not load Mapper");
            throw new RuntimeException("Can not load Mapper");
        }
        return (D) getMapper().toDto(entity, getCycleAvoidingMappingContext());
    }

    protected E getEntity() {
        if (getMapper() == null) {
            // TODO BUG new Throwable("Can not load Mapper");
            throw new RuntimeException("Can not load Mapper");
        }
        return (E) getMapper().toEntity(dto, getCycleAvoidingMappingContext());
    }

    public void setDTO(D dto) {
        this.dto = dto;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    @Override
    @Transactional
    public E save(E entity) {
        if (entity == null) {
            // TODO BUG new Throwable("Save not success.");
            throw new RuntimeException("Save not success.");
        }

        return (E) getRepository().save(entity);
    }

    @Override
    public D save() {
        return null;
    }

    @Override
    @Transactional
    public List<D> save(List<D> dtos) {
        // resetCycleAvoidingMappingContext();
        if (dtos == null) {
            // TODO BUG new Throwable("Save not success.");
            throw new RuntimeException("Save not success.");
        }

        List<E> entities =
                (List<E>) dtos.stream().map(dto -> mapper.toEntity(dto, getCycleAvoidingMappingContext()))
                        .collect(Collectors.toList());
        entities = getRepository().saveAll(entities);
        return (List<D>) entities.stream()
                .map(entity -> mapper.toDto(entity, getCycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }

    @Override
    public D findById(long id) {
        // resetCycleAvoidingMappingContext();
        Optional<E> optional = getRepository().findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Resource not found");
        }
        return (D) mapper.toDto(optional.get(), getCycleAvoidingMappingContext());
    }

    @Override
    public void delete(long id) {
        try {
            // resetCycleAvoidingMappingContext();
            getRepository().deleteById(id);
        } catch (Exception e) {
            // TODO BUG new Throwable("Element not found.");
            throw new ResourceNotFoundException("Element not found.");
        }
    }

    @Override
    public List<D> findAll() {
        // resetCycleAvoidingMappingContext();
        List<E> list = getRepository().findAll();
        return (List<D>) list.stream()
                .map(entity -> getMapper().toDto(entity, getCycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }

    @Override
    public D findById(Object key) {
        if (key instanceof Serializable) {
            // resetCycleAvoidingMappingContext();
            Optional<E> optional = getRepository().findById(key);
            if (optional.isEmpty()) {
                // TODO BUG new Throwable("Element not found.");
                throw new ResourceNotFoundException("Element not found.");
            }
            return (D) mapper.toDto(optional.get(), getCycleAvoidingMappingContext());
        } else {
            // TODO BUG ?
            // must be : throw new ResourceNotFoundException("Element not found.");
            return null;
        }
    }

    @Override
    public E findEntityById(Object key) {
        if (key instanceof Serializable) {
            Optional<E> optional = getRepository().findById(key);
            if (optional.isEmpty()) {
                // TODO BUG new Throwable("Element not found.");
                throw new ResourceNotFoundException("Element not found.");
            }
            return (E) optional.get();
        } else {
            // TODO BUG ?
            // must be : throw new ResourceNotFoundException("Element not found.");
            return null;
        }
    }

    @Override
    public void delete(Object key) {
        if (key instanceof Serializable) {
            try {
                // resetCycleAvoidingMappingContext();
                getRepository().deleteById(key);
            } catch (Exception e) {
                // TODO BUG new Throwable("Element not found.");
                throw new ResourceNotFoundException("Element not found.");
            }
        } else {
            // TODO BUG new Throwable("Element not found.");
            throw new ResourceNotFoundException("Element not found.");
        }
    }
}
