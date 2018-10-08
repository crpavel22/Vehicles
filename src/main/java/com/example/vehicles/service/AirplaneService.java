package com.example.vehicles.service;

import com.example.vehicles.entity.AirplaneEntity;
import com.example.vehicles.utils.SearchCriteria;

import java.util.List;

public interface AirplaneService {
    List<AirplaneEntity> getAll(List<SearchCriteria> params);

    AirplaneEntity get(Integer id);

    boolean add(AirplaneEntity airplaneEntity);

    void remove(Integer id);

    AirplaneEntity update(AirplaneEntity airplaneEntity);
}
