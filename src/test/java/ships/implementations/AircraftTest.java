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
        assertEquals(2, aircraft.getColumnOfFirst());
        assertEquals(6, aircraft.getColumnOfSecond());
        assertEquals(1, aircraft.getRowOfFirst());
        assertEquals(1, aircraft.getRowOfSecond());
    }
}