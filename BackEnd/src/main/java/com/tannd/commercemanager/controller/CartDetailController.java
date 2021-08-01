package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.CartDTO;
import com.tannd.commercemanager.dto.CartDetailDTO;
import com.tannd.commercemanager.model.Cart;
import com.tannd.commercemanager.model.CartDetail;
import com.tannd.commercemanager.services.CartDetailService;
import com.tannd.commercemanager.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart-detail")
@PreAuthorize("hasRole('ADMIN') or ('USER')")
public class CartDetailController extends AbstractController<CartDetailService, CartDetailDTO, CartDetail> {

    @Autowired
    CartDetailService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public CartDetailService getService() {
        initService();
        return service;
    }
}
