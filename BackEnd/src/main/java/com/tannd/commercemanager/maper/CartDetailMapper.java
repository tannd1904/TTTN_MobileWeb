package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.CartDetailDTO;
import com.tannd.commercemanager.model.CartDetail;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartDetailMapper extends AbstractMapper<CartDetailDTO, CartDetail>{
    CartDetailMapper INSTANCE = Mappers.getMapper(CartDetailMapper.class);

    @Mappings({
            @Mapping(source = "cart.id", target = "cartId")
    })
    CartDetailDTO toDtoWithCartId(CartDetail entity, @Context CycleAvoidingMappingContext context);

    @Override
    default CartDetailDTO toDto(CartDetail entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithCartId(entity, context);
    }
}
