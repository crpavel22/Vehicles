package com.example.vehicles;

import com.example.vehicles.entity.BoatEnty;
import com.example.vehicles.entity.CarEntity;
import com.example.vehicles.entity.VehicleEntity;
import com.example.vehicles.service.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLOutput;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/application-config.xml","/spring/db-config.xml"})
public class tst {

    @Autowired
    private VehicleService vehicleService;

    @Test
    public void tst(){

        BoatEnty boat = new BoatEnty();
        boat.setSize("12");
        boat.setName("Boat 1");
        boat.setType("BOAT");

        CarEntity car = new CarEntity();
        car.setColor("RED");
        car.setName("Car 1");
        car.setType("CAR");

        vehicleService.add(boat);
        vehicleService.add(car);
    }

    @Test
    public void getAll() {
        System.out.println(vehicleService.getAll());
    }
}
