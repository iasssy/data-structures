package com.structures.day01carssorted1.entity;

import java.util.Comparator;

public class SortByEngineSize implements Comparator<Car> {
    @Override
    public int compare(Car obj1, Car obj2) {
        if (obj1.engineSizeL < obj2.engineSizeL) return 1;
        if (obj1.engineSizeL > obj2.engineSizeL) return -1;
        return 0;
    }
}
