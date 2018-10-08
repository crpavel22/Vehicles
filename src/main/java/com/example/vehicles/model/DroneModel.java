package com.example.vehicles.model;

import com.example.vehicles.entity.DroneEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DroneModel extends ResponseModel {

    @JsonProperty("drones")
    List<DroneEntity> drones;

    @JsonProperty("drone")
    DroneEntity drone;

    public List<DroneEntity> getDrones() {
        return drones;
    }

    public void setDrones(List<DroneEntity> drones) {
        this.drones = drones;
    }

    public DroneEntity getDrone() {
        return drone;
    }

    public void setDrone(DroneEntity drone) {
        this.drone = drone;
    }

    @Override
    public String toString() {
        return "DroneModel{" +
                "drone=" + drone +
                ", drones=" + drones +
                '}';
    }
}
