package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.ProductDetailDTO;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.ProductDetail;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductDetailMapper extends AbstractMapper<ProductDetailDTO, ProductDetail>{
    ProductDetailMapper INSTANCE = Mappers.getMapper(ProductDetailMapper.class);

//    @Mappings({
//            @Mapping(source = "product.id", target = "productId")
//    })
//    ProductDetailDTO toDtoWithAll(ProductDetail entity, @Context CycleAvoidingMappingContext context);

//    @Override
//    default ProductDetailDTO toDto(ProductDetail entity, @Context CycleAvoidingMappingContext context) {
//        return toDtoWithAll(entity, context);
//    }
}
