package ships;

import coordinates.Coordinates;

public abstract class Ship {
    protected Coordinates firstCoords;
    protected Coordinates secondCoords;

    public Ship(Coordinates firstCoords, Coordinates secondCoords, int distance) {
        this.firstCoords = firstCoords;
        this.secondCoords = secondCoords;
        if (firstCoords.calculateDistanceTo(secondCoords) != distance) {
            throw new RuntimeException("Error! Wrong length of the ship! Try again:");
        }
    }

    public Coordinates getFirstCoords() {
        return firstCoords;
    }

    public Coordinates getSecondCoords() {
        return secondCoords;
    }
}
