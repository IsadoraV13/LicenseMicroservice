package com.example.licensemicroservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpEntity;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ResponseEntity<T> {
    private T data;
    private String message;
    private int response_code;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }
}
