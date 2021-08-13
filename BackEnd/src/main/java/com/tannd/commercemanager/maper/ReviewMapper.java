package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.ReviewDTO;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.Review;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper extends AbstractMapper<ReviewDTO, Review>{
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

//    @Mappings({
//            @Mapping(source = "user.id", target = "userId"),
//            @Mapping(source = "product.id", target = "productId")
//    })
//    ReviewDTO toDtoWithAll(Review entity, @Context CycleAvoidingMappingContext context);
//
//    @Override
//    default ReviewDTO toDto(Review entity, @Context CycleAvoidingMappingContext context) {
//        return toDtoWithAll(entity, context);
//    }
}
