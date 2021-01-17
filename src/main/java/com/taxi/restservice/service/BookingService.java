package com.taxi.restservice.service;

import com.taxi.restservice.model.BookingInput;
import com.taxi.restservice.model.BookingServiceOutput;
import com.taxi.restservice.model.Coordinate;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    void addBookingRecord(final BookingInput bookingInput);
    List<BookingInput> getHistory();
    void reset();
    void updateAllCarPositions(final long asOf);
    Optional<BookingServiceOutput> book(
            final Coordinate source,
            final Coordinate destination,
            final long asOf);
}
