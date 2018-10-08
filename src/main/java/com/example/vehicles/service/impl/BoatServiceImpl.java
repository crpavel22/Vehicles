package com.example.vehicles.service.impl;

import com.example.vehicles.dao.BoatDao;
import com.example.vehicles.entity.BoatEnty;
import com.example.vehicles.entity.VehicleEntity;
import com.example.vehicles.service.BoatService;
import com.example.vehicles.service.VehicleService;
import com.example.vehicles.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoatServiceImpl implements BoatService {

    @Autowired
    private BoatDao boatDao;

    @Autowired
    private VehicleService vehicleService;



    @Override
    public List<BoatEnty> getAll(List<SearchCriteria> params) {
        return boatDao.findAll(params);
    }

    @Override
    public BoatEnty get(Integer id) {
        return boatDao.get(id);
    }


    @Override
    public boolean add(BoatEnty boatEnty) {
        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.getBoatEnties().add(boatEnty);

        vehicleService.add(vehicleEntity);

        if (null == boatEnty.getId()){
            return false;
        }

        return true;
    }

    @Override
    public void remove(Integer id) {
        BoatEnty boatEnty = get(id);
        VehicleEntity vehicle = vehicleService.retrieveFromBoat(boatEnty);
        vehicleService.remove(vehicle);
    }

    @Override
    public BoatEnty update(BoatEnty boatEnty) {
        boatDao.alter(boatEnty);

        return boatEnty;
    }
}
