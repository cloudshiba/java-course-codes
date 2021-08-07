package com.clooudshiba.dynamicdatasource.service;

import com.clooudshiba.dynamicdatasource.datasource.TargetDataSource;
import com.clooudshiba.dynamicdatasource.model.EcOrder;
import com.clooudshiba.dynamicdatasource.repository.EcOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcOrderService {
    private final EcOrderRepository ecOrderRepository;

    public EcOrderService(EcOrderRepository ecOrderRepository) {
        this.ecOrderRepository = ecOrderRepository;
    }

    @TargetDataSource("SECONDARY")
    public List<EcOrder> findAll() {
        return ecOrderRepository.findAll();
    }

    @TargetDataSource("PRIMARY")
    public void create(EcOrder ecOrder) {
        ecOrderRepository.save(ecOrder);
    }
}
