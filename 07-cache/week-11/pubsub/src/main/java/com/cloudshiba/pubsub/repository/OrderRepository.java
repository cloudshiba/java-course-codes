package com.cloudshiba.pubsub.repository;

import com.cloudshiba.pubsub.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {
    void insert(Order order);

    void update(Order order);

    int getTotal(Order order);

    List<Order> findList(@Param("order") Order order, @Param("offset") int offset, @Param("limit")int limit);

    void insertBatch(List<Order> list);
}
