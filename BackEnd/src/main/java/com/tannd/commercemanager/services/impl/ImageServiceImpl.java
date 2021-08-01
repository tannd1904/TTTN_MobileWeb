package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.ImageDTO;
import com.tannd.commercemanager.maper.CategoryMapper;
import com.tannd.commercemanager.maper.ImageMapper;
import com.tannd.commercemanager.model.Image;
import com.tannd.commercemanager.repository.CategoryRepository;
import com.tannd.commercemanager.repository.ImageRepository;
import com.tannd.commercemanager.services.ImageService;
import com.tannd.commercemanager.services.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class ImageServiceImpl extends AbstractServiceImpl<ImageRepository, ImageMapper, ImageDTO, Image>
        implements ImageService {

    @Autowired
    ImageRepository thisRepository;

    private ImageMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ImageRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public ImageMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
