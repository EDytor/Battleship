package game;

import coordinates.Column;
import coordinates.Coordinates;
import coordinates.Row;
import ships.ShipFactory;
import utils.Field;
import utils.Pair;

import java.util.Arrays;
import java.util.Scanner;

public class Board {
    int size = 11;
    Field[][] board;

    Board() {
        board = new Field[size][size];
        for (Field[] row : board) {
            Arrays.fill(row, Field.FREE);
        }
        for (int i = 0; i < size; i++) {
            board[0][i] = Field.EXCLUDED;
            board[i][0] = Field.EXCLUDED;
        }
    }

    public void printingBoard() {
        int column = 1;
        char a = 'A';
        System.out.print(" ");
        for (int i = 1; i < size; i++) {
            System.out.print(" " + column);
            column++;
        }
        System.out.println();

        for (int line = 1; line < size; line++) {
            for (int i = 0; i < size; i++) {
                if (board[line][i] == Field.FREE || board[line][i] == Field.BLOCKED) {
                    System.out.print("~ ");
                } else if (board[line][i] == Field.MISSED) {
                    System.out.print("M ");
                } else if (board[line][i] == Field.HIT) {
                    System.out.print("X ");
                } else if (board[line][i] == Field.SHIP) {
                    System.out.print("O ");
                } else if (board[line][i] == Field.EXCLUDED) {
                    System.out.print(a + " ");
                    a++;
                }
            }
            System.out.println();
        }
    }

    private Pair<Coordinates> readCoordinates() {
        Scanner scanner = new Scanner(System.in);
        String enterCoordinate = scanner.next();
        enterCoordinate = enterCoordinate.trim();
        enterCoordinate = enterCoordinate. replace(" ","");
        enterCoordinate = enterCoordinate.toUpperCase();
        Row row = Row.valueOf(enterCoordinate.substring(0, 1));
        Column column = new Column(Integer.parseInt(enterCoordinate.substring(1)));
        Coordinates coordinate1 = new Coordinates(row, column);
        String enterCoordinate2 = scanner.next();
        scanner.nextLine();
        enterCoordinate2 = enterCoordinate2.trim();
        enterCoordinate2 = enterCoordinate2. replace(" ","");
        enterCoordinate2 = enterCoordinate2.toUpperCase();
        Row row2 = Row.valueOf(enterCoordinate2.substring(0, 1));
        Column column2 = new Column(Integer.parseInt(enterCoordinate2.substring(1)));
        Coordinates coordinate2 = new Coordinates(row2, column2);
        return new Pair<>(coordinate1, coordinate2);
    }

    public void deployShips() {
        String[] namesOfShips = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
        int[] shipsLength = {5, 4, 3, 3, 2};
        for (int shipsToDeploy = 0; shipsToDeploy < 5; ) {
            System.out.println();
            System.out.printf("Enter the coordinates of the %s (%d cells):%n", namesOfShips[shipsToDeploy], shipsLength[shipsToDeploy]);
            System.out.println();
            try {
                Pair<Coordinates> coordinates = readCoordinates();
                ShipFactory.produceShip(namesOfShips[shipsToDeploy], coordinates);
                deployOnTheBoard(coordinates);
            } catch (Throwable e) {
                System.out.println();
                System.out.println("Error! Wrong length of the " + namesOfShips[shipsToDeploy] + "! Try again:");
                System.out.println();
                continue;
            }
            shipsToDeploy++;
            printingBoard();
            System.out.println();
        }
    }

    private boolean ifExist(int x1, int y1) {
        return x1 < 10 && y1 < 10 && x1 > 0 && y1 > 0;
    }

    private void deployOnTheBoard(Pair<Coordinates> coordinatesPair) {

        int x1, x2, y1, y2;
        if (coordinatesPair.getFirstElement().x.getValue() < coordinatesPair.getSecondElement().x.getValue()) {
            x1 = coordinatesPair.getFirstElement().x.getValue();
            x2 = coordinatesPair.getSecondElement().x.getValue();
        } else {
            x1 = coordinatesPair.getSecondElement().x.getValue();
            x2 = coordinatesPair.getFirstElement().x.getValue();
        }
        if (coordinatesPair.getFirstElement().y.getValue() < coordinatesPair.getSecondElement().y.getValue()) {
            y1 = coordinatesPair.getFirstElement().y.getValue();
            y2 = coordinatesPair.getSecondElement().y.getValue();
        } else {
            y1 = coordinatesPair.getSecondElement().y.getValue();
            y2 = coordinatesPair.getFirstElement().y.getValue();
        }
        if (x1 == x2 && y1 != y2) {
            for (int i = y1; i <= y2; i++) {
                if (board[x1][i] != Field.FREE) {
                    throw new RuntimeException("Error! Wrong ship location! Try again:");
                }
            }
            for (int k = y1; k <= y2; k++) {
                board[x1][k] = Field.SHIP;
                if (ifExist(x1 - 1, k)) {
                    board[x1 - 1][k] = Field.BLOCKED;
                }
                if (ifExist(x1 + 1, k)) {
                    board[x1 + 1][k] = Field.BLOCKED;
                }
            }
            if (ifExist(x1 - 1, y1 - 1)) {
                board[x1 - 1][y1 - 1] = Field.BLOCKED;
            }
            if (ifExist(x1 + 1, y1 - 1)) {
                board[x1 + 1][y1 - 1] = Field.BLOCKED;
            }
            if (ifExist(x1 - 1, y2 + 1)) {
                board[x1 - 1][y2 + 1] = Field.BLOCKED;
            }
            if (ifExist(x1 + 1, y2 + 1)) {
                board[x1 + 1][y2 + 1] = Field.BLOCKED;
            }
            if (ifExist(x1, y1 - 1)) {
                board[x1][y2 - 1] = Field.BLOCKED;
            }
            if (ifExist(x1, y2 + 1)) {
                board[x1][y2 + 1] = Field.BLOCKED;
            }

        } else if (y1 == y2 && x1 != x2) {
            for (int i = x1; i <= x2; i++) {
                if (board[i][y1] != Field.FREE) {
                    throw new RuntimeException("Error! Wrong ship location! Try again:");
                }
            }
            for (int k = x1; k <= x2; k++) {
                board[k][y1] = Field.SHIP;
                if (ifExist(k, y1 - 1)) {
                    board[x1 - 1][k] = Field.BLOCKED;
                }
                if (ifExist(k, y1 + 1)) {
                    board[x1 + 1][k] = Field.BLOCKED;
                }
            }
            if (ifExist(x1 - 1, y1 - 1)) {
                board[x1 - 1][y1 - 1] = Field.BLOCKED;
            }
            if (ifExist(x1 - 1, y1 + 1)) {
                board[x1 + 1][y1 - 1] = Field.BLOCKED;
            }
            if (ifExist(x2 + 1, y1 + 1)) {
                board[x1 - 1][y2 + 1] = Field.BLOCKED;
            }
            if (ifExist(x2 + 1, y2 - 1)) {
                board[x1 + 1][y2 + 1] = Field.BLOCKED;
            }
            if (ifExist(x2 + 1, y1)) {
                board[x1 - 1][y2] = Field.BLOCKED;
            }
            if (ifExist(x1 - 1, y1)) {
                board[x2 + 1][y2] = Field.BLOCKED;
            }
        } else {
            throw new RuntimeException("Error! Wrong ship location! Try again:");
        }
    }
}
