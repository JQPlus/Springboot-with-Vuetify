package com.explore.galaxy.basic.utils.responseBody;

import java.io.Serializable;

public interface IBasicResponse extends Serializable {
    void setData(Object data);

    void setResponseType(EResponseType responseType);

    void setMessage(String message);
}
