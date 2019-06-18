package com.epam.console;

public class ConsoleManager implements IConsoleManager {

    @Override
    public String readNextLine() {
        return consoleScanner.nextLine();
    }
}
