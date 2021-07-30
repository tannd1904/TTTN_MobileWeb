package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.CartDTO;
import com.tannd.commercemanager.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartMapper extends AbstractMapper<CartDTO, Cart>{
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
}
