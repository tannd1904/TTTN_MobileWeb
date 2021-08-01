package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.maper.OrderDetailMapper;
import com.tannd.commercemanager.maper.OrderMapper;
import com.tannd.commercemanager.model.Order;
import com.tannd.commercemanager.repository.OrderDetailRepository;
import com.tannd.commercemanager.repository.OrderRepository;
import com.tannd.commercemanager.services.OrderService;
import com.tannd.commercemanager.services.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class OrderServiceImpl extends AbstractServiceImpl<OrderRepository, OrderMapper, OrderDTO, Order>
        implements OrderService {
    @Autowired
    OrderRepository thisRepository;

    private OrderMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public OrderRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public OrderMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
