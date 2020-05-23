package com.sectorseven.service.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author KUMAR V S
 */
public class StudentAppResponse<T> {

    String status;
    String responseCode;
    String message;
    Map<String, T> all = new HashMap<>();
    List<String> keyWords = new ArrayList<>();
    T entity;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Map<String, T> getAll() {
        return all;
    }

    public void setAll(Map<String, T> all) {
        this.all = all;
    }

    public String getStatus() {
        return status;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

}
