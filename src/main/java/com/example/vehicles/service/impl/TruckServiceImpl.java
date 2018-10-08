package com.example.vehicles.service.impl;

import com.example.vehicles.dao.TruckDao;
import com.example.vehicles.entity.TruckEntity;
import com.example.vehicles.entity.VehicleEntity;
import com.example.vehicles.service.TruckService;
import com.example.vehicles.service.VehicleService;
import com.example.vehicles.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TruckServiceImpl implements TruckService {
    @Autowired
    private TruckDao carDao;

    @Autowired
    private VehicleService vehicleService;



    @Override
    public List<TruckEntity> getAll(List<SearchCriteria> params) {
        return carDao.findAll(params);
    }

    @Override
    public TruckEntity get(Integer id) {
        return carDao.get(id);
    }


    @Override
    public boolean add(TruckEntity truckEntity) {
        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.getTruckEntities().add(truckEntity);

        vehicleService.add(vehicleEntity);

        if (null == truckEntity.getId()){
            return false;
        }

        return true;
    }

    @Override
    public void remove(Integer id) {
        TruckEntity truckEntity = get(id);
        VehicleEntity vehicle = vehicleService.retrieveFromTru(truckEntity);
        vehicleService.remove(vehicle);
    }

    @Override
    public TruckEntity update(TruckEntity truckEntity) {
        carDao.alter(truckEntity);

        return truckEntity;
    }
}
