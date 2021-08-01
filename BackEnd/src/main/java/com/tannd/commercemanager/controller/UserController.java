package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.dto.UserDTO;
import com.tannd.commercemanager.maper.UserMapper;
import com.tannd.commercemanager.model.Order;
import com.tannd.commercemanager.model.User;
import com.tannd.commercemanager.services.OrderService;
import com.tannd.commercemanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ADMIN') or ('USER')")
public class UserController extends AbstractController<UserService, UserMapper, UserDTO, User> {

    @Autowired
    UserService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public UserService getService() {
        initService();
        return service;
    }

    private UserMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public UserMapper getMapper() {
        initMapper();
        return mapper;
    }
}
