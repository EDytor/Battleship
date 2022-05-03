package coordinates;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CoordinatesTest {


    @Test
    void shouldThrowExceptionWhenCoordinatesAreDifferent() {
        // Given
        Coordinates coord1 = new Coordinates(Row.A, new Column(3));
        Coordinates coord2 = new Coordinates(Row.B, new Column(5));
        // When
        // Then
        Assertions.assertThatThrownBy(() -> coord1.calculateDistanceTo(coord2))
                .as("Should throw exception when coordinates are not synchronized!")
                .hasMessageContaining("Error! Wrong ship location! Try again:");
    }

    @ParameterizedTest
    @MethodSource("correctCoordinatesProvider")
    void shouldCalculateDistanceWhenCoordinatesAreCorrect(Coordinates coords1, Coordinates coords2, int expectedDistance) {
        // When
        int distance = coords1.calculateDistanceTo(coords2);
        // Then
        Assertions.assertThat(distance)
                .as("Should calculate distance")
                .isEqualTo(expectedDistance);
    }

    private static Stream<Arguments> correctCoordinatesProvider() {
        return Stream.of(
                Arguments.of(new Coordinates(Row.A, new Column(1)), new Coordinates(Row.A, new Column(5)), 4),
                Arguments.of(new Coordinates(Row.A, new Column(1)), new Coordinates(Row.E, new Column(1)), 4)
        );
    }
}