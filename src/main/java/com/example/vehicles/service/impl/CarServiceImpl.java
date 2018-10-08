package com.example.vehicles.service.impl;

import com.example.vehicles.dao.CarDao;
import com.example.vehicles.entity.CarEntity;
import com.example.vehicles.entity.VehicleEntity;
import com.example.vehicles.service.CarService;
import com.example.vehicles.service.VehicleService;
import com.example.vehicles.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Autowired
    private VehicleService vehicleService;



    @Override
    public List<CarEntity> getAll(List<SearchCriteria> params) {
        return carDao.findAll(params);
    }

    @Override
    public CarEntity get(Integer id) {
        return carDao.get(id);
    }


    @Override
    public boolean add(CarEntity carEntity) {
        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.getCarEntities().add(carEntity);

        vehicleService.add(vehicleEntity);

        if (null == carEntity.getId()){
            return false;
        }

        return true;
    }

    @Override
    public void remove(Integer id) {
        CarEntity carEntity = get(id);
        VehicleEntity vehicle = vehicleService.retrieveFromCar(carEntity);
        vehicleService.remove(vehicle);
    }

    @Override
    public CarEntity update(CarEntity carEntity) {
        carDao.alter(carEntity);

        return carEntity;
    }


}
