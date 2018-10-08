package com.example.vehicles.dao;

import com.example.vehicles.entity.BoatEnty;
import com.example.vehicles.utils.SearchCriteria;

import java.util.List;

public interface BoatDao {

    List<BoatEnty> getAll();

    BoatEnty get(Integer id);

    void add(BoatEnty boatEntity);

    void remove(BoatEnty boatEntity);

    void alter(BoatEnty boatEntity);

    List<BoatEnty> findAll(List<SearchCriteria> params);
}
