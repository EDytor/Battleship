package coordinates;

import utils.ConsoleReader;
import utils.Pair;

public class InputCoordinateHandler {

    private final ConsoleReader consoleReader;

    public InputCoordinateHandler(ConsoleReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    Coordinates prepareCoordinates(String input) throws CoordinateException {
            input = input.replace(" ", "");
            input = input.toUpperCase();
        try {
            Row row = Row.valueOf(input.substring(0, 1));
            Column column = new Column(Integer.parseInt(input.substring(1)));
            return new Coordinates(row, column);
        } catch (RuntimeException e) {
            throw new CoordinateException("User input was incorrect!", e);
        }
    }

    public Coordinates readSingleCoordinate() throws CoordinateException {
        String input = consoleReader.readUserInput();
        return prepareCoordinates(input);
    }

    public Pair<Coordinates> readPairOfCoordinates() throws CoordinateException {
        Coordinates coordinates1 = readSingleCoordinate();
        Coordinates coordinates2 = readSingleCoordinate();
        return new Pair<>(coordinates1, coordinates2);
    }
}
