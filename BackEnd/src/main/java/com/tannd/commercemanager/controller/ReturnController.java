package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.ReturnDTO;
import com.tannd.commercemanager.dto.ReviewDTO;
import com.tannd.commercemanager.maper.ReturnMapper;
import com.tannd.commercemanager.maper.ReviewMapper;
import com.tannd.commercemanager.model.Return;
import com.tannd.commercemanager.model.Review;
import com.tannd.commercemanager.services.ReturnService;
import com.tannd.commercemanager.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/return")
public class ReturnController extends AbstractController<ReturnService, ReturnMapper,
        ReturnDTO, Return> {

    @Autowired
    ReturnService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public ReturnService getService() {
        initService();
        return service;
    }

    private ReturnMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ReturnMapper getMapper() {
        initMapper();
        return mapper;
    }
}
