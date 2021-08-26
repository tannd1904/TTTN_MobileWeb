package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.InvoiceDTO;
import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.maper.OrderMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.model.Invoice;
import com.tannd.commercemanager.model.Order;
import com.tannd.commercemanager.services.InvoiceService;
import com.tannd.commercemanager.services.OrderService;
import com.tannd.commercemanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController extends AbstractController<OrderService, OrderMapper, OrderDTO, Order> {

    @Autowired
    OrderService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public OrderService getService() {
        initService();
        return service;
    }

    private OrderMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public OrderMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @Autowired
    UserService userService;

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderDTO dto) {
        Order entity = new Order();
        entity = getMapper().toEntity(dto, new CycleAvoidingMappingContext());
        entity.setUser(userService.findEntityById(dto.getUserId()));
        System.out.println(entity);
        try {
            System.out.println("123");
            entity = getService().save(entity);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new CustomResponse(500, "Request Not Ok" +
                    "\nMessage: " + e.getMessage(), null));
        }
        OrderDTO response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
        return ResponseEntity.ok().body(new CustomResponse(200, "Request Post Ok"
                , response));
    }
}
