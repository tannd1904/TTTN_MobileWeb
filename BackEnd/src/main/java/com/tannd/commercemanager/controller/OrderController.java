package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.InvoiceDTO;
import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.maper.OrderMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.model.Invoice;
import com.tannd.commercemanager.model.Order;
import com.tannd.commercemanager.services.*;
import com.tannd.commercemanager.template.EmailObject;
import com.tannd.commercemanager.template.EmailTemplate;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

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

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllOrders() {
        return getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        return getById(id);
    }

    @GetMapping("/get-by-user-id/{id}")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public ResponseEntity<?> getOrderByUserId(@PathVariable Long id) {
        return ResponseEntity.ok().body(new CustomResponse(200, "Request Get Order By User Id Ok"
                , getService().getByUserId(id)));
    }

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @GetMapping("/test")
    public ResponseEntity<?> testSendEmail() throws MessagingException, TemplateException, IOException {
        EmailObject email = new EmailObject();
        email.setEmail("tannd1904@gmail.com");
        email.setName("Tân Nguyễn");
        email.setUsername("tannd1904");
        try {
            emailService.sendEmail(email);
            return ResponseEntity.ok().body(new CustomResponse(200,"Test Send Email",
                    "OK"));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new CustomResponse(200,"Test Send Email Not OK",
                    e.getMessage()));
        }
    }

    @GetMapping("/test2")
    public ResponseEntity<?> testSendEmail2() throws Exception {
        var order = getService().findById(22);
        try {
            emailService.sendEmailWithAttachment("Order Success", order.toString(), "tannd1904@gmail.com");
            return ResponseEntity.ok().body(new CustomResponse(200,"Test Send Email",
                    "OK"));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new CustomResponse(200,"Test Send Email Not OK",
                    e.getMessage()));
        }
    }

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

    @Autowired
    EmployeeService employeeService;

    @Transactional
    @PutMapping("/confirm/{id}/{employeeId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> confirmOrder(@PathVariable Long id, @PathVariable Long employeeId) {
        var order = getService().findEntityById(id);
        order.setEmployee(employeeService.findEntityById(employeeId));
        order.setStatus(1);
        var response = getMapper().toDto(order, new CycleAvoidingMappingContext());
        return ResponseEntity.ok().body(new CustomResponse(200, "Request Confirm Order Ok"
                , response));
    }

    @Transactional
    @PutMapping("/receive/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> receiveOrder(@PathVariable Long id) {
        var order = getService().findEntityById(id);
        order.setStatus(2);
        var response = getMapper().toDto(order, new CycleAvoidingMappingContext());
        return ResponseEntity.ok().body(new CustomResponse(200, "Request Receive Order Ok"
                , response));
    }

    @Transactional
    @PutMapping("/cancel/{id}/{employeeId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id, @PathVariable Long employeeId) {
        var order = getService().findEntityById(id);
        order.setEmployee(employeeService.findEntityById(employeeId));
        order.setStatus(3);
        order.getListOrderDetails().stream().forEach(s -> {
            s.getProductDetail().setStatus(false);
        });
        var response = getMapper().toDto(order, new CycleAvoidingMappingContext());
        return ResponseEntity.ok().body(new CustomResponse(200, "Request Cancel Order Order Ok"
                , response));
    }

    @Transactional
    @PutMapping("/cancel/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> userCancelOrder(@PathVariable Long id) {
        var order = getService().findEntityById(id);
        order.setStatus(3);
        order.getListOrderDetails().stream().forEach(s -> {
            s.getProductDetail().setStatus(false);
        });
        var response = getMapper().toDto(order, new CycleAvoidingMappingContext());
        return ResponseEntity.ok().body(new CustomResponse(200, "Request Cancel Order Order Ok"
                , response));
    }
}
