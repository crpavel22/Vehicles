package com.example.vehicles.dao;

import com.example.vehicles.entity.AirplaneEntity;
import com.example.vehicles.utils.SearchCriteria;

import java.util.List;

public interface AirplaneDao {

    List<AirplaneEntity> getAll();

    AirplaneEntity get(Integer id);

    void add(AirplaneEntity airplaneEntity);

    void remove(AirplaneEntity airplaneEntity);

    void alter(AirplaneEntity airplaneEntity);

    List<AirplaneEntity> findAll(List<SearchCriteria> params);
    
}
