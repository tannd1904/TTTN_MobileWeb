package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.maper.OrderMapper;
import com.tannd.commercemanager.model.Order;
import com.tannd.commercemanager.repository.OrderRepository;
import com.tannd.commercemanager.services.OrderService;

public class OrderServiceImpl extends AbstractServiceImpl<OrderRepository, OrderMapper, OrderDTO, Order>
        implements OrderService {
}
