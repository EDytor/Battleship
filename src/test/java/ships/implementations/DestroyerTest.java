package ships.implementations;

import coordinates.Column;
import coordinates.Coordinates;
import coordinates.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestroyerTest {
    @Test
    void shouldThrowExceptionWhenLengthOfDestroyerIsDifferent() {
        // Given
        Coordinates firstcoords = new Coordinates(Row.A, new Column(5));
        Coordinates secondcoords = new Coordinates(Row.C, new Column(5));
        // When
        // Then
        Assertions.assertThatThrownBy(() -> new Destroyer(firstcoords, secondcoords))
                .as("Should throw exception when distance is not 1!")
                .hasMessageContaining("Error! Wrong length of the ship! Try again:");
    }

    @Test
    void shouldCreateObjectWhenCoordinatesAreCorrect() {
        // Given
        Coordinates firstcoords = new Coordinates(Row.A, new Column(5));
        Coordinates secondcoords = new Coordinates(Row.B, new Column(5));
        // When
        // Then
        Destroyer destroyer = new Destroyer(firstcoords, secondcoords);
        assertEquals(Row.A, destroyer.getFirstCoords().x);
        assertEquals(5, firstcoords.y.getValue());
        assertEquals(Row.B, destroyer.getSecondCoords().x);
        assertEquals(5, secondcoords.y.getValue());
    }
}