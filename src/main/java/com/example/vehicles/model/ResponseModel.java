package com.example.vehicles.model;

import com.example.vehicles.entity.VehicleEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("Result")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel {

    @JsonProperty("Message")
   private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
