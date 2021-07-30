package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends AbstractMapper<OrderDTO, OrderMapper>{
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
}
