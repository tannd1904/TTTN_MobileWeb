package com.tannd.commercemanager.services;

import com.tannd.commercemanager.dto.ReviewDTO;
import com.tannd.commercemanager.model.Review;

import java.util.List;

public interface ReviewService extends AbstractService<ReviewDTO, Review>{
    List<ReviewDTO> getAllByProductId(Long id);
}
