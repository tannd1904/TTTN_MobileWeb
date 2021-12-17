package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.*;
import com.tannd.commercemanager.maper.*;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.model.*;
import com.tannd.commercemanager.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/property")
//@PreAuthorize("hasRole('ADMIN') or ('USER')")
public class PropertyController extends AbstractController<PropertyService, PropertyMapper, PropertyDTO, Property> {

    @Autowired
    PropertyService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public PropertyService getService() {
        initService();
        return service;
    }

    private PropertyMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public PropertyMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCategory() {
        return getAll();
    }

    @GetMapping("/get-by-product-id/{id}")
    public ResponseEntity<?> getByProductId(@PathVariable Long id) {
        return ResponseEntity.ok(new CustomResponse(200, "Request Get Property By Product Id Ok",
                getService().findByProductId(id).isEmpty() ? Collections.emptyList() : getService().findByProductId(id)));
    }
}
