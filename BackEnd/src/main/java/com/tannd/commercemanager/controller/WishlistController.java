package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.WishlistDTO;
import com.tannd.commercemanager.maper.WishlistMapper;
import com.tannd.commercemanager.model.Wishlist;
import com.tannd.commercemanager.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@PreAuthorize("hasRole('ADMIN') or ('USER')")
public class WishlistController extends AbstractController<WishlistService, WishlistMapper, WishlistDTO, Wishlist> {

    @Autowired
    WishlistService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public WishlistService getService() {
        initService();
        return service;
    }

    private WishlistMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public WishlistMapper getMapper() {
        initMapper();
        return mapper;
    }
}
