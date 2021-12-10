package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.AccessoryCateDTO;
import com.tannd.commercemanager.dto.AccessoryDTO;
import com.tannd.commercemanager.maper.AccessoryCategMapper;
import com.tannd.commercemanager.maper.AccessoryMapper;
import com.tannd.commercemanager.model.Accessory;
import com.tannd.commercemanager.model.AccessoryCate;
import com.tannd.commercemanager.repository.AccessoryCateRepository;
import com.tannd.commercemanager.repository.AccessoryRepository;
import com.tannd.commercemanager.services.AccessoryService;
import com.tannd.commercemanager.services.helper.ServiceHelper;
import com.tannd.commercemanager.services.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class AccessoryServiceImpl extends AbstractServiceImpl<AccessoryRepository, AccessoryMapper, AccessoryDTO, Accessory>
        implements AccessoryService {

    @Autowired
    AccessoryRepository thisRepository;

    private AccessoryMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public AccessoryRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public AccessoryMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
