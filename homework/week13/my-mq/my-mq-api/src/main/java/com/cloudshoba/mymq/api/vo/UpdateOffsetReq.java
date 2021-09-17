package com.cloudshoba.mymq.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOffsetReq {
    @NotBlank(message = "groupName 不能為空")
    private String groupName;

    @NotBlank(message = "topicName 不能為空")
    private String topicName;

    @NotNull(message = "offset 不能為空")
    private Integer offset;
}
