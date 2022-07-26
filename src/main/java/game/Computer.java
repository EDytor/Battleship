package game;

import utils.Field;
import utils.Pair;

import java.util.Optional;
import java.util.Random;

public class Computer {

    final int size = 11;
    int shipsToDeploy = 5;
    int shipsToSink = 5;
    Random random = new Random();

    private int randomizeTheNumber() {
        int number = random.nextInt(size);
        if (number == 0) {
            number = randomizeTheNumber();
        }
        return number;
    }

    private int checkThePossibleDirection(int length, Board board, int x, int y) {
        boolean isCorrect = true;
        for (int i = 0; i < length; i++) {
            if (!board.ifExist(x, y + i) || board.getField(x, y + i) != Field.FREE) {
                isCorrect = false;
            }
        }
        if (isCorrect) {
            return 1;
        } else {
            isCorrect = true;
        }
        for (int i = 0; i < length; i++) {
            if (!board.ifExist(x, y - i) || board.getField(x, y - i) != Field.FREE) {
                isCorrect = false;
            }
        }
        if (isCorrect) {
            return 2;
        } else {
            isCorrect = true;
        }
        for (int i = 0; i < length; i++) {
            if (!board.ifExist(x + i, y) || board.getField(x + i, y) != Field.FREE) {
                isCorrect = false;
            }
        }
        if (isCorrect) {
            return 3;
        } else {
            isCorrect = true;
        }
        for (int i = 0; i < length; i++) {
            if (!board.ifExist(x - i, y) || board.getField(x - i, y) != Field.FREE) {
                isCorrect = false;
            }
        }
        if (isCorrect) {
            return 4;
        }
        return 5;
    }

    private void deployShip(int length, Board board, int x, int y) {
        int direction = checkThePossibleDirection(length, board, x, y);
        if (direction == 1) {
            for (int i = 0; i < length; i++) {
                board.setField(x, y + i, Field.SHIP);
            }
            board.blockFieldsAroundTheShip(x, y);
        } else if (direction == 2) {
            for (int i = 0; i < length; i++) {
                board.setField(x, y - i, Field.SHIP);
            }
            board.blockFieldsAroundTheShip(x, y);
        } else if (direction == 3) {
            for (int i = 0; i < length; i++) {
                board.setField(x + i, y, Field.SHIP);
            }
            board.blockFieldsAroundTheShip(x, y);
        } else if (direction == 4) {
            for (int i = 0; i < length; i++) {
                board.setField(x - i, y, Field.SHIP);
            }
            board.blockFieldsAroundTheShip(x, y);
        } else {
            throw new NullPointerException();
        }
    }

    public void deployFleet(Board board) {
        for (int i = 0; i < 5; ) {
            int[] length = {5, 4, 3, 3, 2};
            int x = randomizeTheNumber();
            int y = randomizeTheNumber();
            try {
                deployShip(length[i], board, x, y);
            } catch (NullPointerException e) {
                continue;
            }
            i++;
            shipsToDeploy--;
        }
    }

    private Optional<Pair<Integer>> selectFieldToShot(Board board) {
        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                if (board.getField(i, j) == Field.HIT) {
                    if (board.ifExist(i, j + 1)) {
                        if (board.getField(i, j + 1) != Field.MISSED && board.getField(i, j + 1) != Field.HIT && board.getField(i, j + 1) != Field.BLOCKED) {
                            return Optional.of(new Pair<>(i, j + 1));
                        } else if (board.getField(i, j + 1) == Field.HIT) {
                            if (board.ifExist(i, j + 2) && board.getField(i, j + 2) != Field.HIT && board.getField(i, j + 2) != Field.MISSED && board.getField(i, j + 2) != Field.BLOCKED) {
                                return Optional.of(new Pair<>(i, j + 2));
                            }
                        }
                    }
                    if (board.ifExist(i, j - 1)) {
                        if (board.getField(i, j - 1) != Field.MISSED && board.getField(i, j - 1) != Field.HIT && board.getField(i, j - 1) != Field.BLOCKED) {
                            return Optional.of(new Pair<>(i, j - 1));
                        } else if (board.getField(i, j - 1) == Field.HIT) {
                            if (board.ifExist(i, j - 2) && board.getField(i, j - 2) != Field.HIT && board.getField(i, j - 2) != Field.MISSED && board.getField(i, j - 2) != Field.BLOCKED) {
                                return Optional.of(new Pair<>(i, j - 2));
                            }
                        }
                    }
                    if (board.ifExist(i + 1, j)) {
                        if (board.getField(i + 1, j) != Field.MISSED && board.getField(i + 1, j) != Field.HIT && board.getField(i + 1, j) != Field.BLOCKED) {
                            return Optional.of(new Pair<>(i + 1, j));
                        } else if (board.getField(i + 1, j) == Field.HIT) {
                            if (board.ifExist(i + 2, j) && board.getField(i + 2, j) != Field.HIT && board.getField(i + 2, j) != Field.MISSED && board.getField(i + 2, j) != Field.BLOCKED) {
                                return Optional.of(new Pair<>(i + 2, j));
                            }
                        }
                    }
                    if (board.ifExist(i - 1, j)) {
                        if (board.getField(i - 1, j) != Field.MISSED && board.getField(i - 1, j) != Field.HIT && board.getField(i - 1, j) != Field.BLOCKED) {
                            return Optional.of(new Pair<>(i - 1, j));
                        } else if (board.getField(i - 1, j) == Field.HIT) {
                            if (board.ifExist(i - 2, j) && board.getField(i - 2, j) != Field.HIT && board.getField(i - 2, j) != Field.MISSED && board.getField(i - 2, j) != Field.BLOCKED) {
                                return Optional.of(new Pair<>(i - 2, j));
                            }
                        }
                    }
                }
            }
        }
        return Optional.empty();
    }

    public void computerShot(Board board) {
        int x;
        int y;
        if (selectFieldToShot(board).isPresent()) {
            x = selectFieldToShot(board).get().getFirstElement();
            y = selectFieldToShot(board).get().getSecondElement();
        } else {
            x = randomizeTheNumber();
            y = randomizeTheNumber();
        }
        if (board.getField(x, y) == Field.SHIP) {
            board.setField(x, y, Field.HIT);
            if (board.isShipSunk(x, y)) {
                shipsToSink--;
                System.out.println("The ship has been sunk!");
            }
        } else if (board.getField(x, y) == Field.FREE) {
            board.setField(x, y, Field.MISSED);
        } else if (board.getField(x, y) == Field.BLOCKED) {
            board.setField(x, y, Field.MISSED);
        }
    }

}

