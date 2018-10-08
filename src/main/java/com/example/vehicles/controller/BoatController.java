package com.example.vehicles.controller;

import com.example.vehicles.entity.BoatEnty;
import com.example.vehicles.exception.ResourceNotFoundException;
import com.example.vehicles.model.BoatModel;
import com.example.vehicles.service.BoatService;
import com.example.vehicles.utils.SearchCriteria;
import com.example.vehicles.utils.VehicleUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BoatController {

    @Autowired
    private BoatService boatService;

    @RequestMapping(value = "/boat", method = RequestMethod.GET)
    @ResponseBody
    public BoatModel retrieveAll(@RequestParam(value = "search", required = false) String search) {

        BoatModel response = new BoatModel();

        List<SearchCriteria> params = new ArrayList<>();

        VehicleUtils.retriveParams(search, params);

        response.setBoats(boatService.getAll(params));

        if (CollectionUtils.isEmpty(response.getBoats())) {
            throw new ResourceNotFoundException();
        }
        return response;
    }

    @RequestMapping(value = "/boat/{id_boat}", method = RequestMethod.GET)
    @ResponseBody
    public BoatModel retrieve(@PathVariable(name = "id_boat") Integer id) {
        System.out.println(id);

        BoatModel response = new BoatModel();

        response.setBoat(boatService.get(id));

        if (null == response.getBoat()) {
            throw new ResourceNotFoundException();
        }

        return response;
    }

    @RequestMapping(value = "/boat", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private BoatModel add(@RequestBody(required = true) BoatEnty boatEnty) {
        System.out.println(boatEnty);

        BoatModel boatModel = new BoatModel();
        boatService.add(boatEnty);
        if (null == boatEnty.getId()) {
            boatModel.setMsg("There is an arror to add the boat.");
        }
        boatModel.setBoat(boatEnty);

        return boatModel;
    }

    @RequestMapping(value = "/boat/{id_boat}", method = RequestMethod.DELETE)
    @ResponseBody
    public BoatModel remove(@PathVariable(name = "id_boat") Integer id) {
        System.out.println(id);

        BoatModel response = new BoatModel();

        boatService.remove(id);

        response.setMsg("The boat was deleted successfully.");
        return response;
    }


    @RequestMapping( value = "/boat/{id_boat}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BoatModel update(@PathVariable(name = "id_boat", required = true) Integer id, @RequestBody(required = true) BoatEnty boatEnty) {
        System.out.println(boatEnty);
        System.out.println(id);

        BoatModel response = new BoatModel();
        boatEnty.setId(id);
        response.setBoat(boatService.update(boatEnty));

        return response;
    }



}
