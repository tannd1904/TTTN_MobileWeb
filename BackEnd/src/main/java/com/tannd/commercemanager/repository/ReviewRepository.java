package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.model.Review;

import java.util.List;

public interface ReviewRepository extends AbstractRepository<Review, Long>{
    List<Review> findAllByProductId(Long id);
}
