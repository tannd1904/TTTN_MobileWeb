package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.ImageDTO;
import com.tannd.commercemanager.dto.InvoiceDTO;
import com.tannd.commercemanager.maper.InvoiceMapper;
import com.tannd.commercemanager.model.Image;
import com.tannd.commercemanager.model.Invoice;
import com.tannd.commercemanager.services.ImageService;
import com.tannd.commercemanager.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoice")
@PreAuthorize("hasRole('ADMIN') or ('USER')")
public class InvoiceController extends AbstractController<InvoiceService, InvoiceMapper, InvoiceDTO, Invoice> {

    @Autowired
    InvoiceService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public InvoiceService getService() {
        initService();
        return service;
    }

    private InvoiceMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public InvoiceMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
