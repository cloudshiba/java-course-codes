package com.cloudshiba.pubsub.service;

import com.cloudshiba.pubsub.entity.Order;
import com.cloudshiba.pubsub.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private final static int limit = 50;

    public void dealOrderSingleThread(final Order order) {
        long beginTime = System.currentTimeMillis();

        int total = orderRepository.getTotal(order);

        int offset = 0;
        do {
            List<Order> orders = orderRepository.findList(order, offset, limit);
            orders.forEach(item -> {
                item.setStatus(2);
                orderRepository.update(item);
            });
            offset = offset + limit;
        }while(offset < total);

        long endTime = System.currentTimeMillis();
        System.out.println("Order deal spend time:" + (endTime - beginTime));
    }

}
