package com.dataart.edu.ms.domain;

public enum TransactionType {
    DISABLED("disabled"),
    ESTABLISH_PRODUCT("establishProduct"),
    RELEASE_PRODUCT("establishProduct");

    public String type;

    TransactionType(String type) {
        this.type = type;
    }
}
