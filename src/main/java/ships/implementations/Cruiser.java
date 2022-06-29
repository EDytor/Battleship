package ships.implementations;

import coordinates.Coordinates;
import ships.Ship;

public class Cruiser extends Ship {

    public Cruiser(Coordinates firstCoords, Coordinates secondCoords) {
        super(firstCoords, secondCoords, 2);
    }
}
