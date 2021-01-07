package com.explore.galaxy.basic.utils.responseBody;

import javax.validation.constraints.Null;

public class DataResponse implements IBasicResponse {
    private Object data;
    private EResponseType responseType;
    private String message;

    public Object getData() {
        return data;
    }

    public EResponseType getResponseType() {
        return responseType;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public void setResponseType(EResponseType responseType) {
        this.responseType = responseType;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccessData(Object data,@Null String message) {
        this.setData(data);
        this.setResponseType(EResponseType.success);
        this.setMessage(message);
    }

    public void setErrorData(Object data,@Null  String message) {
        this.setData(data);
        this.setResponseType(EResponseType.error);
        this.setMessage(message);
    }

    public void setWarningData(Object data,@Null  String message) {
        this.setData(data);
        this.setResponseType(EResponseType.warning);
        this.setMessage(message);
    }
}
