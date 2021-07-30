package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.ImageDTO;
import com.tannd.commercemanager.model.Image;
import com.tannd.commercemanager.repository.ProductRepository;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImageMapper extends AbstractMapper<ImageDTO, Image>{

    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);
}
