package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.EmployeeDTO;
import com.tannd.commercemanager.dto.UserDTO;
import com.tannd.commercemanager.maper.EmployeeMapper;
import com.tannd.commercemanager.maper.UserMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.message.response.CustomResponse;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    @GetMapping("/count-in-current-month")
    public ResponseEntity<?> countUserCreatedInMonth() {
        return ResponseEntity.ok().body(new CustomResponse(200, "Count Employee Registration in current month",
                getService().countEmployeeAddedInMonth()));
    }

    @GetMapping("/count-in-current-year")
    public ResponseEntity<?> countUserCreatedInYear() {
        List<Long> list = new ArrayList<>();
        for (int i=0; i<12; i++) {
            Long count = getService().countEmployeeAddedInOneMonth(i+1);
            list.add(count);
        }
        return ResponseEntity.ok().body(new CustomResponse(200, "Count Employee Registration in current month",
                list));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody EmployeeDTO dto) {
        System.out.println(dto.toString());
        var entity = getService().findEntityById(id);
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        getService().save(entity);
        System.out.println(entity.toString());
        var response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
        return ResponseEntity.ok().body(new CustomResponse(200, "Update Employee By Id " + id,
                response));
    }
}
