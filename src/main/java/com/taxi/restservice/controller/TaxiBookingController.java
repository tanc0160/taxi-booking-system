package com.taxi.restservice.controller;

import java.util.ArrayList;
import java.util.List;

import com.taxi.restservice.model.BookingInput;
import com.taxi.restservice.model.BookingOutput;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TaxiBookingController {

    private List<BookingInput> list = new ArrayList<>();

    @GetMapping("/book")
    public List<BookingInput> greeting() {
        return list;
    }

    @PostMapping("/book")
    BookingOutput book(@RequestBody BookingInput bookingInput) {
        list.add(bookingInput);
        return new BookingOutput(1, 100);
    }

    @PutMapping("/reset")
    public void reset() {
        list = new ArrayList<>();
    }

    @PostMapping("/tick")
    int tick() {
        return list.size();
    }
}