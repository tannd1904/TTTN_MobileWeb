package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.OrderDetailDTO;
import com.tannd.commercemanager.dto.ProductDetailDTO;
import com.tannd.commercemanager.maper.OrderDetailMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.OrderDetail;
import com.tannd.commercemanager.model.ProductDetail;
import com.tannd.commercemanager.repository.OrderDetailRepository;
import com.tannd.commercemanager.services.OrderDetailService;
import com.tannd.commercemanager.services.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ServiceHelper
public class OrderDetailServiceImpl extends AbstractServiceImpl<OrderDetailRepository, OrderDetailMapper, OrderDetailDTO, OrderDetail>
        implements OrderDetailService {

    @Autowired
    OrderDetailRepository thisRepository;

    private OrderDetailMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public OrderDetailRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public OrderDetailMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @Override
    public List<OrderDetailDTO> getByOrderId(Long id) {
        List<OrderDetail> listEntity = getRepository()
                .findByOrderId(id);
        System.out.println(listEntity.toString());
        List<OrderDetailDTO> list = new ArrayList<>();
        listEntity.stream().forEach(product -> {
            var temp = getMapper().toDto(product, new CycleAvoidingMappingContext());
            temp.setProductId(product.getProductDetail().getImportVoucherDetail().getProduct().getId());
            list.add(temp);
        });
        return list;
    }

    @Override
    public Long countExportProductThisMonth() {
        return getRepository().countExportProductThisMonth();
    }

    @Override
    public Long countExportProductInMonth(Integer month) {
        return getRepository().countExportProductInMonth(month);
    }
}
