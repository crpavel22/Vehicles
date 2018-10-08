package com.example.vehicles.service;

import com.example.vehicles.entity.TruckEntity;
import com.example.vehicles.utils.SearchCriteria;

import java.util.List;

public interface TruckService {

    List<TruckEntity> getAll(List<SearchCriteria> params);

    TruckEntity get(Integer id);

    boolean add(TruckEntity truckEntity);

    void remove(Integer id);

    TruckEntity update(TruckEntity truckEntity);
}
