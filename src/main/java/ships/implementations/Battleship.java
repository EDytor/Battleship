package ships.implementations;

import coordinates.Coordinates;
import ships.Ship;

public class Battleship extends Ship {

    public Battleship(Coordinates firstCoords, Coordinates secondCoords) {
        super(firstCoords, secondCoords, 3);
    }
}
