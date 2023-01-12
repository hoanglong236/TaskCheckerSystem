package org.swing.app.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * TODO: comment this
 */
public class ControllerResponse {

    public static final byte RESPONSE_TYPE_SUCCESS = 0;
    public static final byte RESPONSE_TYPE_ERROR = 1;

    private byte responseType;
    private String errorMessage;

    private final Map<String, Object> mapData;

    public ControllerResponse() {
        this.responseType = RESPONSE_TYPE_SUCCESS;
        this.mapData = new HashMap<>();
        this.errorMessage = "";
    }

    public void setResponseType(byte responseType) {
        switch (responseType) {
            case RESPONSE_TYPE_SUCCESS:
            case RESPONSE_TYPE_ERROR:
                this.responseType = responseType;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public byte getResponseType() {
        return responseType;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Map<String, Object> getMapData() {
        return mapData;
    }

    public void putData(String key, Object value) {
        this.mapData.put(key, value);
    }

    public Optional<Object> getData(String key) {
        return Optional.ofNullable(this.mapData.get(key));
    }
}
