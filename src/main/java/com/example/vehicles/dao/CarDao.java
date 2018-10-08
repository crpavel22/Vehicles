package com.example.vehicles.dao;

import com.example.vehicles.entity.CarEntity;
import com.example.vehicles.utils.SearchCriteria;
import org.springframework.data.jpa.domain.Specifications;

import java.util.List;

public interface CarDao {

    List<CarEntity> getAll();

    CarEntity get(Integer id);

    void add(CarEntity carEntity);

    void remove(CarEntity carEntity);

    void alter(CarEntity carEntity);

    List<CarEntity> findAll(List<SearchCriteria> params);
}
