package com.tannd.commercemanager.controller;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.repository.OrderRepository;
import com.tannd.commercemanager.repository.ProductRepository;
import com.tannd.commercemanager.services.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/statistic")
public class StatisticController {


    @Autowired
    UserService userService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    ProductDetailService productDetailService;

    @GetMapping("/account/year")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> countAccountInYear() {
        Map<String, List> response = new HashMap<>();
        List<Long> listUser = new ArrayList<>();
        List<Long> listEmployee = new ArrayList<>();
        for (int i=0; i<12; i++) {
            listUser.add(userService.countUserCreatedInOneMonth(i+1));
            listEmployee.add(employeeService.countEmployeeAddedInOneMonth(i+1));
        }
        response.put("listUser", listUser);
        response.put("listEmployee", listEmployee);
        return ResponseEntity.ok().body(new CustomResponse(200, "Count User and Employee In Current Year",
            response));
    }

    @GetMapping("/order/year")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> countOrderInYear() {
        var list = orderService.getOrdersInCurrentYear();
        return ResponseEntity.ok().body(new CustomResponse(200, "Count Order In Current Year",
                list));
    }

    @GetMapping("/order/month")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> countOrderInMonth() {
        var list = orderService.getOrdersInCurrentMonth();
        return ResponseEntity.ok().body(new CustomResponse(200, "Count Order In Current Year",
                list));
    }

    @GetMapping("/inventory/month")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> countInventoryThisMonth() {
        Map<String, List> response = new HashMap<>();

        return ResponseEntity.ok().body(new CustomResponse(200, "Count Order In Current Year",
                response));
    }

    @GetMapping("/inventory/year")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> countInventoryThisYear() {
        Map<String, List> response = new HashMap<>();
        List<Long> listImport = new ArrayList<>();
        List<Long> listExport = new ArrayList<>();
        for (int i=0; i<12; i++) {
            listImport.add(productDetailService.countImportProductInMonth(i+1));
            listExport.add(orderDetailService.countExportProductInMonth(i+1));
        }
        response.put("listImport", listImport);
        response.put("listExport", listExport);
        return ResponseEntity.ok().body(new CustomResponse(200, "Count Inventory In Current Year",
                response));
    }

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/revenue/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getRevenueAllTime() {
        return ResponseEntity.ok().body(new CustomResponse(200, "Calculate Revenue All Time",
                orderRepository.calculateRevenueAllTime()));
    }

    @GetMapping("/revenue/month")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getRevenueThisMonth() {
        return ResponseEntity.ok().body(new CustomResponse(200, "Calculate Revenue This Month",
                orderRepository.calculateRevenueThisMonth()));
    }

    @GetMapping("/revenue/year")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getRevenueThisYear() {
        return ResponseEntity.ok().body(new CustomResponse(200, "Calculate Revenue This Year",
                orderRepository.calculateRevenueThisYear()));
    }

    @GetMapping("/order/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOrderNumAllTimes() {
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Number of Orders All Times",
                orderRepository.findAll().stream().count()));
    }

    @GetMapping("/product/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProductNumAllTimes() {
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Number of Products All Times",
                productRepository.findAll().stream().count()));
    }

    @GetMapping("/user/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUserNumAllTimes() {
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Number of Users All Times",
                userService.findAll().stream().count()));
    }
}
