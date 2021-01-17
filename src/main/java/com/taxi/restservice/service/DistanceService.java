package com.taxi.restservice.service;

import com.taxi.restservice.model.Coordinate;

public interface DistanceService {
    long calculateDistance(final Coordinate c1,
                           final Coordinate c2);
}
