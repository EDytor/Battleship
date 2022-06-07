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
        assertEquals(0, cruiser.getColumnOfFirst());
        assertEquals(0, cruiser.getColumnOfSecond());
        assertEquals(0, cruiser.getRowOfFirst());
        assertEquals(2, cruiser.getRowOfSecond());
    }
}