package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.AccessoryDTO;
import com.tannd.commercemanager.dto.ProductDTO;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.Accessory;
import com.tannd.commercemanager.model.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccessoryMapper extends AbstractMapper<AccessoryDTO, Accessory>{
    AccessoryMapper INSTANCE = Mappers.getMapper(AccessoryMapper.class);

    @Mappings({
            @Mapping(source = "category.id", target = "accessoryCateId"),
            @Mapping(target = "promotions", ignore = true)
    })
    AccessoryDTO toDtoWithoutList(Accessory entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "category.id", target = "accessoryCateId")
    })
    AccessoryDTO toDtoWithAll(Accessory entity, @Context CycleAvoidingMappingContext context);

    @Override
    default AccessoryDTO toDto(Accessory entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithAll(entity, context);
    }
}
