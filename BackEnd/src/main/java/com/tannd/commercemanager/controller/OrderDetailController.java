package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.dto.OrderDetailDTO;
import com.tannd.commercemanager.maper.OrderDetailMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.model.Order;
import com.tannd.commercemanager.model.OrderDetail;
import com.tannd.commercemanager.services.OrderDetailService;
import com.tannd.commercemanager.services.OrderService;
import com.tannd.commercemanager.services.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order-detail")
public class OrderDetailController extends
        AbstractController<OrderDetailService, OrderDetailMapper, OrderDetailDTO, OrderDetail> {

    @Autowired
    OrderDetailService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public OrderDetailService getService() {
        initService();
        return service;
    }

    private OrderDetailMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public OrderDetailMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAllOrders() {
        return getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        return getById(id);
    }

    @GetMapping("/by-order-id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getOrderByOrderId(@PathVariable Long id) {
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Order Detail By Order Id",
                (getService().getByOrderId(id))));
    }

    @Autowired
    ProductDetailService productDetailService;

    @Autowired
    OrderService orderService;

    @Transactional
    @PostMapping("/add-detail")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createOrderDetail(@RequestBody OrderDetailDTO dto) {
        System.out.println("Save Order Detail Begin...");
        OrderDetail entity = new OrderDetail();
        var order = orderService.findEntityById(dto.getOrderId());
        entity.setOrder(order);
        var productDetail = productDetailService
                .findEntityById(dto.getProductDetailId());
        var listSameProductDetail = productDetailService.getProductDetailEntityByProductIdAndDetail(
                productDetail.getImportVoucherDetail().getProduct().getId(),
//                dto.getProductId(),
                productDetail.getRam(), productDetail.getColor(), productDetail.getMemmory()
        );
        listSameProductDetail.get(0).setStatus(true);
        entity.setProductDetail(listSameProductDetail.get(0));
        try {
            System.out.println("save begin");
            entity = getService().save(entity);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new CustomResponse(500, "Request not OK" +
                    "\nMessage: " + e.getMessage(), null));
        }
        var response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
        response.setProductId(dto.getProductId());
        return ResponseEntity.ok().body(new CustomResponse(200, "Request Post Order Detail Ok",
                response));
    }

    @Transactional
    @PostMapping("/add-list")
    public ResponseEntity<?> createList(@RequestBody List<OrderDetailDTO> list) {
        List<OrderDetailDTO> responseList = new ArrayList<>();
        try {
            list.forEach(dto -> {
                OrderDetail entity = new OrderDetail();
                var order = orderService.findEntityById(dto.getOrderId());
                entity.setOrder(order);
                var productDetail = productDetailService
                        .findEntityById(dto.getProductDetailId());
                var listSameProductDetail = productDetailService.getProductDetailEntityByProductIdAndDetail(
                        productDetail.getImportVoucherDetail().getProduct().getId(),
//                dto.getProductId(),
                        productDetail.getRam(), productDetail.getColor(), productDetail.getMemmory()
                );
                listSameProductDetail.get(0).setStatus(true);
                entity.setProductDetail(listSameProductDetail.get(0));
                entity = getService().save(entity);
                var response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
                response.setProductId(dto.getProductId());
                responseList.add(response);
            });
        } catch (Exception e) {
            return ResponseEntity.ok().body(new CustomResponse(500, "Request not OK" +
                    "\nMessage: " + e.getMessage(), null));
        }
        return ResponseEntity.ok().body(new CustomResponse(200, "Request Post Order Detail Ok",
                responseList));
    }
}
