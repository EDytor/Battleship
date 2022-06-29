package coordinates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utils.ConsoleReader;

import static org.junit.jupiter.api.Assertions.*;

class InputCoordinateHandlerTest {

    ConsoleReader reader;

    @BeforeEach
    void setUp() {
        this.reader = Mockito.mock(ConsoleReader.class);
    }

    @Test
    void should() {
//        given
//        Mockito.when(reader.readUserInput()).thenReturn("A1"); do testu z readerem
//        when
//        test parametryczny 2 
        Coordinates coordinates = new InputCoordinateHandler(reader).prepareCoordinates("  A1");
    }
}