package com.example.vehicles.model;

import com.example.vehicles.entity.BoatEnty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoatModel extends ResponseModel {

    @JsonProperty("boats")
    List<BoatEnty> boats;

    @JsonProperty("boat")
    BoatEnty boat;

    public List<BoatEnty> getBoats() {
        return boats;
    }

    public void setBoats(List<BoatEnty> boats) {
        this.boats = boats;
    }

    public BoatEnty getBoat() {
        return boat;
    }

    public void setBoat(BoatEnty boat) {
        this.boat = boat;
    }

    @Override
    public String toString() {
        return "BoatModel{" +
                "boat=" + boat +
                ", boats=" + boats +
                '}';
    }
}
