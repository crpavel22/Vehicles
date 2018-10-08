package com.example.vehicles.dao;

import com.example.vehicles.entity.DroneEntity;
import com.example.vehicles.utils.SearchCriteria;

import java.util.List;

public interface DroneDao {

    List<DroneEntity> getAll();

    DroneEntity get(Integer id);

    void add(DroneEntity droneEntity);

    void remove(DroneEntity droneEntity);

    void alter(DroneEntity droneEntity);

    List<DroneEntity> findAll(List<SearchCriteria> params);
}
