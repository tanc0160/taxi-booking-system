package com.taxi.restservice.model;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Coordinate)) {
            return false;
        }
        final Coordinate c = (Coordinate) o;
        return c.getY() == y
                && c.getX() == x;
    }
}
