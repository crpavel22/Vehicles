package com.example.vehicles.service;

import com.example.vehicles.entity.DroneEntity;
import com.example.vehicles.utils.SearchCriteria;

import java.util.List;

public interface DroneService {

    List<DroneEntity> getAll(List<SearchCriteria> params);

    DroneEntity get(Integer id);

    boolean add(DroneEntity droneEntity);

    void remove(Integer id);

    DroneEntity update(DroneEntity droneEntity);
}
