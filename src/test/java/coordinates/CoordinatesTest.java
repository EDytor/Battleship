package coordinates;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

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
}