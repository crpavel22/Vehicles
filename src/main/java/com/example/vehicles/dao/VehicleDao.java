package com.example.vehicles.dao;

import com.example.vehicles.entity.VehicleEntity;

import java.util.List;

public interface VehicleDao {

    List<VehicleEntity> getAll();

    List<VehicleEntity> getByType(String type);

    VehicleEntity selectLast();

    void add(VehicleEntity vehicleEntity);

    void remove(VehicleEntity vehicleEntity);
}
