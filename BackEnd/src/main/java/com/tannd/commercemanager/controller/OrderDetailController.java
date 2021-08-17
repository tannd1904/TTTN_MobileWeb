package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.dto.OrderDetailDTO;
import com.tannd.commercemanager.maper.OrderDetailMapper;
import com.tannd.commercemanager.model.Order;
import com.tannd.commercemanager.model.OrderDetail;
import com.tannd.commercemanager.services.OrderDetailService;
import com.tannd.commercemanager.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order-detail")
@PreAuthorize("hasRole('ADMIN') or ('USER')")
public class OrderDetailController extends AbstractController<OrderDetailService, OrderDetailMapper, OrderDetailDTO, OrderDetail> {

    @Autowired
    OrderDetailService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public OrderDetailService getService() {
        initService();
        return service;
    }

    private OrderDetailMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public OrderDetailMapper getMapper() {
        initMapper();
        return mapper;
    }
}
