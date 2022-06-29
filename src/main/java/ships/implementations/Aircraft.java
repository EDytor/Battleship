package ships.implementations;

import coordinates.Coordinates;
import ships.Ship;

public class Aircraft extends Ship {

    public Aircraft(Coordinates firstCoords, Coordinates secondCoords) {
        super(firstCoords, secondCoords, 4);
    }
}