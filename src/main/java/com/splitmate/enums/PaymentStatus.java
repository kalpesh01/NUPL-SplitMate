package com.splitmate.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PaymentStatus {

    PENDING(1, "Pending"),
    PAID(2, "Paid"),
    PARTIALLY_PAID(3, "Partially Paid"),
    FAILED(4, "Failed"),
    CANCELLED(5, "Cancelled");

    private final int id;
    private final String value;

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}


