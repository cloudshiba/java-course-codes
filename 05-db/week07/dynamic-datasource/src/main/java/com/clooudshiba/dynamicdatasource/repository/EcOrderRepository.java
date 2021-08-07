package com.clooudshiba.dynamicdatasource.repository;

import com.clooudshiba.dynamicdatasource.model.EcOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EcOrderRepository extends JpaRepository<EcOrder, Long> {
    List<EcOrder> findAll();
}
