package pl.edu.agh.kis.pz1.util;

/**
 * Simple logger class, that also manages colors of the output.
 */
public class Logger {
    private Logger () {}

    /**
     * Method that logs a message to the console
     * @param message Message to be logged
     */
    public static void log(String message) {
        System.out.println(message);
    }

    /**
     * Method that logs a message to the console with a color
     * @param message Message to be logged
     * @param color Color of the message
     */
    public static void log(String message, ConsoleColors color) {
        System.out.println(color + message + ConsoleColors.RESET);
    }

}

/**
 * enum with colors for the console output.
 */
enum ConsoleColors{
    RED("\033[0;31m"), // RED
    GREEN("\033[0;32m"), // GREEN
    YELLOW("\033[0;33m"), // YELLOW
    BLUE("\033[0;34m"), // BLUE
    PURPLE("\033[0;35m"), // PURPLE
    CYAN("\033[0;36m"), // CYAN
    WHITE("\033[0;37m"), // WHITE
    RESET("\033[0m"); // Text Reset
    // coded color
    final String color;

    /**
     * Constructor for the enum
     * @param color coded color
     */
    ConsoleColors(String color) {
        this.color = color;
    }

    /**
     * Overriden toString method that returns a coded color
     * @return coded color
     */
    @Override
    public String toString() {
        return color;
    }
}