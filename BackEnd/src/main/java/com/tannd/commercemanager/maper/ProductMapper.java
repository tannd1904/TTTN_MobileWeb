package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.ProductDTO;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper extends AbstractMapper<ProductDTO, Product>{
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(source = "category.id", target = "categoryId"),
            @Mapping(target = "importVoucherDetails", ignore = true),
            @Mapping(target = "wishlists", ignore = true),
            @Mapping(target = "reviews", ignore = true),
            @Mapping(target = "promotions", ignore = true),
            @Mapping(target = "properties", ignore = true)
    })
    ProductDTO toDtoWithoutList(Product entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "category.id", target = "categoryId")
    })
    ProductDTO toDtoWithAll(Product entity, @Context CycleAvoidingMappingContext context);

    @Override
    default ProductDTO toDto(Product entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithAll(entity, context);
    }
}
