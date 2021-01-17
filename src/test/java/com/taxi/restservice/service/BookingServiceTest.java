package com.taxi.restservice.service;

import com.taxi.restservice.model.BookingServiceOutput;
import com.taxi.restservice.model.Car;
import com.taxi.restservice.model.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class BookingServiceTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private DistanceService distanceService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void book() {
        final Coordinate source = new Coordinate(2, 2);
        final Coordinate destination = new Coordinate(3, 2);
        when(distanceService.calculateDistance(
                new Coordinate(0, 0),
                source
        )).thenReturn(4L);
        when(distanceService.calculateDistance(
                source,
                destination
        )).thenReturn(1L);
        final Optional<BookingServiceOutput> output = bookingService.book(
                source,
                destination,
                1
        );
        assertTrue(output.isPresent());
        assert(output.get().getCar().getOccupyTo().orElse(0L) == 6);
        assert(output.get().toBookingOutput().getCarId() == 1);
        assert(output.get().toBookingOutput().getTotalTime() == 5);
    }

    @Test
    public void noAvailableCar() {
        final Coordinate source = new Coordinate(2, 2);
        final Coordinate destination = new Coordinate(3, 2);
        // 3 consecutive bookings
        bookingService.book(
                source,
                destination,
                1
        );
        bookingService.book(
                source,
                destination,
                1
        );
        bookingService.book(
                source,
                destination,
                1
        );
        final Optional<BookingServiceOutput> output  = bookingService.book(
                source,
                destination,
                1
        );
        assertFalse(output.isPresent());
    }

    @Test
    public void reset() {
        final Coordinate source = new Coordinate(2, 2);
        final Coordinate destination = new Coordinate(3, 2);
        bookingService.book(
                source,
                destination,
                1
        );
        bookingService.book(
                source,
                destination,
                1
        );
        final List<Car> cars = bookingService.getCars();
        assert(cars.stream().filter(c -> c.getOccupyTo().isPresent()).count() == 2);
        bookingService.reset();
        assert(cars.stream().filter(c -> c.getOccupyTo().isPresent()).count() == 0);
    }

    @Test
    public void tick() {
        final Coordinate source = new Coordinate(1, 0);
        final Coordinate destination = new Coordinate(1, 1);
        when(distanceService.calculateDistance(
                new Coordinate(0, 0),
                source
        )).thenReturn(1L);
        when(distanceService.calculateDistance(
                source,
                destination
        )).thenReturn(1L);
        bookingService.book(
                source,
                destination,
                1
        );
        List<Car> cars = bookingService.getCars();
        assert(cars.get(0).getCarId() == 1);
        assert(cars.get(0).getOccupyTo().get() == 3L);
        bookingService.updateAllCarPositions(3);
        cars = bookingService.getCars();
        Car car = cars.get(0);
        assert(car.getCarId() == 1);
        assertFalse(car.getOccupyTo().isPresent());
        assertFalse(car.getDestinationPosition().isPresent());
        assertTrue(car.getCurrentPosition().equals(new Coordinate(1, 1)));
    }
}
