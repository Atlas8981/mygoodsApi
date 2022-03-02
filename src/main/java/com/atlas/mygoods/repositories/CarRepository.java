package com.atlas.mygoods.repositories;

import com.atlas.mygoods.models.AdditionalInfo.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    @Query(value = "SELECT * FROM mygoods.car_model_list limit ?1",nativeQuery = true)
    List<Car> getAllCarWithLimit(Long limit);
}
