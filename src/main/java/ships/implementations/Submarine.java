package ships.implementations;

import coordinates.Coordinates;
import ships.Ship;

public class Submarine extends Ship {

    public Submarine(Coordinates firstCoords, Coordinates secondCoords) {
        super(firstCoords, secondCoords, 2);
    }
}
