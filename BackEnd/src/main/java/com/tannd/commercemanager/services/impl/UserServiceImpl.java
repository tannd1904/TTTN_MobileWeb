package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.UserDTO;
import com.tannd.commercemanager.maper.UserMapper;
import com.tannd.commercemanager.model.User;
import com.tannd.commercemanager.repository.UserRepository;
import com.tannd.commercemanager.services.UserService;

public class UserServiceImpl extends AbstractServiceImpl<UserRepository, UserMapper, UserDTO, User>
        implements UserService {
}
