package com.cloudshiba.mymq.server.controller;

import com.cloudshiba.mymq.server.service.IMqBrokerService;
import com.cloudshoba.mymq.api.vo.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mq/broker")
public class MyMqServerController {
    private final IMqBrokerService mqService;

    public MyMqServerController(IMqBrokerService mqService) {
        this.mqService = mqService;
    }

    @GetMapping("overview")
    public MqOverviewVo prettyOverview() {
        return mqService.prettyOverview();
    }

    @PostMapping("consumer-group/create")
    public void createConsumerGroup(@RequestBody CreateConsumerGroupReq req) {
        mqService.createConsumerGroup(req.getGroupName(), req.getTopicName());
    }

    @PostMapping("message/offer")
    public boolean offerMessage(@RequestBody MessageOfferReq req) {
        return mqService.offerMessage(req.getTopicName(), req.getMessage());
    }

    @PostMapping("message/pull")
    public MessageResp<String> pullMessage(@RequestBody MessagePullReq req) {
        return mqService.pullMessage(req.getGroupName(), req.getTopicName());
    }

    @PostMapping("message/simple-pull")
    public MessageResp<String> simplePullMessage(@RequestBody MessagePullReq req) {
        return mqService.simplePullMessage(req.getGroupName(), req.getTopicName());
    }

    @PostMapping("message/offset/update")
    public Boolean updateOffset(@RequestBody UpdateOffsetReq req) {
        return mqService.updateOffset(req.getGroupName(), req.getTopicName(), req.getOffset());
    }
}
