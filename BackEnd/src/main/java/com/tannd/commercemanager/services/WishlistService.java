package com.tannd.commercemanager.services;

import com.tannd.commercemanager.dto.WishlistDTO;
import com.tannd.commercemanager.model.Wishlist;

import java.util.List;

public interface WishlistService extends AbstractService<WishlistDTO, Wishlist>{
    List<WishlistDTO> getByUserId(Long userId);
    Wishlist getByProductId(Long productId);
}
