package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.ProductDetailDTO;
import com.tannd.commercemanager.dto.ReviewDTO;
import com.tannd.commercemanager.maper.ReviewMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.ProductDetail;
import com.tannd.commercemanager.model.Review;
import com.tannd.commercemanager.repository.ReviewRepository;
import com.tannd.commercemanager.services.ReviewService;
import com.tannd.commercemanager.services.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<ReviewDTO> getAllByProductId(Long id) {
        List<Review> listEntity = getRepository().findAllByProductId(id);
        System.out.println(listEntity.toString());
        List<ReviewDTO> list = new ArrayList<>();
        listEntity.stream().forEach(product -> {
            var dto = getMapper().toDto(product, new CycleAvoidingMappingContext());
            dto.setUserId(product.getOrder().getUser().getId());
            list.add(dto);
        });
        return list;
    }
}
