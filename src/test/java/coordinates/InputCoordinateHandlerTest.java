package coordinates;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.ConsoleReader;

import java.util.stream.Stream;

class InputCoordinateHandlerTest {

    @ParameterizedTest
    @MethodSource("correctCoordinatesProvider")
    void shouldProcessInputAndReturnCoordinates(Coordinates expectedCoordinates, String input) {
        // When
       Coordinates coordinates = new InputCoordinateHandler(new ConsoleReader()).prepareCoordinates(input);
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
    void shouldReturnNullWhenInputIsWrong(String input) {
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
}