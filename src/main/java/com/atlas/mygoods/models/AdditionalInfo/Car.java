package com.atlas.mygoods.models.AdditionalInfo;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity(name = "Car")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "car_model_list")
public class Car implements Serializable {

    @Id
    @Column(name = "object_id", nullable = false, unique = true)
    private String objectId;

    @Column(name = "Make")
    private String make;
    @Column(name = "Model")
    private String model;
    @Column(name = "Category")
    private String category;
    @Column(name = "Year")
    private String year;

    public Car(String objectId, String make, String model, String category, String year) {
        this.objectId = objectId;
        this.make = make;
        this.model = model;
        this.category = category;
        this.year = year;
    }

    public Car(String make, String model, String category, String year) {
        this.make = make;
        this.model = model;
        this.category = category;
        this.year = year;
    }


    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return objectId != null && Objects.equals(objectId, car.objectId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
