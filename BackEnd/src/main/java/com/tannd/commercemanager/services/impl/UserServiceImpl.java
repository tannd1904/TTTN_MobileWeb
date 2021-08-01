package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.UserDTO;
import com.tannd.commercemanager.maper.ReviewMapper;
import com.tannd.commercemanager.maper.UserMapper;
import com.tannd.commercemanager.model.User;
import com.tannd.commercemanager.repository.ReviewRepository;
import com.tannd.commercemanager.repository.UserRepository;
import com.tannd.commercemanager.services.ServiceHelper;
import com.tannd.commercemanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class UserServiceImpl extends AbstractServiceImpl<UserRepository, UserMapper, UserDTO, User>
        implements UserService {
    @Autowired
    UserRepository thisRepository;

    private UserMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public UserRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public UserMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
