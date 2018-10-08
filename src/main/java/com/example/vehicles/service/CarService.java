package com.example.vehicles.service;

import com.example.vehicles.entity.CarEntity;
import com.example.vehicles.utils.SearchCriteria;

import java.util.List;

public interface CarService {

    List<CarEntity> getAll(List<SearchCriteria> params);

    CarEntity get(Integer id);

    boolean add(CarEntity carEntity);

    void remove(Integer id);

    CarEntity update(CarEntity carEntity);
}
