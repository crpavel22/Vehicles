package com.example.vehicles.controller;

import com.example.vehicles.entity.CarEntity;
import com.example.vehicles.entity.VehicleEntity;
import com.example.vehicles.model.ResponseModel;
import com.example.vehicles.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private VehicleService vehicleService;


    @RequestMapping(value = "/car", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel retrieveAll(){
ResponseModel response = new ResponseModel();

response.setResult(vehicleService.getByType("CAR"));
        return response;
    }
}
