package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.CartDTO;
import com.tannd.commercemanager.dto.CategoryDTO;
import com.tannd.commercemanager.maper.CartMapper;
import com.tannd.commercemanager.model.Cart;
import com.tannd.commercemanager.model.Category;
import com.tannd.commercemanager.services.CartService;
import com.tannd.commercemanager.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@PreAuthorize("hasRole('ADMIN') or ('USER')")
public class CartController extends AbstractController<CartService, CartMapper, CartDTO, Cart> {

    @Autowired
    CartService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public CartService getService() {
        initService();
        return service;
    }

    private CartMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public CartMapper getMapper() {
        initMapper();
        return mapper;
    }
}
