package com.example.vehicles.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Airplane")
@JsonRootName("Airplane")
public class AirplaneEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_air")
    @SequenceGenerator(name = "id_sequence_air", sequenceName = "id_sequence_air", allocationSize = 1)
    private Integer id;

    @Column(name = "type")
    @JsonProperty("type")
    private String type;
    @Column(name = "fuel")
    @JsonProperty("fuel")
    private String fuel;
    @Column(name = "capacity")
    @JsonProperty("capacity")
    private Integer capacity;

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirplaneEntity that = (AirplaneEntity) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(fuel, that.fuel) &&
                Objects.equals(capacity, that.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, fuel, capacity);
    }

    @Override
    public String toString() {
        return "AirplaneEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
