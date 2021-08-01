package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.InvoiceDTO;
import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.model.Invoice;
import com.tannd.commercemanager.model.Order;
import com.tannd.commercemanager.services.InvoiceService;
import com.tannd.commercemanager.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@PreAuthorize("hasRole('ADMIN') or ('USER')")
public class OrderController extends AbstractController<OrderService, OrderDTO, Order> {

    @Autowired
    OrderService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public OrderService getService() {
        initService();
        return service;
    }
}
