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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order-detail")
public class OrderDetailController extends AbstractController<OrderDetailService, OrderDetailMapper, OrderDetailDTO, OrderDetail> {

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

    @Autowired
    ProductDetailService productDetailService;

    @Autowired
    OrderService orderService;

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<?> createOrderDetail(@RequestBody OrderDetailDTO dto) {
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
}
