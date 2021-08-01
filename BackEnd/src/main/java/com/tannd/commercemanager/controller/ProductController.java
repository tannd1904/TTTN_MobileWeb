package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.controller.AbstractController;
import com.tannd.commercemanager.dto.ProductDTO;
import com.tannd.commercemanager.maper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.maper.ProductMapper;
import com.tannd.commercemanager.message.request.ProductRequest;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@PreAuthorize("hasRole('ADMIN') or ('USER')")
public class ProductController extends AbstractController<ProductService, ProductDTO, Product> {

    @Autowired
    ProductService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public ProductService getService() {
        initService();
        return service;
    }
}
