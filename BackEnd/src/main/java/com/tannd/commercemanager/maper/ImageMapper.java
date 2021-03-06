package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.ImageDTO;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.Image;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImageMapper extends AbstractMapper<ImageDTO, Image>{

    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    @Mappings({
            @Mapping(source = "product.id", target = "productId")
    })
    ImageDTO toDtoWithProductId(Image entity, @Context CycleAvoidingMappingContext context);

    @Override
    default ImageDTO toDto(Image entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithProductId(entity, context);
    }
}
