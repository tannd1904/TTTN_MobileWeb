package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.AccessoryDTO;
import com.tannd.commercemanager.dto.PropertyDTO;
import com.tannd.commercemanager.maper.AccessoryMapper;
import com.tannd.commercemanager.maper.PropertyMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.Accessory;
import com.tannd.commercemanager.model.Property;
import com.tannd.commercemanager.repository.AccessoryRepository;
import com.tannd.commercemanager.repository.PropertyRepository;
import com.tannd.commercemanager.services.AccessoryService;
import com.tannd.commercemanager.services.PropertyService;
import com.tannd.commercemanager.services.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ServiceHelper
public class PropertyServiceImpl extends AbstractServiceImpl<PropertyRepository, PropertyMapper, PropertyDTO, Property>
        implements PropertyService {

    @Autowired
    PropertyRepository thisRepository;

    private PropertyMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public PropertyRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public PropertyMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @Override
    public List<PropertyDTO> findByProductId(Long productId) {
        return getRepository().findByProductId(productId).stream().map(item -> getMapper().toDtoWithoutDetails(item, new CycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }
}
