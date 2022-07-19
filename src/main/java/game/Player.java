package game;

import coordinates.Coordinates;
import coordinates.InputCoordinateHandler;
import ships.ShipFactory;
import utils.Field;
import utils.Pair;

import static game.Engine.engine;
import static game.Engine.scanner;

public class Player {
    InputCoordinateHandler inputCoordinateHandler;

    Player(InputCoordinateHandler inputCoordinateHandler) {
        this.inputCoordinateHandler = inputCoordinateHandler;
    }

    int shipsToSink = 5;

    public void putShipsAtSea(Board board) {
        int[] shipsLength = {5, 4, 3, 3, 2};
        String[] namesOfShips = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
        int shipsToDeploy = 5;
        int i = 0;
        System.out.println();
        System.out.printf("Enter the coordinates of the %s (%d cells):%n", namesOfShips[i], shipsLength[i]);
        System.out.println();
        Pair<Coordinates> pair;
        for (i = 0; i < shipsToDeploy; ) {
            pair = inputCoordinateHandler.pairOfCoordinates();
            if (makeShip(pair, namesOfShips[i])) {
                if (checkTheSea(pair, board)) {
                    board.showTheFleet();
                    i++;
                    if (i < 5) {
                        System.out.println();
                        System.out.printf("Enter the coordinates of the %s (%d cells):%n", namesOfShips[i], shipsLength[i]);
                        System.out.println();
                    }
                }
            }
        }
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }

    private boolean makeShip(Pair<Coordinates> pair, String namesOfShips) {
        try {
            ShipFactory.produceShip(namesOfShips, pair);
        } catch (RuntimeException e) {
            System.out.println();
            System.out.println("Error! Wrong length of the " + namesOfShips + "! Try again:");
            System.out.println();
            return false;
        }
        return true;
    }

    private boolean checkTheSea(Pair<Coordinates> pair, Board board) {
        try {
            board.deployOnTheBoard(pair);
        } catch (RuntimeException e) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }
        return true;
    }

    public void shot(Board board) {
        Coordinates coordinates = inputCoordinateHandler.returnCoordinates();
        int x = coordinates.x.getValue();
        int y = coordinates.y.getValue();
        engine.cleaningConsole();
        if (board.getField(x, y) == Field.SHIP) {
            board.setField(x, y, Field.HIT);
            System.out.println("You hit a ship!");
            if (board.isShipSunk(x, y)) {
                shipsToSink--;
                System.out.println("You sank a ship!");
            }
        } else if (board.getField(x, y) == Field.FREE) {
            board.setField(x, y, Field.MISSED);
            System.out.println("You missed!");
        } else if (board.getField(x, y) == Field.BLOCKED) {
            board.setField(x, y, Field.MISSED);
            System.out.println("You missed!");
        } else if (board.getField(x, y) == Field.HIT || board.getField(x, y) == Field.MISSED) {
            System.out.println("You've already shot here!");
        }
    }}
