package com.example.vehicles.controller;

import com.example.vehicles.entity.AirplaneEntity;
import com.example.vehicles.exception.ResourceNotFoundException;
import com.example.vehicles.model.AirplaneModel;
import com.example.vehicles.service.AirplaneService;
import com.example.vehicles.utils.SearchCriteria;
import com.example.vehicles.utils.VehicleUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    @RequestMapping(value = "/airplane", method = RequestMethod.GET)
    @ResponseBody
    public AirplaneModel retrieveAll(@RequestParam(value = "search", required = false) String search) {

        AirplaneModel response = new AirplaneModel();

        List<SearchCriteria> params = new ArrayList<>();

        VehicleUtils.retriveParams(search, params);

        response.setAirplanes(airplaneService.getAll(params));

        if (CollectionUtils.isEmpty(response.getAirplanes())) {
            throw new ResourceNotFoundException();
        }
        return response;
    }

    @RequestMapping(value = "/airplane/{id_airplane}", method = RequestMethod.GET)
    @ResponseBody
    public AirplaneModel retrieve(@PathVariable(name = "id_airplane") Integer id) {
        System.out.println(id);

        AirplaneModel response = new AirplaneModel();

        response.setAirplane(airplaneService.get(id));

        if (null == response.getAirplane()) {
            throw new ResourceNotFoundException();
        }

        return response;
    }

    @RequestMapping(value = "/airplane", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private AirplaneModel add(@RequestBody(required = true) AirplaneEntity airplaneEntity) {
        System.out.println(airplaneEntity);

        AirplaneModel airplaneModel = new AirplaneModel();
        airplaneService.add(airplaneEntity);
        if (null == airplaneEntity.getId()) {
            airplaneModel.setMsg("There is an arror to add the airplane.");
        }
        airplaneModel.setAirplane(airplaneEntity);

        return airplaneModel;
    }

    @RequestMapping(value = "/airplane/{id_airplane}", method = RequestMethod.DELETE)
    @ResponseBody
    public AirplaneModel remove(@PathVariable(name = "id_airplane") Integer id) {
        System.out.println(id);

        AirplaneModel response = new AirplaneModel();

        airplaneService.remove(id);

        response.setMsg("The airplane was deleted successfully.");
        return response;
    }


    @RequestMapping( value = "/airplane/{id_airplane}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AirplaneModel update(@PathVariable(name = "id_airplane", required = true) Integer id, @RequestBody(required = true) AirplaneEntity airplaneEntity) {
        System.out.println(airplaneEntity);
        System.out.println(id);

        AirplaneModel response = new AirplaneModel();
        airplaneEntity.setId(id);
        response.setAirplane(airplaneService.update(airplaneEntity));

        return response;
    }



}
