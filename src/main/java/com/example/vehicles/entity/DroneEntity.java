package com.example.vehicles.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Drone")
@JsonRootName("Drone")
public class DroneEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "id_sequence_drone")
    @SequenceGenerator(name = "id_sequence_drone", sequenceName = "id_sequence_drone", allocationSize = 1)
    private Integer id;

    @Column(name = "type")
    @JsonProperty("type")
    private String type;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "capacity")
    @JsonProperty("capacity")
    private String capacity;

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
        DroneEntity that = (DroneEntity) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(name, that.name) &&
                Objects.equals(capacity, that.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, capacity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "DroneEntity{" +
                "capacity='" + capacity + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
