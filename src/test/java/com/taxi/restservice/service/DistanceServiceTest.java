package com.taxi.restservice.service;

import com.taxi.restservice.model.Coordinate;
import org.junit.jupiter.api.Test;

public class DistanceServiceTest {

    @Test
    public void calculateDistance() {
        final DistanceService distanceService = new DistanceServiceImpl();
        final long distance = distanceService.calculateDistance(
                new Coordinate(1, 2),
                new Coordinate(2, 3)
        );
        assert(distance == 2);
    }

    @Test
    public void calculateDistanceWithOneOfThemIsNegativeCoordinate() {
        final DistanceService distanceService = new DistanceServiceImpl();
        final long distance = distanceService.calculateDistance(
                new Coordinate(-1, -2),
                new Coordinate(2, 3)
        );
        assert(distance == 8);
    }

    @Test
    public void calculateDistanceWithBothAreNegativeCoordinate() {
        final DistanceService distanceService = new DistanceServiceImpl();
        final long distance = distanceService.calculateDistance(
                new Coordinate(-1, -2),
                new Coordinate(-8, -3)
        );
        assert(distance == 8);
    }
}
