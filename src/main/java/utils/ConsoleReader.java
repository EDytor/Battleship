package utils;

import java.util.Scanner;

public class ConsoleReader {

    public String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
