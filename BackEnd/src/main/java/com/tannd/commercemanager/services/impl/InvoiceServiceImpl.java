package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.InvoiceDTO;
import com.tannd.commercemanager.maper.InvoiceMapper;
import com.tannd.commercemanager.model.Invoice;
import com.tannd.commercemanager.repository.InvoiceRepository;
import com.tannd.commercemanager.services.InvoiceService;
import com.tannd.commercemanager.services.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class InvoiceServiceImpl extends AbstractServiceImpl<InvoiceRepository, InvoiceMapper, InvoiceDTO, Invoice>
        implements InvoiceService {

    @Autowired
    InvoiceRepository thisRepository;

    private InvoiceMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public InvoiceRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public InvoiceMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
