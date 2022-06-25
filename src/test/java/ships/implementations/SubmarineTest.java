package ships.implementations;

import coordinates.Column;
import coordinates.Coordinates;
import coordinates.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubmarineTest {
    @Test
    void shouldThrowExceptionWhenLengthOfSubmarineIsDifferent() {
        // Given
        Coordinates firstcoords = new Coordinates(Row.A,new Column(3));
        Coordinates secondcoords = new Coordinates(Row.A,new Column(4));
        // When
        // Then
        Assertions.assertThatThrownBy(() -> new Submarine(firstcoords, secondcoords))
                .as("Should throw exception when distance is not 2!")
                .hasMessageContaining("Error! Wrong length of the ship! Try again:");
    }
    @Test
    void shouldCreateObjectWhenCoordinatesAreCorrect() {
        // Given
        Coordinates firstcoords = new Coordinates(Row.B,new Column(3));
        Coordinates secondcoords = new Coordinates(Row.B,new Column(5));
        // When
        // Then
        Submarine submarine = new Submarine(firstcoords, secondcoords);
        assertEquals(Row.B, submarine.getFirstCoords().x);
        assertEquals(3, firstcoords.y.getValue());
        assertEquals(Row.B, submarine.getSecondCoords().x);
        assertEquals(5, secondcoords.y.getValue());
    }
}