package com.ltp.sensors.exception;

import lombok.Getter;

public class SensorConflictException extends RuntimeException {

    @Getter
    private final String error;

    public SensorConflictException(final String error) {
        super(error);
        this.error = error;
    }
}
