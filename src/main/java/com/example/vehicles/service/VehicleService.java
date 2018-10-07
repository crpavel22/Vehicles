package com.example.vehicles.service;

import com.example.vehicles.entity.VehicleEntity;

import java.util.List;

public interface VehicleService {

    List<VehicleEntity> getAll();

    List<VehicleEntity> getByType(String type);

    void add(VehicleEntity vehicleEntity);

    void removeLast();
}
