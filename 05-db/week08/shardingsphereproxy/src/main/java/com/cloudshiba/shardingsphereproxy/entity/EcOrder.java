package com.cloudshiba.shardingsphereproxy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EcOrder {
    private Long orderId;
    private Long userId;
    private BigDecimal totalPrice;
    private Long createTime;
    private Long updateTime;
}
