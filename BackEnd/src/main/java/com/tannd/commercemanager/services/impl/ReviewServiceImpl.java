package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.ReviewDTO;
import com.tannd.commercemanager.maper.ReviewMapper;
import com.tannd.commercemanager.model.Review;
import com.tannd.commercemanager.repository.ReviewRepository;
import com.tannd.commercemanager.services.ReviewService;

public class ReviewServiceImpl extends AbstractServiceImpl<ReviewRepository, ReviewMapper, ReviewDTO, Review>
        implements ReviewService {
}
