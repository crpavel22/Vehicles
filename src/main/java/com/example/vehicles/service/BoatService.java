package com.example.vehicles.service;

import com.example.vehicles.entity.BoatEnty;
import com.example.vehicles.utils.SearchCriteria;

import java.util.List;

public interface BoatService {

    List<BoatEnty> getAll(List<SearchCriteria> params);

    BoatEnty get(Integer id);

    boolean add(BoatEnty boatEnty);

    void remove(Integer id);

    BoatEnty update(BoatEnty boatEnty);
}
