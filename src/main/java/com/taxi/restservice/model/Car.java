package com.taxi.restservice.model;

import java.util.Optional;

public class Car {

    private int carId;
    private Coordinate currentPosition;
    private Optional<Coordinate> destinationPosition;
    private Optional<Long> occupyTo;

    public Car(final int carId) {
        this.carId = carId;
        reset();
    }

    public void reset() {
        currentPosition = new Coordinate(0, 0);
        destinationPosition = Optional.empty();
        occupyTo = Optional.empty();
    }

    public int getCarId() {
        return carId;
    }

    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    public Optional<Coordinate> getDestinationPosition() {
        return destinationPosition;
    }

    public Optional<Long> getOccupyTo() {
        return occupyTo;
    }

    public void setOccupyTo(Long time) {
        occupyTo = Optional.ofNullable(time);
    }

    public void setDestinationPosition(final Coordinate coordinate) {
        destinationPosition = Optional.ofNullable(coordinate);
    }

    public void reachDestination() {
        destinationPosition.ifPresent(d ->
                currentPosition = d
        );
        occupyTo = Optional.empty();
        destinationPosition = Optional.empty();
    }
}
