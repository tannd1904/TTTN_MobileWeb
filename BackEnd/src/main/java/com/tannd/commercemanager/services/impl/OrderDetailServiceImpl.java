package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.dto.OrderDetailDTO;
import com.tannd.commercemanager.maper.InvoiceMapper;
import com.tannd.commercemanager.maper.OrderDetailMapper;
import com.tannd.commercemanager.maper.OrderMapper;
import com.tannd.commercemanager.model.Order;
import com.tannd.commercemanager.model.OrderDetail;
import com.tannd.commercemanager.repository.InvoiceRepository;
import com.tannd.commercemanager.repository.OrderDetailRepository;
import com.tannd.commercemanager.repository.OrderRepository;
import com.tannd.commercemanager.services.OrderDetailService;
import com.tannd.commercemanager.services.OrderService;
import com.tannd.commercemanager.services.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class OrderDetailServiceImpl extends AbstractServiceImpl<OrderDetailRepository, OrderDetailMapper, OrderDetailDTO, OrderDetail>
        implements OrderDetailService {

    @Autowired
    OrderDetailRepository thisRepository;

    private OrderDetailMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public OrderDetailRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public OrderDetailMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
