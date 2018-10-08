package com.example.vehicles.service.impl;

import com.example.vehicles.dao.DroneDao;
import com.example.vehicles.entity.DroneEntity;
import com.example.vehicles.entity.VehicleEntity;
import com.example.vehicles.service.DroneService;
import com.example.vehicles.service.VehicleService;
import com.example.vehicles.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneServiceImpl implements DroneService {
    @Autowired
    private DroneDao droneDao;

    @Autowired
    private VehicleService vehicleService;



    @Override
    public List<DroneEntity> getAll(List<SearchCriteria> params) {
        return droneDao.findAll(params);
    }

    @Override
    public DroneEntity get(Integer id) {
        return droneDao.get(id);
    }


    @Override
    public boolean add(DroneEntity droneEntity) {
        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.getDroneEntities().add(droneEntity);

        vehicleService.add(vehicleEntity);

        if (null == droneEntity.getId()){
            return false;
        }

        return true;
    }

    @Override
    public void remove(Integer id) {
        DroneEntity droneEntity = get(id);
        VehicleEntity vehicle = vehicleService.retrieveFromDro(droneEntity);
        vehicleService.remove(vehicle);
    }

    @Override
    public DroneEntity update(DroneEntity droneEntity) {
        droneDao.alter(droneEntity);

        return droneEntity;
    }
}
