package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.AccessoryCateDTO;
import com.tannd.commercemanager.dto.CategoryDTO;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.Accessory;
import com.tannd.commercemanager.model.AccessoryCate;
import com.tannd.commercemanager.model.Category;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccessoryCategMapper extends AbstractMapper<AccessoryCateDTO, AccessoryCate>{

    AccessoryCategMapper INSTANCE = Mappers.getMapper(AccessoryCategMapper.class);

    @Mappings({
//            @Mapping(target = "products", ignore = true)
    })
    AccessoryCateDTO toDtoWithoutProducts(AccessoryCate entity, @Context CycleAvoidingMappingContext context);

    @Override
    default AccessoryCateDTO toDto(AccessoryCate entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutProducts(entity, context);
    }

    @Mappings({
            @Mapping(target = "accessories", ignore = true)
    })
    AccessoryCate toEntityWithoutProducts(AccessoryCateDTO dto, @Context CycleAvoidingMappingContext context);

    @Override
    default AccessoryCate toEntity(AccessoryCateDTO dto, @Context CycleAvoidingMappingContext context) {
        return toEntityWithoutProducts(dto, context);
    }
}
