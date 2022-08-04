package ru.rombok.stub.impl.common;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidUtils {

    public UUID randomUuid() {
        return UUID.randomUUID();
    }
}
