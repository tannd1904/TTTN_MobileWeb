package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.AccessoryCateDTO;
import com.tannd.commercemanager.dto.InvoiceDTO;
import com.tannd.commercemanager.maper.AccessoryCategMapper;
import com.tannd.commercemanager.maper.InvoiceMapper;
import com.tannd.commercemanager.model.AccessoryCate;
import com.tannd.commercemanager.model.Invoice;
import com.tannd.commercemanager.repository.AccessoryCateRepository;
import com.tannd.commercemanager.repository.InvoiceRepository;
import com.tannd.commercemanager.services.AccessoryCateService;
import com.tannd.commercemanager.services.InvoiceService;
import com.tannd.commercemanager.services.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class AccessoryCateServiceImpl extends AbstractServiceImpl<AccessoryCateRepository, AccessoryCategMapper, AccessoryCateDTO, AccessoryCate>
        implements AccessoryCateService {

    @Autowired
    AccessoryCateRepository thisRepository;

    private AccessoryCategMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public AccessoryCateRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public AccessoryCategMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
