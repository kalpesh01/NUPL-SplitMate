package com.splitmate.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSplitRequest {
    private Double shareAmount;
    private String paymentStatus;
}

