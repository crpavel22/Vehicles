package com.example.vehicles.controller;

import com.example.vehicles.entity.AmphibianEntity;
import com.example.vehicles.exception.ResourceNotFoundException;
import com.example.vehicles.model.AmphibianModel;
import com.example.vehicles.service.AmphibianService;
import com.example.vehicles.utils.SearchCriteria;
import com.example.vehicles.utils.VehicleUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AmphibianController {

    @Autowired
    private AmphibianService amphibianService;

    @RequestMapping(value = "/amphibian", method = RequestMethod.GET)
    @ResponseBody
    public AmphibianModel retrieveAll(@RequestParam(value = "search", required = false) String search) {

        AmphibianModel response = new AmphibianModel();

        List<SearchCriteria> params = new ArrayList<>();

        VehicleUtils.retriveParams(search, params);

        response.setAmphibians(amphibianService.getAll(params));

        if (CollectionUtils.isEmpty(response.getAmphibians())) {
            throw new ResourceNotFoundException();
        }
        return response;
    }

    @RequestMapping(value = "/amphibian/{id_amphibian}", method = RequestMethod.GET)
    @ResponseBody
    public AmphibianModel retrieve(@PathVariable(name = "id_amphibian") Integer id) {
        System.out.println(id);

        AmphibianModel response = new AmphibianModel();

        response.setAmphibian(amphibianService.get(id));

        if (null == response.getAmphibian()) {
            throw new ResourceNotFoundException();
        }

        return response;
    }

    @RequestMapping(value = "/amphibian", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private AmphibianModel add(@RequestBody(required = true) AmphibianEntity amphibianEntity) {
        System.out.println(amphibianEntity);

        AmphibianModel amphibianModel = new AmphibianModel();
        amphibianService.add(amphibianEntity);
        if (null == amphibianEntity.getId()) {
            amphibianModel.setMsg("There is an arror to add the amphibian.");
        }
        amphibianModel.setAmphibian(amphibianEntity);

        return amphibianModel;
    }

    @RequestMapping(value = "/amphibian/{id_amphibian}", method = RequestMethod.DELETE)
    @ResponseBody
    public AmphibianModel remove(@PathVariable(name = "id_amphibian") Integer id) {
        System.out.println(id);

        AmphibianModel response = new AmphibianModel();

        amphibianService.remove(id);

        response.setMsg("The amphibian was deleted successfully.");
        return response;
    }


    @RequestMapping( value = "/amphibian/{id_amphibian}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AmphibianModel update(@PathVariable(name = "id_amphibian", required = true) Integer id, @RequestBody(required = true) AmphibianEntity amphibianEntity) {
        System.out.println(amphibianEntity);
        System.out.println(id);

        AmphibianModel response = new AmphibianModel();
        amphibianEntity.setId(id);
        response.setAmphibian(amphibianService.update(amphibianEntity));

        return response;
    }



}
