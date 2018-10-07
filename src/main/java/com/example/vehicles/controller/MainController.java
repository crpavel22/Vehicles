package com.example.vehicles.controller;

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
public class MainController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel retrieveAll(){
        ResponseModel result = new ResponseModel();

        result.setResult(vehicleService.getAll());

        return result;
    }

    @RequestMapping(value = "/last", method = RequestMethod.DELETE)
    public void deleteLast(){
        System.out.println("Delete");

        vehicleService.removeLast();
    }

}
