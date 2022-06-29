package ships.implementations;

import coordinates.Column;
import coordinates.Coordinates;
import coordinates.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleshipTest {

    @Test
    void shouldThrowExceptionWhenLengthOfBattleshipIsDifferent() {
        // Given
        Coordinates firstcoords = new Coordinates(Row.A,new Column(3));
        Coordinates secondcoords = new Coordinates(Row.A,new Column(7));
        // When
        // Then
        Assertions.assertThatThrownBy(() -> new Battleship(firstcoords, secondcoords))
                .as("Should throw exception when distance is not 3!")
                .hasMessageContaining("Error! Wrong length of the ship! Try again:");
    }
    @Test
    void shouldCreateObjectWhenCoordinatesAreCorrect() {
        // Given
        Coordinates firstcoords = new Coordinates(Row.B,new Column(3));
        Coordinates secondcoords = new Coordinates(Row.B,new Column(6));
        // When
        Battleship battleship = new Battleship(firstcoords, secondcoords);
        // Then
        assertEquals(Row.B, battleship.getFirstCoords().x);
        assertEquals(3, firstcoords.y.getValue());
        assertEquals(Row.B, battleship.getSecondCoords().x);
        assertEquals(6, secondcoords.y.getValue());
    }
}