package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.ReviewDTO;
import com.tannd.commercemanager.dto.UserDTO;
import com.tannd.commercemanager.maper.ReviewMapper;
import com.tannd.commercemanager.model.Review;
import com.tannd.commercemanager.model.User;
import com.tannd.commercemanager.services.ReviewService;
import com.tannd.commercemanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
@PreAuthorize("hasRole('ADMIN') or ('USER')")
public class ReviewController extends AbstractController<ReviewService, ReviewMapper, ReviewDTO, Review> {

    @Autowired
    ReviewService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public ReviewService getService() {
        initService();
        return service;
    }

    private ReviewMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ReviewMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
