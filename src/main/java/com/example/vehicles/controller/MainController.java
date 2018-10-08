package com.example.vehicles.controller;

import com.example.vehicles.entity.VehicleEntity;
import com.example.vehicles.exception.ResourceNotFoundException;
import com.example.vehicles.model.ResponseModel;
import com.example.vehicles.model.VehicleModel;
import com.example.vehicles.service.VehicleService;
import com.example.vehicles.utils.SearchCriteria;
import com.example.vehicles.utils.VehicleUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class MainController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public VehicleModel retrieveAll(@RequestParam(value = "search", required = false) String search){
        VehicleModel result = new VehicleModel();

        List<SearchCriteria> params = new ArrayList<>();

        VehicleUtils.retriveParams(search, params);

        result.setVehicles(vehicleService.getAll(params));

        if (CollectionUtils.isEmpty(result.getVehicles())){
            throw new ResourceNotFoundException();
        }

        return result;
    }



    @RequestMapping(value = "/last", method = RequestMethod.DELETE)
    public VehicleModel deleteLast(){
        System.out.println("Delete");

        VehicleModel vehicleModel = new VehicleModel();

        vehicleService.removeLast();

        vehicleModel.setMsg("Registro Eliminado");

        return vehicleModel;
    }

}
