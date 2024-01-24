package com.example.licensemicroservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpEntity;

import java.util.LinkedHashMap;

/*
This is a model I found and tried to implement, because speaking to Noe, I understood that it was best not to return
the json object itself to the controller
I Struggled a lot with:
1. figuring out how to only pass the desired data from the json object to it. I circumvented it in the Service but I
know it's not the right way.
2. figuring out how to set the response code
3. what's best practice for messages
4. I didn't know if I needed to have a toString() method here as I didn't know how to handle the generic type T
 */

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
