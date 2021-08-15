package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.CategoryDTO;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.Category;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper extends AbstractMapper<CategoryDTO, Category>{

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mappings({
            @Mapping(target = "products", ignore = true)
    })
    CategoryDTO toDtoWithoutProducts(Category entity, @Context CycleAvoidingMappingContext context);

    @Override
    default CategoryDTO toDto(Category entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutProducts(entity, context);
    }

    @Mappings({
            @Mapping(target = "products", ignore = true)
    })
    Category toEntityWithoutProducts(CategoryDTO dto, @Context CycleAvoidingMappingContext context);

    @Override
    default Category toEntity(CategoryDTO dto, @Context CycleAvoidingMappingContext context) {
        return toEntityWithoutProducts(dto, context);
    }
}
