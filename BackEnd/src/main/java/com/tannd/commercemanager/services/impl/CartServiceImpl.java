package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.CartDTO;
import com.tannd.commercemanager.maper.CartMapper;
import com.tannd.commercemanager.model.Cart;
import com.tannd.commercemanager.repository.CartRepository;
import com.tannd.commercemanager.services.CartService;

public class CartServiceImpl extends AbstractServiceImpl<CartRepository, CartMapper, CartDTO, Cart>
        implements CartService {
}
