package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.ProductDTO;
import com.tannd.commercemanager.dto.WishlistDTO;
import com.tannd.commercemanager.exception.ResourceNotFoundException;
import com.tannd.commercemanager.maper.WishlistMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.model.Wishlist;
import com.tannd.commercemanager.repository.WishlistRepository;
import com.tannd.commercemanager.services.WishlistService;
import com.tannd.commercemanager.services.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<WishlistDTO> getByUserId(Long userId) {
        List<Wishlist> listEntity = getRepository().findByUserId(userId);
        System.out.println(listEntity.toString());
        List<WishlistDTO> list = new ArrayList<>();
        listEntity.stream().forEach(product -> {
            if (product.getRemoval_flag() == false) {
                list.add(getMapper().toDto(product, new CycleAvoidingMappingContext()));
            }
        });
        return list;
    }

    @Override
    public Wishlist getByProductId(Long productId) {
        Wishlist listEntity = getRepository().findByProductId(productId);
        return listEntity;
    }

    @Override
    public List<WishlistDTO> findAll() {
        List<Wishlist> list1 = getRepository().findAll();
        List<Wishlist> list = new ArrayList<>();
        list1.forEach(s -> {
            if (s.getRemoval_flag() == false) {
                list.add(s);
            }
        });
        return (List<WishlistDTO>) list.stream()
                .map(entity -> getMapper().toDto(entity, getCycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Object key) {
        var entity = getRepository().findById((Long) key);
        if (!entity.isPresent()) {
            throw new ResourceNotFoundException("Element not found.");
        } else {
            entity.get().setRemoval_flag(true);
        }
    }
}
