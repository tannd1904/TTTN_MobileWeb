package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.dto.ProductDetailDTO;
import com.tannd.commercemanager.model.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductDetailMapper extends AbstractMapper<ProductDetailDTO, ProductDetail>{
    ProductDetailMapper INSTANCE = Mappers.getMapper(ProductDetailMapper.class);
}
