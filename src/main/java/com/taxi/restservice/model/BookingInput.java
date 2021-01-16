package com.taxi.restservice.model;

public class BookingInput {
    private Coordinate source;
    private Coordinate destination;

    public BookingInput(Coordinate source, Coordinate destination) {
        this.source = source;
        this.destination = destination;
    }

    public Coordinate getSource() {
        return source;
    }

    public Coordinate getDestination() {
        return destination;
    }
}
