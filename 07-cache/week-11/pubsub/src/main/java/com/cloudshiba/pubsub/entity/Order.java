package com.cloudshiba.pubsub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class Order {
    private long id;

    private String no;

    private long productId;

    private int status;

    private Instant createTime;

    public Order(String no, long productId, int status) {
        this.no = no;
        this.productId = productId;
        this.status = status;
        this.createTime = Instant.now();
    }
}
