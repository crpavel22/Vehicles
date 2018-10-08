package com.example.vehicles.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Amphibian")
@JsonRootName("Amphibian")
public class AmphibianEntity {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "id_sequence_amp")
    @SequenceGenerator(name = "id_sequence_amp", sequenceName = "id_sequence_air", allocationSize = 1)
    private Integer id;

    @Column(name = "type")
    @JsonProperty("type")
    private String type;

    @Column(name = "size")
    @JsonProperty("size")
    private Integer size;

    @Column(name = "fuel")
    @JsonProperty("fuel")
    private String fuel;

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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmphibianEntity that = (AmphibianEntity) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(size, that.size) &&
                Objects.equals(fuel, that.fuel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, size, fuel);
    }

    @Override
    public String toString() {
        return "AmphibianEntity{" +
                "fuel='" + fuel + '\'' +
                ", id=" + id +
                ", size=" + size +
                ", type='" + type + '\'' +
                '}';
    }
}
