package coordinates;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import utils.ConsoleReader;
import utils.Pair;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputCoordinateHandlerTest {

    @ParameterizedTest
    @MethodSource("correctCoordinatesProvider")
    void shouldProcessInputAndReturnCoordinates(Coordinates expectedCoordinates, String input) throws CoordinateException {
        // Given
        ConsoleReader consoleReader = Mockito.mock(ConsoleReader.class);
        Mockito.when(consoleReader.readUserInput()).thenReturn(input);
        // When
       Coordinates coordinates = new InputCoordinateHandler(consoleReader).readSingleCoordinate();
        // Then
        Assertions.assertThat(coordinates)
                .as("Should return correct coordinates")
                .isEqualTo(expectedCoordinates);
    }

    private static Stream<Arguments> correctCoordinatesProvider() {
        return Stream.of(
                Arguments.of(new Coordinates(Row.A, new Column(2)), "A2"),
                Arguments.of(new Coordinates(Row.A, new Column(10)), "  a 10 "),
                Arguments.of(new Coordinates(Row.H, new Column(2)), "h 2 "),
                Arguments.of(new Coordinates(Row.B, new Column(1)), "b   1 ")
        );
    }

    @ParameterizedTest
    @MethodSource("incorrectInputProvider")
    void shouldReturnNullWhenInputIsWrong(String input) throws CoordinateException {
        // Then
        Assertions.assertThat((new InputCoordinateHandler(new ConsoleReader()).prepareCoordinates(input)))
                .as("Should return null when input is not correct!")
                .isEqualTo(null);

    }
    private static Stream<Arguments> incorrectInputProvider() {
        return Stream.of(
                Arguments.of(" a_fjn"),
                Arguments.of("   .,a1"),
                Arguments.of("q98 "),
                Arguments.of("z1 ")
        );
    }
    @Test
    void shouldReturnPairOfCoordinates() throws CoordinateException {
        // Given
        String input1 = "a1";
        String input2 = "a5";
        Coordinates coordinates = new Coordinates(Row.A,new Column(1));
        ConsoleReader consoleReader = Mockito.mock(ConsoleReader.class);
        Mockito.when(consoleReader.readUserInput()).thenReturn(input1, input2);
        // When
        Pair<Coordinates> pair = new InputCoordinateHandler(consoleReader).readPairOfCoordinates();
        // Then
        assertEquals(Row.A,pair.getFirstElement().x);
        assertEquals(Row.A,pair.getSecondElement().x);
        assertEquals(1,pair.getFirstElement().y.getValue());
        assertEquals(5, pair.getSecondElement().y.getValue());
    }
}