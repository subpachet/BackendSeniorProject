package com.seniorproject.project.model.responsemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ResponseModel<T> implements Serializable {
    private StatusModel status;
    @JsonProperty("data")
    private T dataObject;

    public ResponseModel(int code){
        status = new StatusModel();
        status.setCode(code);
    }

    public void setCode(int code){
        status.setCode(code);
    }

    @JsonProperty("data")
    public ResponseModel<T> setDataObj(final T dataObj) {
        this.dataObject = dataObj;
        return this;
    }

}
