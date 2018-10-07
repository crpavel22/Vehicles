package com.example.vehicles.entity;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.Entity;

@Entity
@JsonRootName("Boat")
public class BoatEnty extends VehicleEntity {

    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "BoatEnty{" +
                "size='" + super.toString() + '\'' +
                "size='" + size + '\'' +
                '}';
    }
}
