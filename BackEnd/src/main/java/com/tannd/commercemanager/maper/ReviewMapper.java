package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.ProductDTO;
import com.tannd.commercemanager.dto.ReviewDTO;
import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper extends AbstractMapper<ReviewDTO, Review>{
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
}
