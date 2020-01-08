package com.dataart.edu.ms.utils;

import java.util.UUID;

public class IDGenerator {

    public static synchronized String nextId() {
        return UUID.randomUUID().toString();
    }

}
