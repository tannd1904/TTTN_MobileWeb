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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/promotion")
//@PreAuthorize("hasRole('ADMIN') or ('USER')")
public class PromotionController extends AbstractController<PromotionService, PromotionMapper, PromotionDTO, Promotion> {

    @Autowired
    PromotionService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public PromotionService getService() {
        initService();
        return service;
    }

    private PromotionMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public PromotionMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCategory() {
        return getAll();
    }

    @GetMapping("/get-by-product-id/{id}")
    public ResponseEntity<?> getByProductId(@PathVariable Long id) {
        return ResponseEntity.ok(new CustomResponse(200, "Request get by product Id OK",
                getService().getByProductId(id).isEmpty() ? Collections.emptyList() : getService().getByProductId(id)));
    }

    @GetMapping("/get-by-accessory-id/{id}")
    public ResponseEntity<?> getByAccessoryId(@PathVariable Long id) {
        return ResponseEntity.ok(new CustomResponse(200, "Request get by product Id OK",
                getService().getByAccessoryId(id).isEmpty() ? Collections.emptyList() : getService().getByAccessoryId(id)));
    }
}
