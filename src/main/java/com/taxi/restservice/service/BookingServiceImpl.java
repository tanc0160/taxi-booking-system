package com.taxi.restservice.service;

import com.taxi.restservice.model.BookingInput;
import com.taxi.restservice.model.BookingServiceOutput;
import com.taxi.restservice.model.Car;
import com.taxi.restservice.model.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private List<Car> cars;
    private List<BookingInput> history;

    @Autowired
    private DistanceService distanceService;

    public BookingServiceImpl() {
        cars = new ArrayList<>();
        cars.add(new Car(1));
        cars.add(new Car(2));
        cars.add(new Car(3));
        history = new ArrayList<>();
    }

    public List<Car> getCars() {
        return cars;
    }

    public void addBookingRecord(final BookingInput bookingInput) {
        history.add(bookingInput);
    }

    public List<BookingInput> getHistory() {
        return history;
    }

    public void reset() {
        history = new ArrayList<>();
        cars.forEach(Car::reset);
    }

    public void updateAllCarPositions(final long asOf) {
        for (final Car car : cars) {
            if (car.getOccupyTo().filter(t -> t == asOf).isPresent()) {
                car.reachDestination();
            }
        }
    }

    private Optional<Car> findAvailableCar(final Coordinate source) {
        // find available cars
        Car emptyCar = null;
        long minDistance = Long.MAX_VALUE;
        for (final Car car : cars) {
            if (!car.getOccupyTo().isPresent()) {
                final long distance = distanceService.calculateDistance(
                        car.getCurrentPosition(),
                        source);
                if (distance < minDistance ||
                        (distance == minDistance && emptyCar != null &&
                                car.getCarId() < emptyCar.getCarId())) {
                    emptyCar = car;
                    minDistance = distance;
                }
            }
        }
        return Optional.ofNullable(emptyCar);
    }

    public Optional<BookingServiceOutput> book(
            final Coordinate source,
            final Coordinate destination,
            final long asOf) {
        Optional<Car> availableCar = findAvailableCar(source);
        return availableCar.map(car -> {
            final long distance = distanceService.calculateDistance(car.getCurrentPosition(),
                    source) + distanceService.calculateDistance(source, destination);
            car.setOccupyTo(asOf + distance);
            car.setDestinationPosition(destination);
            return new BookingServiceOutput(car, distance);
        });
    }
}
