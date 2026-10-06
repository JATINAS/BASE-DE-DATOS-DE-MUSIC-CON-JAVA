package com.ebac.modulo39.controller;

import org.springframework.http.ResponseEntity;

//public class ResponseWrapper<T> {
//    private final boolean success;
//    private final String message;
//    private final ResponseEntity<T> responseEntity;
//
//    public ResponseWrapper(boolean success, String message, ResponseEntity<T> responseEntity) {
//        this.success = success;
//        this.message = message;
//        this.responseEntity = responseEntity;
//    }
//
//    public boolean isSuccess() {
//        return success;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public ResponseEntity<T> getResponseEntity() {
//        return responseEntity;
//    }
//}

public class ResponseWrapper<T> {

    private final boolean success;
    private final String message;
    private final T data;

    public ResponseWrapper(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
