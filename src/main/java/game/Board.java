package game;

import coordinates.Coordinates;
import utils.Field;
import utils.Pair;

import java.util.Arrays;

public class Board {
    int size = 11;
    Field[][] board;

    public void setField(int x, int y, Field field) {
        board[x][y] = field;
    }

    public Field getField(int x, int y) {

        return board[x][y];
    }

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

    public void showTheFleet() {
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
                    System.out.print("~ ");
                } else if (board[line][i] == Field.EXCLUDED) {
                    System.out.print(a + " ");
                    a++;
                }
            }
            System.out.println();
        }
    }

    protected boolean ifExist(int x1, int y1) {
        return x1 < 11 && y1 < 11 && x1 > 0 && y1 > 0;
    }

    public void deployOnTheBoard(Pair<Coordinates> coordinatesPair) {
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
                } else {
                    board[x1][i] = Field.SHIP;
                    if (ifExist(x1 - 1, i)) {
                        board[x1 - 1][i] = Field.BLOCKED;
                    }
                    if (ifExist(x1 + 1, i)) {
                        board[x1 + 1][i] = Field.BLOCKED;
                    }
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
                board[x1][y1 - 1] = Field.BLOCKED;
            }
            if (ifExist(x1, y2 + 1)) {
                board[x1][y2 + 1] = Field.BLOCKED;
            }

        } else if (y1 == y2 && x1 != x2) {
            for (int i = x1; i <= x2; i++) {
                if (board[i][y1] != Field.FREE) {
                    throw new RuntimeException("Error! Wrong ship location! Try again:");
                } else {
                    board[i][y1] = Field.SHIP;
                    if (ifExist(i, y1 - 1)) {
                        board[i][y1 - 1] = Field.BLOCKED;
                    }
                    if (ifExist(i, y1 + 1)) {
                        board[i][y1 + 1] = Field.BLOCKED;
                    }
                }
            }
            if (ifExist(x1 - 1, y1 - 1)) {
                board[x1 - 1][y1 - 1] = Field.BLOCKED;
            }
            if (ifExist(x1 - 1, y1 + 1)) {
                board[x1 - 1][y1 + 1] = Field.BLOCKED;
            }
            if (ifExist(x2 + 1, y1 + 1)) {
                board[x2 + 1][y1 + 1] = Field.BLOCKED;
            }
            if (ifExist(x2 + 1, y2 - 1)) {
                board[x2 + 1][y2 - 1] = Field.BLOCKED;
            }
            if (ifExist(x2 + 1, y1)) {
                board[x2 + 1][y1] = Field.BLOCKED;
            }
            if (ifExist(x1 - 1, y1)) {
                board[x1 - 1][y1] = Field.BLOCKED;
            }
        } else {
            throw new RuntimeException("Error! Wrong ship location! Try again:");
        }
    }

    public boolean isShipSunk(int x, int y) {
        int points = 1;
        int length = checkLength(x, y);
        boolean isHorizontal = checkThePosition(x, y);
        if (isHorizontal) {
            for (int i = 1; i <= length; i++) {
                if (ifExist(x, y + i)) {
                    if (board[x][y + i] == Field.HIT) {
                        points = points + 1;
                    } else if (board[x][y + i] == Field.FREE || board[x][y + i] == Field.MISSED) {
                        i = length;
                    }
                }
            }
            for (int k = 1; k <= length; k++) {
                if (ifExist(x, y - k)) {
                    if (board[x][y - k] == Field.HIT) {
                        points = points + 1;
                    } else if (board[x][y - k] == Field.FREE || board[x][y - k] == Field.MISSED) {
                        k = length;
                    }
                }
            }
            return points == length;
        } else {
            for (int i = 1; i < length + 1; i++) {
                if (ifExist(x + i, y)) {
                    if (board[x + i][y] == Field.HIT) {
                        points = points + 1;
                    } else if (board[x + i][y] == Field.FREE || board[x + i][y] == Field.MISSED) {
                        i = length;
                    }
                }
            }
            for (int i = 1; i < length + 1; i++) {
                if (ifExist(x - i, y)) {
                    if (board[x - i][y] == Field.HIT) {
                        points = points + 1;
                    } else if (board[x - i][y] == Field.FREE || board[x - i][y] == Field.MISSED) {
                        i = length;
                    }
                }
            }
            return points == length;
        }
    }

    private boolean checkThePosition(int x, int y) {
        boolean isHorizontal = false;
        if (ifExist(x, y + 1)) {
            if (board[x][y + 1] == Field.SHIP || board[x][y + 1] == Field.HIT) {
                isHorizontal = true;
            }
        }
        if (ifExist(x, y - 1)) {
            if (board[x][y - 1] == Field.SHIP || board[x][y - 1] == Field.HIT) {
                isHorizontal = true;
            }
        }
        return isHorizontal;
    }

    private int checkLength(int x, int y) {
        boolean isHorizontal = checkThePosition(x, y);
        int length = 1;
        if (isHorizontal) {
            for (int i = 1; i < 5; i++) {
                if (ifExist(x, y + i)) {
                    if (board[x][y + i] == Field.SHIP || board[x][y + i] == Field.HIT) {
                        length = length + 1;
                    }
                }
                if (ifExist(x, y - i)) {
                    if (board[x][y - i] == Field.SHIP || board[x][y - i] == Field.HIT) {
                        length = length + 1;
                    }
                }
            }
        } else {
            for (int i = 1; i < 5; i++) {
                if (ifExist(x + i, y)) {
                    if (board[x + i][y] == Field.SHIP || board[x + i][y] == Field.HIT) {
                        length = length + 1;
                    }
                }
                if (ifExist(x - i, y)) {
                    if (board[x - i][y] == Field.SHIP || board[x - i][y] == Field.HIT) {
                        length = length + 1;
                    }
                }
            }
        }
        return length;
    }
}
