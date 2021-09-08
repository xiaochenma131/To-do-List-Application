package todoList;

import java.io.IOException;
import java.text.ParseException;

/**
 * This is the Main class to run the TodoList system!
 */
public class Main {
    /**
     * This is the main method to run the system.
     * @param args Command line arguments client provided.
     * @throws WrongArgumentsException If the client did not provide proper arguments in the command line.
     */
    public static void main(String[] args) throws WrongArgumentsException, IOException {
        CommandLineParser parser = new CommandLineParser(args);
        Validator validator = new Validator(parser);
        Controller.execute(validator);
    }
}
