package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Field;

class ComputerTest {

    @Test
    void shouldDeployFiveShips() {
        // Given
        Board board = new Board();
        Computer computer = new Computer();
        // When
        System.out.println(computer.shipsToDeploy);
        computer.deployFleet(board);
        // Then
        System.out.println(computer.shipsToDeploy);
        Assertions.assertEquals(0, computer.shipsToDeploy);
    }

    @Test
    void shouldShotInCorrectField() {
        // Given
        Board board = new Board();
        Computer computer = new Computer();
        // When
        board.setField(4, 5, Field.HIT);
        board.setField(3, 5, Field.SHIP);
        board.setField(4, 6, Field.MISSED);
        board.setField(4, 4, Field.MISSED);
        board.setField(5, 5, Field.MISSED);
        computer.computerShot(board);
        // Then
        Assertions.assertEquals(Field.HIT, board.getField(3, 5));
    }
}