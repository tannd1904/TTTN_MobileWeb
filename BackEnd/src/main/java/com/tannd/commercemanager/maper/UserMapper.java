package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.ReviewDTO;
import com.tannd.commercemanager.dto.UserDTO;
import com.tannd.commercemanager.model.Review;
import com.tannd.commercemanager.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends AbstractMapper<UserDTO, User>{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
