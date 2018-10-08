package com.example.vehicles.model;

import com.example.vehicles.entity.TruckEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TruckModel extends ResponseModel {

    @JsonProperty("trucks")
    List<TruckEntity> trucks;

    @JsonProperty("truck")
    TruckEntity truck;

    public List<TruckEntity> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<TruckEntity> trucks) {
        this.trucks = trucks;
    }

    public TruckEntity getTruck() {
        return truck;
    }

    public void setTruck(TruckEntity truck) {
        this.truck = truck;
    }

    @Override
    public String toString() {
        return "TruckModel{" +
                "truck=" + truck +
                ", trucks=" + trucks +
                '}';
    }
}
