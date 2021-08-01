package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.controller.AbstractController;
import com.tannd.commercemanager.dto.CategoryDTO;
import com.tannd.commercemanager.maper.CategoryMapper;
import com.tannd.commercemanager.message.request.CategoryRequest;
import com.tannd.commercemanager.model.Category;
import com.tannd.commercemanager.services.CategoryService;
import com.tannd.commercemanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@PreAuthorize("hasRole('ADMIN') or ('USER')")
public class CategoryController extends AbstractController<CategoryService, CategoryMapper, CategoryDTO, Category> {

    @Autowired
    CategoryService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public CategoryService getService() {
        initService();
        return service;
    }

    private CategoryMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public CategoryMapper getMapper() {
        initMapper();
        return mapper;
    }
}
