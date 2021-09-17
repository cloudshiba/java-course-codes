package com.cloudshoba.mymq.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageOfferReq {
    @NotBlank(message = "message 不能為空")
    private String message;

    @NotBlank(message = "topicName 不能為空")
    private String topicName;
}
