package com.taxi.restservice.model;

public class BookingServiceOutput {

    private Car car;
    private long totalTime;

    public BookingServiceOutput(final Car car,
                                final long totalTime) {
        this.car = car;
        this.totalTime = totalTime;
    }

    public BookingOutput toBookingOutput() {
        return new BookingOutput(car.getCarId(), totalTime);
    }
}
