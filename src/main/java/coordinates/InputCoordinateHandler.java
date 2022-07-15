package coordinates;

import utils.ConsoleReader;
import utils.Pair;

public class InputCoordinateHandler {

    private final ConsoleReader consoleReader;

    public InputCoordinateHandler(ConsoleReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    protected Coordinates prepareCoordinates(String input) {
        try {
            input = input.replace(" ", "");
            input = input.toUpperCase();
            Row row = Row.valueOf(input.substring(0, 1));
            Column column = new Column(Integer.parseInt(input.substring(1)));
            return new Coordinates(row, column);
        } catch (Throwable e) {
            return null;
        }
    }

    public Coordinates returnCoordinates() {
        String input = consoleReader.readUserInput();
        Coordinates coordinates = prepareCoordinates(input);
        if (coordinates == null) {
            System.out.println();
            System.out.println("Error! Wrong ship location! Try again:");
            return returnCoordinates();
        } else {
            return coordinates;
        }
    }

    public Pair<Coordinates> pairOfCoordinates() {
        Coordinates coordinates1 = returnCoordinates();
        Coordinates coordinates2 = returnCoordinates();
        return new Pair<>(coordinates1, coordinates2);
    }
}
