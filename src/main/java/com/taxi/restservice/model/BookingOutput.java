package com.taxi.restservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingOutput {
    private int carId;
    private long totalTime;

    public BookingOutput(int carId, long totalTime) {
        this.carId = carId;
        this.totalTime = totalTime;
    }

    @JsonProperty("car_id")
    public int getCarId() {
        return carId;
    }

    @JsonProperty("total_time")
    public long getTotalTime() {
        return totalTime;
    }
}
