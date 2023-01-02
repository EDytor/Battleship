package game;

import coordinates.Coordinates;
import utils.Field;
import utils.Pair;

import java.util.Arrays;

public class Board {
    final int size = 11;
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
                if (board[line][i] == Field.FREE) {
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
                } else if (board[line][i] == Field.BLOCKED) {
                    System.out.print("* ");
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
        x1 = getLoverValue(coordinatesPair.getFirstElement().x.getValue(), coordinatesPair.getSecondElement().x.getValue());
        x2 = getHigherValue(coordinatesPair.getFirstElement().x.getValue(), coordinatesPair.getSecondElement().x.getValue());
        y1 = getLoverValue(coordinatesPair.getFirstElement().y.getValue(), coordinatesPair.getSecondElement().y.getValue());
        y2 = getHigherValue(coordinatesPair.getFirstElement().y.getValue(), coordinatesPair.getSecondElement().y.getValue());

        if (x1 == x2 && y1 != y2) {
            for (int i = y1; i <= y2; i++) {
                if (board[x1][i] != Field.FREE) {
                    throw new RuntimeException("Error! Wrong ship location! Try again:");
                } else {
                    board[x1][i] = Field.SHIP;
                }
            }
            blockFieldsAroundTheShip(x1, y1);
        } else if (y1 == y2 && x1 != x2) {
            for (int i = x1; i <= x2; i++) {
                if (board[i][y1] != Field.FREE) {
                    throw new RuntimeException("Error! Wrong ship location! Try again:");
                } else {
                    board[i][y1] = Field.SHIP;
                }
            }
            blockFieldsAroundTheShip(x1, y1);
        } else {
            throw new RuntimeException("Error! Wrong ship location! Try again:");
        }
    }

    private int getLoverValue(int x1, int x2) {
        return Math.min(x1, x2);
    }

    private int getHigherValue(int x1, int x2) {
        return Math.max(x1, x2);
    }

    public boolean isShipSunk(int x, int y) {
        int points = 1;
        int length = checkLengthOfTheShip(x, y);
        boolean isHorizontal = isShipHorizontalOrVertical(x, y);
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
            if (points == length) {
                blockFieldsAroundTheShip(x, y);
            }
        }
        return points == length;
    }

    private boolean isShipHorizontalOrVertical(int x, int y) {
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

    private int checkLengthOfTheShip(int x, int y) {
        boolean isHorizontal = isShipHorizontalOrVertical(x, y);
        int length = 1;
        if (isHorizontal) {
            length = length + checkLengthLeft(x, y) + checkLengthRight(x, y);
        } else {
            length = length + checkLengthDown(x, y) + checkLengthUp(x, y);
        }
        return length;
    }

    private int checkLengthUp(int x, int y) {
        int length = 0;
        for (int i = 1; i < 5; i++) {
            if (ifExist(x - i, y)) {
                if (board[x - i][y] == Field.SHIP || board[x - i][y] == Field.HIT) {
                    length = length + 1;
                }
            }
        }
        return length;
    }

    private int checkLengthDown(int x, int y) {
        int length = 0;
        for (int i = 1; i < 5; i++) {
            if (ifExist(x + i, y)) {
                if (board[x + i][y] == Field.SHIP || board[x + i][y] == Field.HIT) {
                    length = length + 1;
                } else {
                    break;
                }
            }
        }
        return length;
    }

    private int checkLengthRight(int x, int y) {
        int length = 0;
        for (int i = 1; i < 5; i++) {
            if (ifExist(x, y + i)) {
                if (board[x][y + i] == Field.SHIP || board[x][y + i] == Field.HIT) {
                    length = length + 1;
                } else {
                    break;
                }
            }
        }
        return length;
    }

    private int checkLengthLeft(int x, int y) {
        int length = 0;
        for (int i = 1; i < 5; i++) {
            if (ifExist(x, y - i)) {
                if (board[x][y - i] == Field.SHIP || board[x][y - i] == Field.HIT) {
                    length = length + 1;
                } else {
                    break;
                }
            }
        }
        return length;
    }

    private int findTheBeginningOfTheShip(int x, int y) {
        int length = checkLengthOfTheShip(x, y);
        boolean isHorizontal = isShipHorizontalOrVertical(x, y);
        int up = 0;
        int left = 0;
        int beginningOfShip;
        for (int i = 1; i <= length; i++) {
            if (ifExist(x, y - i)) {
                if (getField(x, y - i) == Field.HIT || getField(x,y-i) == Field.SHIP) {
                    left++;
                }
            }
            if (ifExist(x - i, y)) {
                if (getField(x - i, y) == Field.HIT || getField(x - i,y) == Field.SHIP) {
                    up++;
                }
            }
        }
        if (isHorizontal) {
            beginningOfShip = y - left;
        } else {
            beginningOfShip = x - up;
        }
        return beginningOfShip;
    }

    public void blockFieldsAroundTheShip(int x, int y) {
        int length = checkLengthOfTheShip(x, y);
        boolean isHorizontal = isShipHorizontalOrVertical(x, y);
        int b = findTheBeginningOfTheShip(x, y);
        if (isHorizontal) {
            for (int column = -1; column <= length; column++) {
                for (int row = -1; row < 2; row++) {
                    if (ifExist(x + row, b + column) && getField(x + row, b + column) != Field.HIT && getField(x + row, b + column) != Field.SHIP) {
                        setField(x + row, b + column, Field.BLOCKED);
                    }
                }
            }
        } else {
            for (int row = -1; row <= length; row++) {
                for (int column = -1; column < 2; column++) {
                    if (ifExist(b + row, y + column) && getField(b + row, y + column) != Field.HIT && getField(b + row, y + column) != Field.SHIP) {
                        setField(b + row, y + column, Field.BLOCKED);
                    }
                }
            }

        }
    }
}


