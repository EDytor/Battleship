package ships.implementations;

import coordinates.Column;
import coordinates.Coordinates;
import coordinates.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CruiserTest {

    @Test
    void shouldThrowExceptionWhenLengthOfCruiserDifferent() {
        // Given
        Coordinates firstcoords = new Coordinates(Row.A, new Column(1));
        Coordinates secondcoords = new Coordinates(Row.D, new Column(1));
        // When
        // Then
        Assertions.assertThatThrownBy(() -> new Cruiser(firstcoords, secondcoords))
                .as("Should throw exception when distance is not 3!")
                .hasMessageContaining("Error! Wrong length of the ship! Try again:");
    }

    @Test
    void shouldCreateObjectWhenCoordinatesAreCorrect() {
        // Given
        Coordinates firstcoords = new Coordinates(Row.A, new Column(1));
        Coordinates secondcoords = new Coordinates(Row.C, new Column(1));
        // When
        // Then
        Cruiser cruiser = new Cruiser(firstcoords, secondcoords);
        assertEquals(Row.A, cruiser.getFirstCoords().x);
        assertEquals(1, firstcoords.y.getValue());
        assertEquals(Row.C, cruiser.getSecondCoords().x);
        assertEquals(1, secondcoords.y.getValue());
    }
}