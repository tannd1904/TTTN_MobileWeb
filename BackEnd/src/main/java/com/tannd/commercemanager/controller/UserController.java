package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.dto.UserDTO;
import com.tannd.commercemanager.maper.UserMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.model.Order;
import com.tannd.commercemanager.model.User;
import com.tannd.commercemanager.services.OrderService;
import com.tannd.commercemanager.services.UserService;
import com.tannd.commercemanager.validation.email.EmailExistedValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
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
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @Autowired
    private EmailExistedValidator emailExistedValidator;

    @PostMapping("/emailcheck")
    public ResponseEntity<?> emailCheck(@RequestBody Map<String, Object> inputData) {
        String email = (String)inputData.get("email");
        Boolean bool = emailExistedValidator.emailExists(email);
        return ResponseEntity.status(HttpStatus.OK).body(bool);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        var entity = getService().findEntityById(id);
        var response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
        return ResponseEntity.ok().body(new CustomResponse(200, "Get User By Id " + id,
                response));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
        System.out.println(dto.toString());
        var entity = getService().findEntityById(id);
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        getService().save(entity);
        System.out.println(entity.toString());
        var response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
        return ResponseEntity.ok().body(new CustomResponse(200, "Update User By Id " + id,
                response));
    }
}
