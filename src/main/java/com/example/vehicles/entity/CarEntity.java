package com.example.vehicles.entity;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE_CAR")
@JsonRootName("Car")
public class CarEntity extends VehicleEntity{

    @Column(name = "COLOR")
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "color='" + super.toString() + '\'' +
                "color='" + color + '\'' +
                '}';
    }
}
