package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.ReviewDTO;
import com.tannd.commercemanager.dto.UserDTO;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.Review;
import com.tannd.commercemanager.model.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends AbstractMapper<UserDTO, User>{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "orders", ignore = true),
    })
    UserDTO toDtoWithoutLists(User entity, @Context CycleAvoidingMappingContext context);

    @Override
    default UserDTO toDto(User entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }
}
