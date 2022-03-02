package com.atlas.mygoods.models.AdditionalInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {
    private int count;
    private List<Car> cars;
}
