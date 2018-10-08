package com.example.vehicles.service;

import com.example.vehicles.entity.*;
import com.example.vehicles.utils.SearchCriteria;

import java.util.List;

public interface VehicleService {

    List<VehicleEntity> getAll(List<SearchCriteria> params);

    List<VehicleEntity> getByType(String type);

    void add(VehicleEntity vehicleEntity);

    void removeLast();

    VehicleEntity retrieveFromAir(AirplaneEntity airplaneEntity);

    VehicleEntity retrieveFromAmp(AmphibianEntity amphibianEntity);

    VehicleEntity retrieveFromBoat(BoatEnty boatEnty);

    VehicleEntity retrieveFromCar(CarEntity carEntity);

    VehicleEntity retrieveFromDro(DroneEntity droneEntity);

    VehicleEntity retrieveFromTru(TruckEntity truckEntity);

    void remove(VehicleEntity vehicleEntity);
}
