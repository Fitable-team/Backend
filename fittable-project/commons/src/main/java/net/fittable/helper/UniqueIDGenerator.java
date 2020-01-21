package net.fittable.helper;

import java.util.UUID;

public class UniqueIDGenerator {

    public static String generateEntityId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
