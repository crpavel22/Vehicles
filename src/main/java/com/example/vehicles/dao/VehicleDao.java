package com.example.vehicles.dao;

import com.example.vehicles.entity.*;
import com.example.vehicles.utils.SearchCriteria;

import java.util.List;

public interface VehicleDao {

    List<VehicleEntity> getAll();

    List<VehicleEntity> getByType(String type);

    VehicleEntity selectLast();

    void add(VehicleEntity vehicleEntity);

    void remove(VehicleEntity vehicleEntity);

    List<VehicleEntity> findAll(List<SearchCriteria> params);

    VehicleEntity findByAirplane(AirplaneEntity airplaneEntity);

    VehicleEntity findByAmphibian(AmphibianEntity amphibianEntity);

    VehicleEntity findByBoat(BoatEnty boatEnty);

    VehicleEntity findByCar(CarEntity carEntity);

    VehicleEntity findByDrone(DroneEntity droneEntity);

    VehicleEntity findByTruck(TruckEntity truckEntity);
}
