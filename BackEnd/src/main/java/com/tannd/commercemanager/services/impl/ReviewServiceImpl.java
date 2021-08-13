package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.ReviewDTO;
import com.tannd.commercemanager.maper.ReviewMapper;
import com.tannd.commercemanager.model.Review;
import com.tannd.commercemanager.repository.ReviewRepository;
import com.tannd.commercemanager.services.ReviewService;
import com.tannd.commercemanager.services.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class ReviewServiceImpl extends AbstractServiceImpl<ReviewRepository, ReviewMapper, ReviewDTO, Review>
        implements ReviewService {

    @Autowired
    ReviewRepository thisRepository;

    private ReviewMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ReviewRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public ReviewMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
