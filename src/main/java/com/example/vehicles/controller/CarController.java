package com.example.vehicles.controller;

import com.example.vehicles.entity.CarEntity;
import com.example.vehicles.exception.ResourceNotFoundException;
import com.example.vehicles.model.CarModel;
import com.example.vehicles.service.CarService;
import com.example.vehicles.utils.SearchCriteria;
import com.example.vehicles.utils.VehicleUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/car", method = RequestMethod.GET)
    @ResponseBody
    public CarModel retrieveAll(@RequestParam(value = "search", required = false) String search) {

        CarModel response = new CarModel();

        List<SearchCriteria> params = new ArrayList<>();

        VehicleUtils.retriveParams(search, params);

        response.setCars(carService.getAll(params));

        if (CollectionUtils.isEmpty(response.getCars())) {
            throw new ResourceNotFoundException();
        }
        return response;
    }

    @RequestMapping(value = "/car/{id_car}", method = RequestMethod.GET)
    @ResponseBody
    public CarModel retrieve(@PathVariable(name = "id_car") Integer id) {
        System.out.println(id);

        CarModel response = new CarModel();

        response.setCar(carService.get(id));

        if (null == response.getCar()) {
            throw new ResourceNotFoundException();
        }

        return response;
    }

    @RequestMapping(value = "/car", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private CarModel add(@RequestBody(required = true) CarEntity carEntity) {
        System.out.println(carEntity);

        CarModel carModel = new CarModel();
        boolean b = carService.add(carEntity);
        if (null == carEntity.getId()) {
            carModel.setMsg("There is an arror to add the car.");
        }
        carModel.setCar(carEntity);

        return carModel;
    }

    @RequestMapping(value = "/car/{id_car}", method = RequestMethod.DELETE)
    @ResponseBody
    public CarModel remove(@PathVariable(name = "id_car") Integer id) {
        System.out.println(id);

        CarModel response = new CarModel();

        carService.remove(id);

        response.setMsg("The car was deleted successfully.");
        return response;
    }


    @RequestMapping( value = "/car/{id_car}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CarModel update(@PathVariable(name = "id_car", required = true) Integer id, @RequestBody(required = true) CarEntity carEntity) {
        System.out.println(carEntity);
        System.out.println(id);

        CarModel response = new CarModel();
        carEntity.setId(id);
        response.setCar(carService.update(carEntity));

        return response;
    }



}
