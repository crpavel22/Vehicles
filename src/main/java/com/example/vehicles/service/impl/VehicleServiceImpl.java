package com.example.vehicles.service.impl;

import com.example.vehicles.dao.VehicleDao;
import com.example.vehicles.entity.VehicleEntity;
import com.example.vehicles.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {


    @Autowired
    private VehicleDao vehicleDao;

    @Override
    public List<VehicleEntity> getAll() {

        return vehicleDao.getAll();
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
        VehicleEntity vehicleEntity = vehicleDao.selectLast();

        System.out.println(vehicleEntity);
        if (null != vehicleEntity){
            vehicleDao.remove(vehicleEntity);
        }

    }
}
