package com.structures.day01carssorted1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Comparable{
    String makeModel;
    double engineSizeL;
    int prodYear;

    @Override
    public String toString() {
        return "Car: " +
                "Make/Model='" + makeModel + ", Engine Size=" + engineSizeL + "L, Production Year=" + prodYear;
    }

    @Override
    public int compareTo(Object obj) {
        Car other = (Car) obj;
        return this.makeModel.compareTo(other.makeModel);
    }
}
