package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.ImportVoucherDTO;
import com.tannd.commercemanager.dto.PromotionDTO;
import com.tannd.commercemanager.dto.PropertyDTO;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.ImportVoucher;
import com.tannd.commercemanager.model.Property;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PropertyMapper extends AbstractMapper<PropertyDTO, Property>{
    PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

    @Mappings({
            @Mapping(source = "product.id", target = "productId")
    })
    PropertyDTO toDtoWithoutDetails(Property entity, @Context CycleAvoidingMappingContext context);

    @Override
    default PropertyDTO toDto(Property entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutDetails(entity, context);
    }

    @Mappings({
            @Mapping(source = "productId", target = "product.id")
    })
    Property toEntityWithEmplId(PropertyDTO dto, @Context CycleAvoidingMappingContext context);

    @Override
    default Property toEntity(PropertyDTO dto, @Context CycleAvoidingMappingContext context) {
        return toEntityWithEmplId(dto, context);
    }

}
