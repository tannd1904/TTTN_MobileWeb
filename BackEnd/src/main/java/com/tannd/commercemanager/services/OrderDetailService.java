package com.tannd.commercemanager.services;

import com.tannd.commercemanager.dto.OrderDetailDTO;
import com.tannd.commercemanager.model.OrderDetail;

import java.util.List;

public interface OrderDetailService extends AbstractService<OrderDetailDTO, OrderDetail>{
    List<OrderDetailDTO> getByOrderId(Long id);
}
