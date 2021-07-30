package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.ProductDTO;
import com.tannd.commercemanager.dto.ProductDetailDTO;
import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.model.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper extends AbstractMapper<ProductDTO, Product>{
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
}
