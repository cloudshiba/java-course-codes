package com.cloudshiba.shardingspherejdbc.repository;

import com.cloudshiba.shardingspherejdbc.model.EcOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EcOrderRepository extends JpaRepository<EcOrder, Long> {
}
