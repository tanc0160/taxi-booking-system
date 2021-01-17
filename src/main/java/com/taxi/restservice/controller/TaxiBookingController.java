package com.taxi.restservice.controller;

import com.taxi.restservice.model.BookingInput;
import com.taxi.restservice.model.BookingOutput;
import com.taxi.restservice.model.BookingServiceOutput;
import com.taxi.restservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api")
public class TaxiBookingController {
    @Autowired
    private BookingService service;

    private AtomicLong curTime = new AtomicLong();


    @GetMapping("/book")
    public List<BookingInput> booking() {
        return service.getHistory();
    }

    @PostMapping("/book")
    BookingOutput book(@RequestBody BookingInput bookingInput) {
        service.addBookingRecord(bookingInput);
        return service.book(
                bookingInput.getSource(),
                bookingInput.getDestination(),
                curTime.get()
        ).map(BookingServiceOutput::toBookingOutput).orElse(null);
    }

    @PutMapping("/reset")
    public void reset() {
        service.reset();
    }

    @PostMapping("/tick")
    public void tick() {
        service.updateAllCarPositions(curTime.incrementAndGet());
    }
}