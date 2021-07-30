package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.ImageDTO;
import com.tannd.commercemanager.maper.ImageMapper;
import com.tannd.commercemanager.model.Image;
import com.tannd.commercemanager.repository.ImageRepository;
import com.tannd.commercemanager.services.ImageService;

public class ImageServiceImpl extends AbstractServiceImpl<ImageRepository, ImageMapper, ImageDTO, Image>
        implements ImageService {
}
