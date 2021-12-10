package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.PromotionDTO;
import com.tannd.commercemanager.dto.WishlistDTO;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.Promotion;
import com.tannd.commercemanager.model.Wishlist;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PromotionMapper extends AbstractMapper<PromotionDTO, Promotion>{
    PromotionMapper INSTANCE = Mappers.getMapper(PromotionMapper.class);

    @Mappings({
            @Mapping(source = "product.id", target = "productId"),
            @Mapping(source = "accessory.id", target = "accessoryId")
    })
    PromotionDTO toDtoWithAll(Promotion entity, @Context CycleAvoidingMappingContext context);

    @Override
    default PromotionDTO toDto(Promotion entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithAll(entity, context);
    }
}
