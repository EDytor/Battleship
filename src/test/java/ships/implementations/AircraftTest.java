package ships.implementations;


import coordinates.Column;
import coordinates.Coordinates;
import coordinates.Row;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class AircraftTest {

    @Test
    void shouldThrowExceptionWhenLengthOfAircraftIsDifferent() {
        // Given
        Coordinates firstcoords = new Coordinates(Row.A,new Column(3));
        Coordinates secondcoords = new Coordinates(Row.A,new Column(8));
        // When
        // Then
        Assertions.assertThatThrownBy(() -> new Aircraft(firstcoords, secondcoords))
                .as("Should throw exception when distance is not 4!")
                .hasMessageContaining("Error! Wrong length of the ship! Try again:");
    }
    @Test
    void shouldCreateObjectWhenCoordinatesAreCorrect() {
        // Given
        Coordinates firstcoords = new Coordinates(Row.B,new Column(3));
        Coordinates secondcoords = new Coordinates(Row.B,new Column(7));
        // When
        // Then
        Aircraft aircraft = new Aircraft(firstcoords, secondcoords);
        // When
        // Then
        Aircraft aircraft1 = new Aircraft(firstcoords, secondcoords);
        assertEquals(Row.B, aircraft.getFirstCoords().x);
        assertEquals(3, firstcoords.y.getValue());
        assertEquals(Row.B, aircraft.getSecondCoords().x);
        assertEquals(7, secondcoords.y.getValue());
    }
}