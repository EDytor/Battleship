package game;

import utils.Field;
import utils.Pair;

import java.util.Random;

public class Computer {

    int shipsToDeploy = 5;
    int shipsToSink = 5;
    Random random = new Random();

    protected int randomizeTheNumber() {
        int number = random.nextInt(11);
        if (number == 0) {
            number = randomizeTheNumber();
        }
        return number;
    }

    private String checkTheDirection(int length, Board board, int x, int y) {
        boolean isCorrect = true;
        for (int i = 0; i < length; i++) {
            if (!board.ifExist(x, y + i) || board.getField(x, y + i) != Field.FREE) {
                isCorrect = false;
            }
        }
        if (isCorrect) {
            return "HorizontallyRight";
        } else {
            isCorrect = true;
        }
        for (int i = 0; i < length; i++) {
            if (!board.ifExist(x, y - i) || board.getField(x, y - i) != Field.FREE) {
                isCorrect = false;
            }
        }
        if (isCorrect) {
            return "HorizontallyLeft";
        } else {
            isCorrect = true;
        }
        for (int i = 0; i < length; i++) {
            if (!board.ifExist(x + i, y) || board.getField(x + i, y) != Field.FREE) {
                isCorrect = false;
            }
        }
        if (isCorrect) {
            return "VerticallyDown";
        } else {
            isCorrect = true;
        }
        for (int i = 0; i < length; i++) {
            if (!board.ifExist(x - i, y) || board.getField(x - i, y) != Field.FREE) {
                isCorrect = false;
            }
        }
        if (isCorrect) {
            return "VerticallyUp";
        }

        return "Unknown!";
    }

