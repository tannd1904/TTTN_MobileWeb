package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.WishlistDTO;
import com.tannd.commercemanager.maper.WishlistMapper;
import com.tannd.commercemanager.model.Wishlist;
import com.tannd.commercemanager.repository.WishlistRepository;
import com.tannd.commercemanager.services.WishlistService;
import com.tannd.commercemanager.services.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class WishlistServiceImpl extends AbstractServiceImpl<WishlistRepository, WishlistMapper, WishlistDTO, Wishlist>
        implements WishlistService {

    @Autowired
    WishlistRepository thisRepository;

    private WishlistMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public WishlistRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public WishlistMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
