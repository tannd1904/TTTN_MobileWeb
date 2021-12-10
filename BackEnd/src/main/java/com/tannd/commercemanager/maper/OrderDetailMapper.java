package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.OrderDetailDTO;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.OrderDetail;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDetailMapper extends AbstractMapper<OrderDetailDTO, OrderDetail>{
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

    @Mappings({
            @Mapping(source = "order.id", target = "orderId"),
            @Mapping(source = "productDetail.serial", target = "productDetailSerial")
    })
    OrderDetailDTO toDtoWithAll(OrderDetail entity, @Context CycleAvoidingMappingContext context);

    @Override
    default OrderDetailDTO toDto(OrderDetail entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithAll(entity, context);
    }
}