    private void deployShip(int length, Board board, int x, int y) {
        String direction = checkTheDirection(length, board, x, y);
        switch (direction) {
            case "HorizontallyRight":
                for (int i = 0; i < length; i++) {
                    board.setField(x, y + i, Field.SHIP);
                    if (board.ifExist(x + 1, y + i)) {
                        board.setField(x + 1, y + i, Field.BLOCKED);
                    }
                    if (board.ifExist(x - 1, y + i)) {
                        board.setField(x - 1, y + i, Field.BLOCKED);
                    }
                }
                if (board.ifExist(x - 1, y + length)) {
                    board.setField(x - 1, y + length, Field.BLOCKED);
                }
                if (board.ifExist(x + 1, y + length)) {
                    board.setField(x + 1, y + length, Field.BLOCKED);
                }
                if (board.ifExist(x - 1, y - 1)) {
                    board.setField(x - 1, y - 1, Field.BLOCKED);
                }
                if (board.ifExist(x + 1, y - 1)) {
                    board.setField(x + 1, y - 1, Field.BLOCKED);
                }
                if (board.ifExist(x, y - 1)) {
                    board.setField(x, y - 1, Field.BLOCKED);
                }
                if (board.ifExist(x, y + length)) {
                    board.setField(x, y + length, Field.BLOCKED);
                }
                break;
            case "HorizontallyLeft":
                for (int i = 0; i < length; i++) {
                    board.setField(x, y - i, Field.SHIP);
                    if (board.ifExist(x - 1, y - i)) {
                        board.setField(x - 1, y - i, Field.BLOCKED);
                    }
                    if (board.ifExist(x + 1, y - i)) {
                        board.setField(x + 1, y - i, Field.BLOCKED);
                    }
                }
                if (board.ifExist(x, y + 1)) {
                    board.setField(x, y + 1, Field.BLOCKED);
                }
                if (board.ifExist(x, y - length)) {
                    board.setField(x, y - length, Field.BLOCKED);
                }
                if (board.ifExist(x + 1, y + 1)) {
                    board.setField(x + 1, y + 1, Field.BLOCKED);
                }
                if (board.ifExist(x + 1, y - length)) {
                    board.setField(x + 1, y - length, Field.BLOCKED);
                }
                if (board.ifExist(x - 1, y + 1)) {
                    board.setField(x - 1, y + 1, Field.BLOCKED);
                }
                if (board.ifExist(x - 1, y - length)) {
                    board.setField(x - 1, y - length, Field.BLOCKED);
                }
                break;
            case "VerticallyDown":
                for (int i = 0; i < length; i++) {
                    board.setField(x + i, y, Field.SHIP);
                    if (board.ifExist(x + i, y + 1)) {
                        board.setField(x + i, y + 1, Field.BLOCKED);
                    }
                    if (board.ifExist(x + i, y - 1)) {

                        board.setField(x + i, y - 1, Field.BLOCKED);
                    }
                }
                if (board.ifExist(x - 1, y)) {
                    board.setField(x - 1, y, Field.BLOCKED);
                }
                if (board.ifExist(x + length, y)) {
                    board.setField(x + length, y, Field.BLOCKED);
                }
                if (board.ifExist(x - 1, y + 1)) {
                    board.setField(x - 1, y + 1, Field.BLOCKED);
                }
                if (board.ifExist(x - 1, y - 1)) {
                    board.setField(x - 1, y - 1, Field.BLOCKED);
                }
                if (board.ifExist(x + length, y + 1)) {
                    board.setField(x + length, y + 1, Field.BLOCKED);
                }
                if (board.ifExist(x + length, y - 1)) {
                    board.setField(x + length, y - 1, Field.BLOCKED);
                }
                break;
            case "VerticallyUp":
                for (int i = 0; i < length; i++) {
                    board.setField(x - i, y, Field.SHIP);
                    if (board.ifExist(x - i, y + 1)) {
                        board.setField(x - i, y + 1, Field.BLOCKED);
                    }
                    if (board.ifExist(x - i, y - 1)) {
                        board.setField(x - i, y - 1, Field.BLOCKED);
                    }
                }
                if (board.ifExist(x + 1, y)) {
                    board.setField(x + 1, y, Field.BLOCKED);
                }
                if (board.ifExist(x - length, y)) {
                    board.setField(x - length, y, Field.BLOCKED);
                }
                if (board.ifExist(x + 1, y + 1)) {
                    board.setField(x + 1, y + 1, Field.BLOCKED);
                }
                if (board.ifExist(x + 1, y - 1)) {
                    board.setField(x + 1, y - 1, Field.BLOCKED);
                }
                if (board.ifExist(x - length, y + 1)) {
                    board.setField(x - length, y + 1, Field.BLOCKED);
                }
                if (board.ifExist(x - length, y - 1)) {
                    board.setField(x - length, y - 1, Field.BLOCKED);
                }
                break;
            default:
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

    private Pair<Integer> checkFields(Board board) {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (board.getField(i, j) == Field.HIT) {
                    if (board.ifExist(i, j + 1)) {
                        if (board.getField(i, j + 1) != Field.MISSED && board.getField(i, j + 1) != Field.HIT && board.getField(i, j + 1) != Field.BLOCKED) {
                            return new Pair<>(i, j + 1);
                        } else if (board.getField(i, j + 1) == Field.HIT) {
                            if (board.ifExist(i, j + 2) && board.getField(i, j + 2) != Field.HIT && board.getField(i, j + 2) != Field.MISSED && board.getField(i, j + 2) != Field.BLOCKED) {
                                return new Pair<>(i, j + 2);
                            }
                        }
                    }
                    if (board.ifExist(i, j - 1)) {
                        if (board.getField(i, j - 1) != Field.MISSED && board.getField(i, j - 1) != Field.HIT && board.getField(i, j - 1) != Field.BLOCKED) {
                            return new Pair<>(i, j - 1);
                        } else if (board.getField(i, j - 1) == Field.HIT) {
                            if (board.ifExist(i, j - 2) && board.getField(i, j - 2) != Field.HIT && board.getField(i, j - 2) != Field.MISSED && board.getField(i, j - 2) != Field.BLOCKED) {
                                return new Pair<>(i, j - 2);
                            }
                        }
                    }
                    if (board.ifExist(i + 1, j)) {
                        if (board.getField(i + 1, j) != Field.MISSED && board.getField(i + 1, j) != Field.HIT && board.getField(i + 1, j) != Field.BLOCKED) {
                            return new Pair<>(i + 1, j);
                        } else if (board.getField(i + 1, j) == Field.HIT) {
                            if (board.ifExist(i + 2, j) && board.getField(i + 2, j) != Field.HIT && board.getField(i + 2, j) != Field.MISSED && board.getField(i + 2, j) != Field.BLOCKED) {
                                return new Pair<>(i + 2, j);
                            }
                        }
                    }
                    if (board.ifExist(i - 1, j)) {
                        if (board.getField(i - 1, j) != Field.MISSED && board.getField(i - 1, j) != Field.HIT && board.getField(i - 1, j) != Field.BLOCKED) {
                            return new Pair<>(i - 1, j);
                        } else if (board.getField(i - 1, j) == Field.HIT) {
                            if (board.ifExist(i - 2, j) && board.getField(i - 2, j) != Field.HIT && board.getField(i - 2, j) != Field.MISSED && board.getField(i - 2, j) != Field.BLOCKED) {
                                return new Pair<>(i - 2, j);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public void computerShot(Board board) {
        Pair<Integer> pair = checkFields(board);
        int x;
        int y;
        if (pair == null) {
            x = randomizeTheNumber();
            y = randomizeTheNumber();
        } else {
            x = pair.getFirstElement();
            y = pair.getSecondElement();
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

