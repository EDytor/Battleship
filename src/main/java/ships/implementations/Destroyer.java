package ships.implementations;

import coordinates.Coordinates;
import ships.Ship;

public class Destroyer extends Ship {

    public Destroyer(Coordinates firstCoords, Coordinates secondCoords) {
        super(firstCoords, secondCoords, 1);
    }
}
