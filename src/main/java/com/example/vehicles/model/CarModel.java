package com.example.vehicles.model;

import com.example.vehicles.entity.CarEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarModel extends ResponseModel {

    @JsonProperty("cars")
    List<CarEntity> cars;

    @JsonProperty("car")
    CarEntity car;

    public List<CarEntity> getCars() {
        return cars;
    }

    public void setCars(List<CarEntity> cars) {
        this.cars = cars;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "car=" + car +
                ", cars=" + cars +
                '}';
    }
}
