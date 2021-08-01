package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.CategoryDTO;
import com.tannd.commercemanager.model.Category;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper extends AbstractMapper<CategoryDTO, Category>{

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mappings({
            @Mapping(target = "products", ignore = true)
    })
    CategoryDTO toDtoWithoutProducts(Category entity, @Context CycleAvoidingMappingContext context);

}
