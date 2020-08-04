package com.explore.galaxy.basic.utils.UUIDUtils;

import java.util.UUID;

public class UUIDUtils {
    public static String init() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
