package com.example.vehicles.service.impl;

import com.example.vehicles.dao.AirplaneDao;
import com.example.vehicles.entity.AirplaneEntity;
import com.example.vehicles.entity.VehicleEntity;
import com.example.vehicles.service.AirplaneService;
import com.example.vehicles.service.VehicleService;
import com.example.vehicles.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    private AirplaneDao airplaneDao;

    @Autowired
    private VehicleService vehicleService;



    @Override
    public List<AirplaneEntity> getAll(List<SearchCriteria> params) {
        return airplaneDao.findAll(params);
    }

    @Override
    public AirplaneEntity get(Integer id) {
        return airplaneDao.get(id);
    }


    @Override
    public boolean add(AirplaneEntity airplaneEntity) {
        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.getAirplaneEntities().add(airplaneEntity);

        vehicleService.add(vehicleEntity);

        if (null != airplaneEntity.getId()){
            return true;
        }

        return false;
    }

    @Override
    public void remove(Integer id) {
        AirplaneEntity airplaneEntity = get(id);
        VehicleEntity vehicle = vehicleService.retrieveFromAir(airplaneEntity);
        vehicleService.remove(vehicle);
    }

    @Override
    public AirplaneEntity update(AirplaneEntity airplaneEntity) {
        airplaneDao.alter(airplaneEntity);

        return airplaneEntity;
    }
}
