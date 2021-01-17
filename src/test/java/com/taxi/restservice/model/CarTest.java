package com.taxi.restservice.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarTest {

    @Test
    public void reachDestination() {
        final Car car = new Car(1);
        car.setDestinationPosition(new Coordinate(2, 2));
        car.setOccupyTo(6L);
        car.reachDestination();
        assertFalse(car.getOccupyTo().isPresent());
        assertFalse(car.getDestinationPosition().isPresent());
        assertTrue(car.getCurrentPosition().equals(new Coordinate(2, 2)));
    }

    @Test
    public void reset() {
        final Car car = new Car(1);
        car.setOccupyTo(4L);
        car.setDestinationPosition(new Coordinate(4, 5));
        car.reachDestination();
        assertTrue(car.getCurrentPosition().equals(new Coordinate(4, 5)));
        car.reset();
        assertTrue(car.getCurrentPosition().equals(new Coordinate(0, 0)));
        assertFalse(car.getOccupyTo().isPresent());
        assertFalse(car.getDestinationPosition().isPresent());
    }
}
