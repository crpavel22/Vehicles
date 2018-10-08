package com.example.vehicles.controller;

import com.example.vehicles.entity.TruckEntity;
import com.example.vehicles.exception.ResourceNotFoundException;
import com.example.vehicles.model.TruckModel;
import com.example.vehicles.service.TruckService;
import com.example.vehicles.utils.SearchCriteria;
import com.example.vehicles.utils.VehicleUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TruckController {

    @Autowired
    private TruckService truckService;

    @RequestMapping(value = "/truck", method = RequestMethod.GET)
    @ResponseBody
    public TruckModel retrieveAll(@RequestParam(value = "search", required = false) String search) {

        TruckModel response = new TruckModel();

        List<SearchCriteria> params = new ArrayList<>();

        VehicleUtils.retriveParams(search, params);

        response.setTrucks(truckService.getAll(params));

        if (CollectionUtils.isEmpty(response.getTrucks())) {
            throw new ResourceNotFoundException();
        }
        return response;
    }

    @RequestMapping(value = "/truck/{id_truck}", method = RequestMethod.GET)
    @ResponseBody
    public TruckModel retrieve(@PathVariable(name = "id_truck") Integer id) {
        System.out.println(id);

        TruckModel response = new TruckModel();

        response.setTruck(truckService.get(id));

        if (null == response.getTruck()) {
            throw new ResourceNotFoundException();
        }

        return response;
    }

    @RequestMapping(value = "/truck", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private TruckModel add(@RequestBody(required = true) TruckEntity truckEntity) {
        System.out.println(truckEntity);

        TruckModel truckModel = new TruckModel();
        truckService.add(truckEntity);
        if (null == truckEntity.getId()) {
            truckModel.setMsg("There is an arror to add the truck.");
        }
        truckModel.setTruck(truckEntity);

        return truckModel;
    }

    @RequestMapping(value = "/truck/{id_truck}", method = RequestMethod.DELETE)
    @ResponseBody
    public TruckModel remove(@PathVariable(name = "id_truck") Integer id) {
        System.out.println(id);

        TruckModel response = new TruckModel();

        truckService.remove(id);

        response.setMsg("The truck was deleted successfully.");
        return response;
    }


    @RequestMapping( value = "/truck/{id_truck}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TruckModel update(@PathVariable(name = "id_truck", required = true) Integer id, @RequestBody(required = true) TruckEntity truckEntity) {
        System.out.println(truckEntity);
        System.out.println(id);

        TruckModel response = new TruckModel();
        truckEntity.setId(id);
        response.setTruck(truckService.update(truckEntity));

        return response;
    }



}
