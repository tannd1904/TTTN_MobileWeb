package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.WishlistDTO;
import com.tannd.commercemanager.model.Wishlist;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WishlistMapper extends AbstractMapper<WishlistDTO, Wishlist>{
    WishlistMapper INSTANCE = Mappers.getMapper(WishlistMapper.class);
}
