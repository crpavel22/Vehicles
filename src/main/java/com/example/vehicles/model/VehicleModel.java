package com.example.vehicles.model;

import com.example.vehicles.entity.VehicleEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleModel extends ResponseModel {

    @JsonProperty("Vehicles")
    List<VehicleEntity> vehicles;

    public List<VehicleEntity> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleEntity> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "VehicleModel{" +
                "vehicles=" + vehicles +
                '}';
    }
}
