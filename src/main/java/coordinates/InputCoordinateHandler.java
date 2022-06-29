package coordinates;

import utils.ConsoleReader;

public final class InputCoordinateHandler {

    private final ConsoleReader consoleReader;

    InputCoordinateHandler(){
       this.consoleReader = ConsoleReader.INSTANCE;
    }
    InputCoordinateHandler(ConsoleReader consoleReader){
        this.consoleReader = consoleReader;
    }

    public Coordinates prepareCoordinates(String input) {


    }
}
