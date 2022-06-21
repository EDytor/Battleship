package ships;

import coordinates.Column;
import coordinates.Coordinates;
import coordinates.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ships.implementations.*;
import utils.Pair;

import java.util.stream.Stream;

class ShipFactoryTest {

    @ParameterizedTest
    @MethodSource("shipParametersProvider")
    void shouldCreateShipWhenParametersAreCorrect(Pair<Coordinates> coordinatesPair, String typeOfShip, Class<? extends Ship> clazzOfShip) {
        // Given
        // When
        Ship ship = ShipFactory.produceShip(typeOfShip, coordinatesPair);
        // Then
        Assertions.assertThat(ship).as("Factory not generated correct ship")
                .isExactlyInstanceOf(clazzOfShip);
    }

    private static Stream<Arguments> shipParametersProvider() {
        return Stream.of(
                Arguments.of(new Pair<>(
                                new Coordinates(Row.A, new Column(1)),
                                new Coordinates(Row.A, new Column(5))
                        ),
                        "Aircraft Carrier",
                        Aircraft.class
                ),
                Arguments.of(new Pair<>(
                                new Coordinates(Row.B, new Column(1)),
                                new Coordinates(Row.B, new Column(4))
                        ),
                        "Battleship",
                        Battleship.class
                ),
                Arguments.of(new Pair<>(
                                new Coordinates(Row.C, new Column(1)),
                                new Coordinates(Row.C, new Column(3))
                        ),
                        "Submarine",
                        Submarine.class
                ),
                Arguments.of(new Pair<>(
                                new Coordinates(Row.D, new Column(1)),
                                new Coordinates(Row.F, new Column(1))
                        ),
                        "Cruiser",
                        Cruiser.class
                ),
                Arguments.of(new Pair<>(
                                new Coordinates(Row.C, new Column(5)),
                                new Coordinates(Row.D, new Column(5))
                        ),
                        "Destroyer",
                        Destroyer.class
                ));
    }
}