package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.OrderDTO;
import com.tannd.commercemanager.maper.OrderMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.Order;
import com.tannd.commercemanager.repository.OrderRepository;
import com.tannd.commercemanager.services.OrderService;
import com.tannd.commercemanager.services.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ServiceHelper
public class OrderServiceImpl extends AbstractServiceImpl<OrderRepository, OrderMapper, OrderDTO, Order>
        implements OrderService {
    @Autowired
    OrderRepository thisRepository;

    private OrderMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public OrderRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public OrderMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @Override
    public List<OrderDTO> getByUserId(Long id) {
        var listEntity = getRepository().findAllByUserId(id);
        List<OrderDTO> list = new ArrayList<>();
        listEntity.stream().forEach(product -> {
            list.add(getMapper().toDto(product, new CycleAvoidingMappingContext()));
        });
        return list;
    }

    @Override
    public List<Number> getOrdersInCurrentYear() {
        List<Order> list = getRepository().findOrdersInCurrentYear();
        List<Number> response = new ArrayList<>();
        for (int i=0; i<4; i++) {
            int finalI = i;
            response.add(list.stream().filter(s -> s.getStatus().intValue() == finalI)
                    .collect(Collectors.toList()).stream().count());
        }
        return response;
    }

    @Override
    public List<Number> getOrdersInCurrentMonth() {
        List<Order> list = getRepository().findOrdersInCurrentMonth();
        List<Number> response = new ArrayList<>();
        for (int i=0; i<4; i++) {
            int finalI = i;
            response.add(list.stream().filter(s -> s.getStatus().intValue() == finalI)
                    .collect(Collectors.toList()).stream().count());
        }
        return response;
    }
}
