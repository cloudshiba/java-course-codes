package com.cloudshoba.mymq.api.feign;

import com.cloudshoba.mymq.api.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "mq-broker", url = "http://localhost:8080/mq/broker")
public interface MyMqBrokerFeignClient {
    /**
     * MQ 資訊總覽
     */
    @GetMapping("overview")
    MqOverviewVo prettyOverview();

    /**
     * 創建消費者群組
     */
    @PostMapping("consumer-group/create")
    void createConsumerGroup(@RequestBody @Valid CreateConsumerGroupReq req);

    /**
     * 生產訊息
     */
    @PostMapping("message/offer")
    boolean offerMessage(@RequestBody @Valid MessageOfferReq req);

    /**
     * 消費訊息
     */
    @PostMapping("message/pull")
    MessageResp<String> pullMessage(@RequestBody @Valid MessagePullReq req);

    /**
     * 消費訊息並自動更新偏移量
     */
    @PostMapping("message/simple-pull")
    MessageResp<String> simplePullMessage(@RequestBody MessagePullReq req);

    /**
     * 訊息消費偏移量更新
     */
    @PostMapping("message/offset/update")
    boolean updateOffset(@RequestBody @Valid UpdateOffsetReq req);
}
