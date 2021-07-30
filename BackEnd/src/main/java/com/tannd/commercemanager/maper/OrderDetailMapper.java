package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.OrderDetailDTO;
import com.tannd.commercemanager.model.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDetailMapper extends AbstractMapper<OrderDetailDTO, OrderDetail>{
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);
}
