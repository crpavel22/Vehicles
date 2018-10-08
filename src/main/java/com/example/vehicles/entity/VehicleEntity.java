package com.example.vehicles.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.apache.commons.collections.CollectionUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Vehicle")
@JsonRootName("Vehicle")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_vehi")
    @SequenceGenerator(name = "id_sequence_vehi", sequenceName = "id_sequence_vehi")
    private Integer id;

    @JsonProperty("airplanes")
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<AirplaneEntity> airplaneEntities;

    @JsonProperty("amphibians")
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<AmphibianEntity> amphibianEntities;

    @JsonProperty("boats")
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<BoatEnty> boatEnties;

    @JsonProperty("cars")
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CarEntity> carEntities;

    @JsonProperty("drones")
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<DroneEntity> droneEntities;

    @JsonProperty("trucks")
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<TruckEntity> truckEntities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<AirplaneEntity> getAirplaneEntities() {
        if (CollectionUtils.isEmpty(airplaneEntities)){
            airplaneEntities = new HashSet<>();
        }
        return airplaneEntities;
    }

    public void setAirplaneEntities(Set<AirplaneEntity> airplaneEntities) {
        this.airplaneEntities = airplaneEntities;
    }

    public Set<AmphibianEntity> getAmphibianEntities() {
        if (CollectionUtils.isEmpty(amphibianEntities)){
            amphibianEntities = new HashSet<>();
        }
        return amphibianEntities;
    }

    public void setAmphibianEntities(Set<AmphibianEntity> amphibianEntities) {
        this.amphibianEntities = amphibianEntities;
    }

    public Set<BoatEnty> getBoatEnties() {
        if (CollectionUtils.isEmpty(boatEnties)){
            boatEnties = new HashSet<>();
        }
        return boatEnties;
    }

    public void setBoatEnties(Set<BoatEnty> boatEnties) {
        this.boatEnties = boatEnties;
    }



    public Set<CarEntity> getCarEntities() {
        if (CollectionUtils.isEmpty(carEntities)){
            carEntities = new HashSet<>();
        }
        return carEntities;
    }

    public void setCarEntities(Set<CarEntity> carEntities) {
        this.carEntities = carEntities;
    }

    public Set<DroneEntity> getDroneEntities() {
        if (CollectionUtils.isEmpty(droneEntities)){
            droneEntities = new HashSet<>();
        }
        return droneEntities;
    }

    public void setDroneEntities(Set<DroneEntity> droneEntities) {
        this.droneEntities = droneEntities;
    }

    public Set<TruckEntity> getTruckEntities() {
        if (CollectionUtils.isEmpty(truckEntities)){
            truckEntities = new HashSet<>();
        }
        return truckEntities;
    }

    public void setTruckEntities(Set<TruckEntity> truckEntities) {
        this.truckEntities = truckEntities;
    }

    @JsonGetter("airplanes")
    private Set<AirplaneEntity> getAirplanes() {
        if (CollectionUtils.isEmpty(airplaneEntities)) {
            return null;
        }
        return airplaneEntities;
    }

    @JsonGetter("amphibians")
    private Set<AmphibianEntity> getAmphibians() {
        if (CollectionUtils.isEmpty(amphibianEntities)) {
            return null;
        }
        return amphibianEntities;
    }

    @JsonGetter("boats")
    private Set<BoatEnty> getBoats() {
        if (CollectionUtils.isEmpty(boatEnties)) {
            return null;
        }
        return boatEnties;
    }

    @JsonGetter("cars")
    private Set<CarEntity> getCars() {
        if (CollectionUtils.isEmpty(carEntities)) {
            return null;
        }
        return carEntities;
    }

    @JsonGetter("drones")
    private Set<DroneEntity> getDrones() {
        if (CollectionUtils.isEmpty(droneEntities)) {
            return null;
        }
        return droneEntities;
    }

    @JsonGetter("trucks")
    private Set<TruckEntity> getTrucks() {
        if (CollectionUtils.isEmpty(truckEntities)) {
            return null;
        }
        return truckEntities;
    }


    @Override
    public String toString() {
        return "VehicleEntity{" +
                "airplaneEntities=" + airplaneEntities +
                ", amphibianEntities=" + amphibianEntities +
                ", boatEnties=" + boatEnties +
                ", carEntities=" + carEntities +
                ", droneEntities=" + droneEntities +
                ", id=" + id +
                ", truckEntities=" + truckEntities +
                '}';
    }
}
