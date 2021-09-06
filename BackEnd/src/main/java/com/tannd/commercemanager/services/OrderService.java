package com.tannd.commercemanager.services;

import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.model.Order;

import java.util.List;

public interface OrderService extends AbstractService<OrderDTO, Order>{
    List<OrderDTO> getByUserId(Long id);
    List<Number> getOrdersInCurrentYear();
    List<Number> getOrdersInCurrentMonth();
}
