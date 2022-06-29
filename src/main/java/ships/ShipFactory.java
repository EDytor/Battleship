package ships;

import coordinates.Coordinates;
import ships.implementations.*;
import utils.Pair;

public class ShipFactory {
    public static Ship produceShip(String nameOfShip, Pair<Coordinates> pairOfCoords) {
        switch (nameOfShip) {
            case "Destroyer":
                return new Destroyer(pairOfCoords.getFirstElement(), pairOfCoords.getSecondElement());
            case "Cruiser":
                return new Cruiser(pairOfCoords.getFirstElement(), pairOfCoords.getSecondElement());
            case "Submarine":
                return new Submarine(pairOfCoords.getFirstElement(), pairOfCoords.getSecondElement());
            case "Battleship":
                return new Battleship(pairOfCoords.getFirstElement(), pairOfCoords.getSecondElement());
            case "Aircraft Carrier":
                return new Aircraft(pairOfCoords.getFirstElement(), pairOfCoords.getSecondElement());
            default:
                throw new RuntimeException("Incorrect ship details");
        }
    }
}
