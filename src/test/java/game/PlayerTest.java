package game;

import coordinates.Column;
import coordinates.Coordinates;
import coordinates.InputCoordinateHandler;
import coordinates.Row;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.Field;
import utils.Pair;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlayerTest {

    @ParameterizedTest
    @MethodSource("shotParametersProvider")
    void shot_test(Coordinates shotCoordinates, Field field) {
        //Given
        InputCoordinateHandler mockInputCoordinatesHandler = mock(InputCoordinateHandler.class);
        when(mockInputCoordinatesHandler.returnCoordinates()).thenReturn(shotCoordinates);
        Player player = new Player(mockInputCoordinatesHandler);
        Board board = new Board();
        Pair<Coordinates> pair = new Pair<>(new Coordinates(Row.G, new Column(2)), new Coordinates(Row.G, new Column(4)));
        //When
        board.deployOnTheBoard(pair);
        player.shot(board);
        //Then
        assertSame(board.getField(shotCoordinates.x.getValue(), shotCoordinates.y.getValue()), field);
    }
    private static Stream<Arguments> shotParametersProvider() {
        return Stream.of(
                Arguments.of(new Coordinates(Row.G,new Column(2)), Field.HIT),
                Arguments.of(new Coordinates(Row.G,new Column(3)), Field.HIT),
                Arguments.of(new Coordinates(Row.G,new Column(4)), Field.HIT),
                Arguments.of(new Coordinates(Row.G,new Column(1)), Field.MISSED),
                Arguments.of(new Coordinates(Row.H,new Column(2)), Field.MISSED),
                Arguments.of(new Coordinates(Row.I,new Column(2)), Field.MISSED));
    }
}