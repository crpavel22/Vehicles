package com.example.vehicles.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "Boat")
@JsonRootName("Boat")
public class BoatEnty {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "id_sequence_boat")
    @SequenceGenerator(name = "id_sequence_boat", sequenceName = "id_sequence_boat", allocationSize = 1)
    private Integer id;

    @Column(name = "type")
    @JsonProperty("type")
    private String type;

    @Column(name = "numberMotors")
    @JsonProperty("numberMotors")
    private Integer numberMotors;

    @Column(name = "fuel")
    @JsonProperty("fuel")
    private String fuel;

    @Column(name = "size")
    @JsonProperty("size")
    private Integer size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumberMotors() {
        return numberMotors;
    }

    public void setNumberMotors(Integer numberMotors) {
        this.numberMotors = numberMotors;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoatEnty boatEnty = (BoatEnty) o;
        return Objects.equals(type, boatEnty.type) &&
                Objects.equals(numberMotors, boatEnty.numberMotors) &&
                Objects.equals(fuel, boatEnty.fuel) &&
                Objects.equals(size, boatEnty.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, numberMotors, fuel, size);
    }

    @Override
    public String toString() {
        return "BoatEnty{" +
                "fuel='" + fuel + '\'' +
                ", id=" + id +
                ", numberMotors=" + numberMotors +
                ", size=" + size +
                ", type='" + type + '\'' +
                '}';
    }
}
