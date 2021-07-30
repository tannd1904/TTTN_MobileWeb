package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.dto.OrderDetailDTO;
import com.tannd.commercemanager.maper.OrderDetailMapper;
import com.tannd.commercemanager.maper.OrderMapper;
import com.tannd.commercemanager.model.Order;
import com.tannd.commercemanager.model.OrderDetail;
import com.tannd.commercemanager.repository.OrderDetailRepository;
import com.tannd.commercemanager.repository.OrderRepository;
import com.tannd.commercemanager.services.OrderDetailService;
import com.tannd.commercemanager.services.OrderService;

public class OrderDetailServiceImpl extends AbstractServiceImpl<OrderDetailRepository, OrderDetailMapper, OrderDetailDTO, OrderDetail>
        implements OrderDetailService {
}
