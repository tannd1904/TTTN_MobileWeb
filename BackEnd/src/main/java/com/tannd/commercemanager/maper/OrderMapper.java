package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.Order;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends AbstractMapper<OrderDTO, Order>{
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "employee.id", target = "employeeId")
    })
    OrderDTO toDtoWithUser(Order entity, @Context CycleAvoidingMappingContext context);

    @Override
    default OrderDTO toDto(Order entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithUser(entity, context);
    }

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Order toEntityWithoutId(OrderDTO dto, @Context CycleAvoidingMappingContext context);

    @Override
    default Order toEntity(OrderDTO dto, @Context CycleAvoidingMappingContext context) {
        return toEntityWithoutId(dto, context);
    }
}
