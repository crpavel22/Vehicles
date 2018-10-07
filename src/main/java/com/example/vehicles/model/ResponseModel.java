package com.example.vehicles.model;

import com.example.vehicles.entity.VehicleEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseModel {

    @JsonProperty("Vehicles")
    List<VehicleEntity> result;

    @Override
    public String toString() {
        return "ResponseModel{" +
                "result=" + result +
                '}';
    }

    public List<VehicleEntity> getResult() {
        return result;
    }

    public void setResult(List<VehicleEntity> result) {
        this.result = result;
    }
}
