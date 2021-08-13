package com.tannd.commercemanager.repository.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class AbstractRepositoryCustomTool implements ApplicationContextAware {

    protected static ApplicationContext applicationContext = null;
    protected static EntityManager entityManager = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AbstractRepositoryCustomTool.applicationContext = applicationContext;
        AbstractRepositoryCustomTool.entityManager = applicationContext.getBean(EntityManager.class);
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
