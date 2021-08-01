package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.CartDetailDTO;
import com.tannd.commercemanager.maper.CartDetailMapper;
import com.tannd.commercemanager.maper.ProductMapper;
import com.tannd.commercemanager.model.CartDetail;
import com.tannd.commercemanager.repository.CartDetailRepository;
import com.tannd.commercemanager.repository.ProductRepository;
import com.tannd.commercemanager.services.CartDetailService;
import com.tannd.commercemanager.services.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class CartDetailServiceImpl extends AbstractServiceImpl<CartDetailRepository, CartDetailMapper, CartDetailDTO, CartDetail>
        implements CartDetailService {
    @Autowired
    CartDetailRepository thisRepository;

    private CartDetailMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public CartDetailRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public CartDetailMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
