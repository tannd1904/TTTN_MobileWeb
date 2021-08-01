package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.dto.OrderDetailDTO;
import com.tannd.commercemanager.model.OrderDetail;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDetailMapper extends AbstractMapper<OrderDetailDTO, OrderDetail>{
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

    @Mappings({
            @Mapping(source = "order.id", target = "orderId"),
            @Mapping(source = "productDetail.id", target = "productDetailId")
    })
    OrderDetailDTO toDtoWithAll(OrderDetail entity, @Context CycleAvoidingMappingContext context);

    @Override
    default OrderDetailDTO toDto(OrderDetail entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithAll(entity, context);
    }
}
