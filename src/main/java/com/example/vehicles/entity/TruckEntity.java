package com.example.vehicles.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Truck")
@JsonRootName("Truck")
public class TruckEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "id_sequence_truck")
    @SequenceGenerator(name = "id_sequence_truck", sequenceName = "id_sequence_truck", allocationSize = 1)
    private Integer id;

    @Column(name = "type")
    @JsonProperty("type")
    private String type;

    @Column(name = "size")
    @JsonProperty("size")
    private Integer size;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckEntity that = (TruckEntity) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(size, that.size) &&
                Objects.equals(capacity, that.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, size, capacity);
    }

    @Column(name = "capacity")
    @JsonProperty("capacity")
    private String capacity;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
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
    public String toString() {
        return "TruckEntity{" +
                "capacity='" + capacity + '\'' +
                ", id=" + id +
                ", size=" + size +
                ", type='" + type + '\'' +
                '}';
    }
}
