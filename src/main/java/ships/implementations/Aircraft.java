package ships.implementations;

import coordinates.Coordinates;
import ships.Ship;

public class Aircraft extends Ship {

    public Aircraft(Coordinates firstCoords, Coordinates secondCoords) {
        super(firstCoords, secondCoords, 4);
    }

    public int getRowOfFirst() {
        return firstCoords.x.getValue();
    }

    public int getColumnOfFirst() {
        return firstCoords.y.getValue();
    }

    public int getRowOfSecond() {
        return secondCoords.x.getValue();
    }

    public int getColumnOfSecond() {
        return secondCoords.y.getValue();
    }

}