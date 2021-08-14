package com.cloudshiba.shardingsphereproxy.service;

import com.cloudshiba.shardingsphereproxy.dao.EcOrderDao;
import com.cloudshiba.shardingsphereproxy.entity.EcOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Slf4j
public class EcOrderService {
    private final EcOrderDao ecOrderDao;

    public EcOrderService(EcOrderDao ecOrderDao) {
        this.ecOrderDao = ecOrderDao;
    }

    public List<EcOrder> findAll() {
        return ecOrderDao.findAll();
    }

    public Optional<EcOrder> findOne(Long orderId) {
        return ecOrderDao.findOne(orderId);
    }

    public EcOrder create(EcOrder order) {
        return ecOrderDao.create(order);
    }

    public EcOrder update(EcOrder order) {
        return ecOrderDao.update(order);
    }

    public boolean delete(Long orderId) {
        return ecOrderDao.delete(orderId);
    }
}
