package com.example.restservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingOutput {
    private int carId;
    private int totalTime;

    public BookingOutput(int carId, int totalTime) {
        this.carId = carId;
        this.totalTime = totalTime;
    }

    @JsonProperty("car_id")
    public int getCarId() {
        return carId;
    }

    @JsonProperty("total_time")
    public int getTotalTime() {
        return totalTime;
    }
}
