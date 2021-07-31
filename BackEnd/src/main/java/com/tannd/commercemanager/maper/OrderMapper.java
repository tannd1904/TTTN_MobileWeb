package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends AbstractMapper<OrderDTO, Order>{
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
}
