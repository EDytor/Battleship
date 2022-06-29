package game;

public class Engine {

    private static final Board boardOfFirstPlayer = new Board();
    private static final Board boardOfSecondPlayer = new Board();

    public static void main(String[] args) {
        System.out.println("Hello!");
        System.out.println("Welcome to Batlleship game!");
        System.out.println("Player 1, place your ships on the game field");
        System.out.println();
        boardOfFirstPlayer.printingBoard();
        boardOfFirstPlayer.deployShips();
    }
}
