package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.CartDTO;
import com.tannd.commercemanager.maper.CartMapper;
import com.tannd.commercemanager.maper.ProductMapper;
import com.tannd.commercemanager.model.Cart;
import com.tannd.commercemanager.repository.CartRepository;
import com.tannd.commercemanager.repository.ProductRepository;
import com.tannd.commercemanager.services.CartService;
import com.tannd.commercemanager.services.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class CartServiceImpl extends AbstractServiceImpl<CartRepository, CartMapper, CartDTO, Cart>
        implements CartService {

    @Autowired
    CartRepository thisRepository;

    private CartMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public CartRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public CartMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
