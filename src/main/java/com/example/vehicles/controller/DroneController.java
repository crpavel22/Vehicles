package com.example.vehicles.controller;

import com.example.vehicles.entity.DroneEntity;
import com.example.vehicles.exception.ResourceNotFoundException;
import com.example.vehicles.model.DroneModel;
import com.example.vehicles.service.DroneService;
import com.example.vehicles.utils.SearchCriteria;
import com.example.vehicles.utils.VehicleUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DroneController {

    @Autowired
    private DroneService droneService;

    @RequestMapping(value = "/drone", method = RequestMethod.GET)
    @ResponseBody
    public DroneModel retrieveAll(@RequestParam(value = "search", required = false) String search) {

        DroneModel response = new DroneModel();

        List<SearchCriteria> params = new ArrayList<>();

        VehicleUtils.retriveParams(search, params);

        response.setDrones(droneService.getAll(params));

        if (CollectionUtils.isEmpty(response.getDrones())) {
            throw new ResourceNotFoundException();
        }
        return response;
    }

    @RequestMapping(value = "/drone/{id_drone}", method = RequestMethod.GET)
    @ResponseBody
    public DroneModel retrieve(@PathVariable(name = "id_drone") Integer id) {
        System.out.println(id);

        DroneModel response = new DroneModel();

        response.setDrone(droneService.get(id));

        if (null == response.getDrone()) {
            throw new ResourceNotFoundException();
        }

        return response;
    }

    @RequestMapping(value = "/drone", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private DroneModel add(@RequestBody(required = true) DroneEntity droneEntity) {
        System.out.println(droneEntity);

        DroneModel droneModel = new DroneModel();
        droneService.add(droneEntity);
        if (null == droneEntity.getId()) {
            droneModel.setMsg("There is an arror to add the drone.");
        }
        droneModel.setDrone(droneEntity);

        return droneModel;
    }

    @RequestMapping(value = "/drone/{id_drone}", method = RequestMethod.DELETE)
    @ResponseBody
    public DroneModel remove(@PathVariable(name = "id_drone") Integer id) {
        System.out.println(id);

        DroneModel response = new DroneModel();

        droneService.remove(id);

        response.setMsg("The drone was deleted successfully.");
        return response;
    }


    @RequestMapping( value = "/drone/{id_drone}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DroneModel update(@PathVariable(name = "id_drone", required = true) Integer id, @RequestBody(required = true) DroneEntity droneEntity) {
        System.out.println(droneEntity);
        System.out.println(id);

        DroneModel response = new DroneModel();
        droneEntity.setId(id);
        response.setDrone(droneService.update(droneEntity));

        return response;
    }



}
