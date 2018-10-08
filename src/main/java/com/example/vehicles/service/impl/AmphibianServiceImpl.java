package com.example.vehicles.service.impl;

import com.example.vehicles.dao.AmphibianDao;
import com.example.vehicles.entity.AmphibianEntity;
import com.example.vehicles.entity.VehicleEntity;
import com.example.vehicles.service.AmphibianService;
import com.example.vehicles.service.VehicleService;
import com.example.vehicles.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmphibianServiceImpl implements AmphibianService {

    @Autowired
    private AmphibianDao amphibianDao;

    @Autowired
    private VehicleService vehicleService;



    @Override
    public List<AmphibianEntity> getAll(List<SearchCriteria> params) {
        return amphibianDao.findAll(params);
    }

    @Override
    public AmphibianEntity get(Integer id) {
        return amphibianDao.get(id);
    }


    @Override
    public boolean add(AmphibianEntity amphibianEntity) {
        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.getAmphibianEntities().add(amphibianEntity);

        vehicleService.add(vehicleEntity);
        
        if (null == amphibianEntity.getId()){
            return false;
        }

        return true;
    }

    @Override
    public void remove(Integer id) {
        AmphibianEntity amphibianEntity = get(id);
        VehicleEntity vehicle = vehicleService.retrieveFromAmp(amphibianEntity);
        vehicleService.remove(vehicle);
    }

    @Override
    public AmphibianEntity update(AmphibianEntity amphibianEntity) {
        amphibianDao.alter(amphibianEntity);

        return amphibianEntity;
    }
}
