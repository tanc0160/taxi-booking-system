package com.taxi.restservice.service;

import com.taxi.restservice.model.Coordinate;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceImpl implements DistanceService {
    public long calculateDistance(final Coordinate c1,
                                   final Coordinate c2) {
        return Math.abs(c1.getX() - c2.getX()) +
                Math.abs(c1.getY() - c2.getY());
    }
}
