package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.*;
import com.tannd.commercemanager.maper.*;
import com.tannd.commercemanager.model.*;
import com.tannd.commercemanager.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
