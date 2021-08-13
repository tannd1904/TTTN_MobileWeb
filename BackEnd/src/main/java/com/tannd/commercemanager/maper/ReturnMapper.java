package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.ReturnDTO;
import com.tannd.commercemanager.dto.WishlistDTO;
import com.tannd.commercemanager.model.Return;
import com.tannd.commercemanager.model.Wishlist;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReturnMapper extends AbstractMapper<ReturnDTO, Return>{
    ReturnMapper INSTANCE = Mappers.getMapper(ReturnMapper.class);
}
