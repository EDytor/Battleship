package game;

import coordinates.InputCoordinateHandler;
import utils.ConsoleReader;

import java.util.Scanner;

public class Engine {

    static ConsoleReader consoleReader = new ConsoleReader();
    private static final Board boardOfFirstPlayer = new Board();
    private static final Board boardOfSecondPlayer = new Board();
    private static final Board boardOfComputer = new Board();
    static InputCoordinateHandler inputCoordinateHandler = new InputCoordinateHandler(consoleReader);
    static Player player1 = new Player(inputCoordinateHandler);
    static Player player2 = new Player(inputCoordinateHandler);
    static Computer computer = new Computer();
    static Scanner scanner = new Scanner(System.in);
    static Engine engine = new Engine();

    public static void main(String[] args) {
        System.out.println("Hello!");
        System.out.println("Welcome to Battleship game!");
        System.out.println();
        System.out.println("If you want to play with computer enter: C");
        System.out.println("If you want to play with another player enter: P");
        String input = scanner.nextLine();
        if (input.contains("P") || input.contains("p")) {
            engine.playWithAnotherPlayer();
        } else {
            engine.playWithComputer();
        }
    }

    public void cleaningConsole() {
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
    }

    public void playWithAnotherPlayer() {
        System.out.println();
        System.out.println("Player 1, place your ships on the game field!");
        System.out.println();
        boardOfFirstPlayer.showTheFleet();
        player1.putShipsAtSea(boardOfFirstPlayer);
        engine.cleaningConsole();
        System.out.println("Player 2, place your ships on the game field!");
        boardOfSecondPlayer.showTheFleet();
        player2.putShipsAtSea(boardOfSecondPlayer);
        engine.cleaningConsole();
        for (; ; ) {
            engine.takeAShot(player1, boardOfFirstPlayer, boardOfSecondPlayer, 1);
            System.out.println("Your board:");
            System.out.println();
            boardOfFirstPlayer.printingBoard();
            if (engine.checkWinner()) {
                break;
            } else {
                System.out.println("------------------------------------------------------------------");
                System.out.println("Press Enter and pass the move to another player");
                scanner.nextLine();
            }
            engine.cleaningConsole();
            engine.takeAShot(player2, boardOfSecondPlayer, boardOfFirstPlayer, 2);
            System.out.println("Your board:");
            System.out.println();
            boardOfSecondPlayer.printingBoard();
            if (engine.checkWinner()) {
                break;
            } else {
                System.out.println("------------------------------------------------------------------");
                System.out.println("Press Enter and pass the move to another player");
                scanner.nextLine();
            }
            engine.cleaningConsole();
        }
    }

    public void playWithComputer() {
        System.out.println();
        System.out.println("Player 1, place your ships on the game field!");
        System.out.println();
        boardOfFirstPlayer.showTheFleet();
        player1.putShipsAtSea(boardOfFirstPlayer);
        computer.deployFleet(boardOfComputer);
        engine.cleaningConsole();
        for (; ; ) {
            engine.takeAShot(player1, boardOfFirstPlayer, boardOfComputer, 1);
            if (engine.checkWinner()) {
                break;
            } else {
                System.out.println("------------------------------------------------------------------");
                System.out.println("Press Enter and pass the move to another player");
                scanner.nextLine();
            }
            computer.computerShot(boardOfFirstPlayer);
            System.out.println("Your enemy's turn!");
            System.out.println();
            boardOfFirstPlayer.printingBoard();
            if (engine.checkWinner()) {
                break;
            } else {
                System.out.println("------------------------------------------------------------------");
                System.out.println("Press Enter and pass the move to another player");
                scanner.nextLine();
            }
            engine.cleaningConsole();
        }
    }

    private void takeAShot(Player player, Board yourBoard, Board yourEnemyBoard, int numberOfPlayer) {
        yourEnemyBoard.printingBoard();
        System.out.println();
        System.out.println("Player " + numberOfPlayer + ", it's your turn: ");
        System.out.println("Take a shot!");
        System.out.println();
        player.shot(yourEnemyBoard);
        System.out.println();
        yourEnemyBoard.printingBoard();
        System.out.println();
    }

    private boolean checkWinner() {
        if (player1.shipsToSink == 0 || player2.shipsToSink == 0) {
            System.out.println();
            System.out.println("You sank the last ship. You won. Congratulations!");
            return true;
        } else if (computer.shipsToSink == 0) {
            System.out.println();
            System.out.println("You have lost! Your opponent has sunk all the ships!");
            return true;
        } else {
            return false;
        }
    }
}

