package com.ltp.sensors.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SensorConflictException.class)
    public ResponseEntity error(final SensorConflictException sensorConflictException) {
        final ErrorMessage errorMessage = new ErrorMessage(sensorConflictException.getError());
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @AllArgsConstructor
    @Getter
    private class ErrorMessage {
        private String msg;
    }

}
