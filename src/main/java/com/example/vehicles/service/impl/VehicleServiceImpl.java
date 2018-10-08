package com.example.vehicles.service.impl;

import com.example.vehicles.dao.VehicleDao;
import com.example.vehicles.entity.*;
import com.example.vehicles.exception.ResourceNotFoundException;
import com.example.vehicles.service.VehicleService;
import com.example.vehicles.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {


    @Autowired
    private VehicleDao vehicleDao;

    @Override
    public List<VehicleEntity> getAll(List<SearchCriteria> params) {

        return vehicleDao.findAll(params);
    }

    @Override
    public List<VehicleEntity> getByType(String type) {
        return vehicleDao.getByType(type);
    }

    @Override
    public void add(VehicleEntity vehicleEntity) {
        vehicleDao.add(vehicleEntity);
    }

    @Override
    public void removeLast() {
        VehicleEntity vehicleEntity;
        try {
            vehicleEntity = vehicleDao.selectLast();
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException(e);
        }

        if (null != vehicleEntity) {
            System.out.println(vehicleEntity);
            vehicleDao.remove(vehicleEntity);
        } else {
            throw new ResourceNotFoundException();
        }

    }

    @Override
    public VehicleEntity retrieveFromAir(AirplaneEntity airplaneEntity) {
        return vehicleDao.findByAirplane(airplaneEntity);
    }

    @Override
    public VehicleEntity retrieveFromAmp(AmphibianEntity amphibianEntity) {
        return vehicleDao.findByAmphibian(amphibianEntity);
    }

    @Override
    public VehicleEntity retrieveFromBoat(BoatEnty boatEnty) {
        return vehicleDao.findByBoat(boatEnty);
    }

    @Override
    public VehicleEntity retrieveFromCar(CarEntity carEntity) {
        return vehicleDao.findByCar(carEntity);
    }

    @Override
    public VehicleEntity retrieveFromDro(DroneEntity droneEntity) {
        return vehicleDao.findByDrone(droneEntity);
    }

    @Override
    public VehicleEntity retrieveFromTru(TruckEntity truckEntity) {
        return vehicleDao.findByTruck(truckEntity);
    }

    @Override
    public void remove(VehicleEntity vehicleEntity) {
        vehicleDao.remove(vehicleEntity);
    }
}
