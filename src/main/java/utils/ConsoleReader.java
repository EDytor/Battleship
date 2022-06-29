package utils;

import java.util.Scanner;

public enum ConsoleReader {
    INSTANCE;
    public String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        return input;
    }
}
