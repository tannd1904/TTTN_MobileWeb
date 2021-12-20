package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.AccessoryCateDTO;
import com.tannd.commercemanager.dto.InvoiceDTO;
import com.tannd.commercemanager.maper.AccessoryCategMapper;
import com.tannd.commercemanager.maper.InvoiceMapper;
import com.tannd.commercemanager.model.Accessory;
import com.tannd.commercemanager.model.Invoice;
import com.tannd.commercemanager.services.AccessoryCateService;
import com.tannd.commercemanager.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accessoryCate")
//@PreAuthorize("hasRole('ADMIN') or ('USER')")
public class AccessoryCateController extends AbstractController<AccessoryCateService, AccessoryCategMapper, AccessoryCateDTO, Accessory> {

    @Autowired
    AccessoryCateService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public AccessoryCateService getService() {
        initService();
        return service;
    }

    private AccessoryCategMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public AccessoryCategMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCate() {
        return getAll();
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getAccCateById(@PathVariable Long id) {
        return getById(id);
    }
}
