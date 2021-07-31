package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.CartDetailDTO;
import com.tannd.commercemanager.model.CartDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartDetailMapper extends AbstractMapper<CartDetailDTO, CartDetail>{
    CartDetailMapper INSTANCE = Mappers.getMapper(CartDetailMapper.class);
}
