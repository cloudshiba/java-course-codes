package com.cloudshiba.shardingspherejdbc.service;

import com.cloudshiba.shardingspherejdbc.model.EcOrder;
import com.cloudshiba.shardingspherejdbc.repository.EcOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcOrderService {
    private final EcOrderRepository ecOrderRepository;

    public EcOrderService(EcOrderRepository ecOrderRepository) {
        this.ecOrderRepository = ecOrderRepository;
    }

    public List<EcOrder> findAll() {
        return ecOrderRepository.findAll();
    }

    public void create(EcOrder ecOrder) {
        ecOrderRepository.save(ecOrder);
    }
}

