package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.EmployeeDTO;
import com.tannd.commercemanager.dto.UserDTO;
import com.tannd.commercemanager.maper.EmployeeMapper;
import com.tannd.commercemanager.maper.UserMapper;
import com.tannd.commercemanager.model.Employee;
import com.tannd.commercemanager.model.User;
import com.tannd.commercemanager.services.EmployeeService;
import com.tannd.commercemanager.services.UserService;
import com.tannd.commercemanager.validation.email.EmailExistedValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController extends AbstractController<EmployeeService, EmployeeMapper,
        EmployeeDTO, Employee> {

    @Autowired
    EmployeeService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public EmployeeService getService() {
        initService();
        return service;
    }

    private EmployeeMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public EmployeeMapper getMapper() {
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

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getEmplAll() {
        return getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmplById(@PathVariable Long id) {
        return getById(id);
    }
}
