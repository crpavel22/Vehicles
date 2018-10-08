package com.example.vehicles.dao;

import com.example.vehicles.entity.TruckEntity;
import com.example.vehicles.utils.SearchCriteria;

import java.util.List;

public interface TruckDao {

    List<TruckEntity> getAll();

    TruckEntity get(Integer id);

    void add(TruckEntity truckEntity);

    void remove(TruckEntity truckEntity);

    void alter(TruckEntity truckEntity);

    List<TruckEntity> findAll(List<SearchCriteria> params);
}
