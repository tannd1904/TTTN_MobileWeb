package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.PromotionDTO;
import com.tannd.commercemanager.dto.PropertyDTO;
import com.tannd.commercemanager.maper.PromotionMapper;
import com.tannd.commercemanager.maper.PropertyMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.Promotion;
import com.tannd.commercemanager.model.Property;
import com.tannd.commercemanager.repository.PromotionRepository;
import com.tannd.commercemanager.repository.PropertyRepository;
import com.tannd.commercemanager.services.PromotionService;
import com.tannd.commercemanager.services.PropertyService;
import com.tannd.commercemanager.services.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ServiceHelper
public class PromotionServiceImpl extends AbstractServiceImpl<PromotionRepository, PromotionMapper, PromotionDTO, Promotion>
        implements PromotionService {

    @Autowired
    PromotionRepository thisRepository;

    private PromotionMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public PromotionRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public PromotionMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @Override
    public List<PromotionDTO> getByProductId(Long id) {
        return getRepository().getByProductId(id).stream().map(item ->
                getMapper().toDto(item, new CycleAvoidingMappingContext())).collect(Collectors.toList());
    }

    @Override
    public List<PromotionDTO> getByAccessoryId(Long id) {
        return getRepository().getByAccessoryId(id).stream().map(item ->
                getMapper().toDto(item, new CycleAvoidingMappingContext())).collect(Collectors.toList());
    }
}
