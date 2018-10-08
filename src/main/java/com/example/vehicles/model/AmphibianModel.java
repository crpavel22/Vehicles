package com.example.vehicles.model;

import com.example.vehicles.entity.AmphibianEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmphibianModel extends ResponseModel {

    @JsonProperty("amphibians")
    List<AmphibianEntity> amphibians;

    @JsonProperty("amphibian")
    AmphibianEntity amphibian;

    public List<AmphibianEntity> getAmphibians() {
        return amphibians;
    }

    public void setAmphibians(List<AmphibianEntity> amphibians) {
        this.amphibians = amphibians;
    }

    public AmphibianEntity getAmphibian() {
        return amphibian;
    }

    public void setAmphibian(AmphibianEntity amphibian) {
        this.amphibian = amphibian;
    }

    @Override
    public String toString() {
        return "AmphibianModel{" +
                "amphibian=" + amphibian +
                ", amphibians=" + amphibians +
                '}';
    }
}
