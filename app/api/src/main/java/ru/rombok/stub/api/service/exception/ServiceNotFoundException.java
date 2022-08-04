package ru.rombok.stub.api.service.exception;

import java.util.UUID;

public class ServiceNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Service with uuid %s not found";

    public ServiceNotFoundException(UUID uuid) {
        super(String.format(MESSAGE, uuid));
    }
}
