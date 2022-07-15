package game;

import coordinates.Column;
import coordinates.Coordinates;
import coordinates.InputCoordinateHandler;
import coordinates.Row;
import org.junit.jupiter.api.Test;
import utils.ConsoleReader;
import utils.Field;
import utils.Pair;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void shouldDeployShipOnTheBoard() {
        // Given
        Board board = new Board();
        Pair<Coordinates> pair = new Pair<>(new Coordinates(Row.A, new Column(2)), new Coordinates(Row.A, new Column(3)));
        // When
        board.deployOnTheBoard(pair);
        // Then
        assertAll("Should assign fields of the Field class",
                () -> assertSame(board.getField(1, 2), Field.SHIP),
                () -> assertSame(board.getField(1, 3), Field.SHIP),
                () -> assertSame(board.getField(1, 1), Field.BLOCKED),
                () -> assertSame(board.getField(2, 1), Field.BLOCKED),
                () -> assertSame(board.getField(2, 2), Field.BLOCKED),
                () -> assertSame(board.getField(2, 3), Field.BLOCKED),
                () -> assertSame(board.getField(2, 4), Field.BLOCKED),
                () -> assertSame(board.getField(1, 4), Field.BLOCKED),
                () -> assertSame(board.getField(0, 1), Field.EXCLUDED),
                () -> assertSame(board.getField(0, 2), Field.EXCLUDED),
                () -> assertSame(board.getField(0, 3), Field.EXCLUDED),
                () -> assertSame(board.getField(0, 4), Field.EXCLUDED));
    }

    @Test
    void shouldReturnTrueWhenShipIsSunk() {
        // Given
        Board board = new Board();
        // When
        board.setField(10, 1, Field.HIT);
        board.setField(10, 2, Field.HIT);
        board.setField(10, 3, Field.HIT);
        // Then
        assertTrue(board.isShipSunk(10,3));
    }
    @Test
    void shouldReturnFalseWhenShipIsNotSunk() {
        // Given
        Board board = new Board();
        // When
        board.setField(1, 6, Field.HIT);
        board.setField(1, 7, Field.HIT);
        board.setField(1, 8, Field.SHIP);
        board.setField(1, 9, Field.HIT);
        board.setField(1, 10, Field.HIT);
        // Then
        assertFalse(board.isShipSunk(1,10));
    }
}