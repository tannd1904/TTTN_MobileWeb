package com.tannd.commercemanager.repository.helper;

import com.tannd.commercemanager.repository.helper.AbstractRepositoryCustomTool;

import javax.persistence.EntityManager;

public interface AbstractRepositoryHelper <E, ID> {

    default EntityManager getEntityManagerClass() {
        return AbstractRepositoryCustomTool.getEntityManager();
    }
}
