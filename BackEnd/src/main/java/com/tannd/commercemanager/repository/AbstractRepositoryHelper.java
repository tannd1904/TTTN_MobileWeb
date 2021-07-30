package com.tannd.commercemanager.repository;

import javax.persistence.EntityManager;

public interface AbstractRepositoryHelper <E, ID> {

    default EntityManager getEntityManagerClass() {
        return AbstractRepositoryCustomTool.getEntityManager();
    }
}
