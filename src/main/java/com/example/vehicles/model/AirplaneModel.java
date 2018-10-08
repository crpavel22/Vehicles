package com.example.vehicles.model;

import com.example.vehicles.entity.AirplaneEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirplaneModel extends ResponseModel {

    @JsonProperty("airplanes")
    List<AirplaneEntity> airplanes;

    @JsonProperty("airplane")
    AirplaneEntity airplane;

    public List<AirplaneEntity> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(List<AirplaneEntity> airplanes) {
        this.airplanes = airplanes;
    }

    public AirplaneEntity getAirplane() {
        return airplane;
    }

    public void setAirplane(AirplaneEntity airplane) {
        this.airplane = airplane;
    }

    @Override
    public String toString() {
        return "AirplaneModel{" +
                "airplane=" + airplane +
                ", airplanes=" + airplanes +
                '}';
    }
}
