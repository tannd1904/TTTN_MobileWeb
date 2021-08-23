package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.WishlistDTO;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.Wishlist;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WishlistMapper extends AbstractMapper<WishlistDTO, Wishlist>{
    WishlistMapper INSTANCE = Mappers.getMapper(WishlistMapper.class);

    @Mappings({
            @Mapping(source = "product.id", target = "productId"),
            @Mapping(source = "user.id", target = "userId")
    })
    WishlistDTO toDtoWithAll(Wishlist entity, @Context CycleAvoidingMappingContext context);

    @Override
    default WishlistDTO toDto(Wishlist entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithAll(entity, context);
    }
}
