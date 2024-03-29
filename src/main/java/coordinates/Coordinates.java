package coordinates;

import java.util.Objects;

import static java.lang.Math.abs;

public class Coordinates {
    public Row x;
    public Column y;

    public Coordinates(Row x, Column y) {
        this.x = x;
        this.y = y;
    }

    public int calculateDistanceTo(Coordinates secondCoords) {
        int distance;
        int x1 = x.getValue();
        int y1 = y.getValue();
        int x2 = secondCoords.x.getValue();
        int y2 = secondCoords.y.getValue();
        if (x1 == x2) {
            distance = abs(y2 - y1);
        } else if (y1 == y2) {
            distance = abs(x2 - x1);
        } else {
            throw new RuntimeException("Error! Wrong ship location! Try again:");
        }
        return distance;
    }

    @Override
    public boolean equals(Object coordinate) {
        if (this == coordinate) return true;
        if (coordinate == null || getClass() != coordinate.getClass()) return false;
        Coordinates that = (Coordinates) coordinate;
        return x == that.x && y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}