package com.example.vehicles.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Car")
@JsonRootName("Car")
public class CarEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "id_sequence_car")
    @SequenceGenerator(name = "id_sequence_car", sequenceName = "id_sequence_car", allocationSize = 1)
    private Integer id;

    @Column(name = "yearModel")
    @JsonProperty("yearModel")
    private Integer yearModel;
    @Column(name = "provider")
    @JsonProperty("provider")
    private String provider;
    @Column(name = "model")
    @JsonProperty("model")
    private String model;
    @Column(name = "doorsNumber")
    @JsonProperty("doorsNumber")
    private Integer doorsNumber;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYearModel() {
        return yearModel;
    }

    public void setYearModel(Integer yearModel) {
        this.yearModel = yearModel;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getDoorsNumber() {
        return doorsNumber;
    }

    public void setDoorsNumber(Integer doorsNumber) {
        this.doorsNumber = doorsNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEntity carEntity = (CarEntity) o;
        return Objects.equals(yearModel, carEntity.yearModel) &&
                Objects.equals(provider, carEntity.provider) &&
                Objects.equals(model, carEntity.model) &&
                Objects.equals(doorsNumber, carEntity.doorsNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yearModel, provider, model, doorsNumber);
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "doorsNumber=" + doorsNumber +
                ", id=" + id +
                ", provider='" + provider + '\'' +
                ", model='" + model + '\'' +
                ", yearModel=" + yearModel +
                '}';
    }
}
