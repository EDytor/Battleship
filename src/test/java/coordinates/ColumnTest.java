package coordinates;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ColumnTest {
    @Test
    void shouldThrowExceptionWhenColumnToHigh() {
        // Given
        final int tooHighColumn = 11;
        // When
        // Then
        Assertions.assertThatThrownBy(() -> new Column(tooHighColumn))
                .as("Should throw exception!")
                .hasMessageContaining("Error! Wrong ship location! Try again:");
    }
    @Test
    void shouldThrowExceptionWhenColumnToLow() {
        // Given
        final int tooLowColumn = 0;
        // When
        // Then
        Assertions.assertThatThrownBy(() -> new Column(tooLowColumn))
                .as("Should throw exception!")
                .hasMessageContaining("Error! Wrong ship location! Try again:");
    }
}