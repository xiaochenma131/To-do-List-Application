package todoList;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class represents a command line argument parser.
 * It parses, saves the arguments passed in by the client to a hashmap.
 */
public class CommandLineParser {
    // A map to save the validate arguments.
    private static final Map<String, Boolean> VALID_ARGS;
    static {
        // Put valid argument's name and whether or not they need a parameter as a key value pair into the map.
        VALID_ARGS = new HashMap<>();
        VALID_ARGS.put("--csv-file", true);
        VALID_ARGS.put("--add-todo", false);
        VALID_ARGS.put("--todo-text", true);
        VALID_ARGS.put("--completed", false);
        VALID_ARGS.put("--due", true);
        VALID_ARGS.put("--priority", true);
        VALID_ARGS.put("--category", true);
        VALID_ARGS.put("--complete-todo", true);
        VALID_ARGS.put("--display", false);
        VALID_ARGS.put("--show-incomplete", false);
        VALID_ARGS.put("--show-category", true);
        VALID_ARGS.put("--sort-by-date", false);
        VALID_ARGS.put("--sort-by-priority", false);
    }
    public Map<String, String> parsedArgs;

    /**
     * The constructor parses the command line arguments and save the info to the hashmap if no exception occurred,
     * so that Validator can validate requirements and Controller can call methods accordingly.
     * @param args command line arguments.
     * @throws WrongArgumentsException if required arguments are missing or weired arguments are provided.
     */
    public CommandLineParser(String[] args) throws WrongArgumentsException {
        // At least 3 arguments: --csv-file <pathToCSV> & --display.
        if (args.length < 3) {
            throw new WrongArgumentsException("Need more arguments!");
        }

        this.parsedArgs = new HashMap<>();

        for (int i = 0; i < args.length; i ++) {
            // If the argument is not in the VALID_ARGS map, throw exception.
            if (!VALID_ARGS.containsKey(args[i])) {
                String errorMessage = String.format("Ops! I don't know this %s command!", args[i]);
                throw new WrongArgumentsException(errorMessage);
            }
            // If the argument does exist in the VALID_ARGS map, move forward.
            if (VALID_ARGS.get(args[i])) {
                if (i == args.length - 1) {
                    String errorMessage = String.format("Oh no! Missing parameter for %s command!", args[i]);
                    throw new WrongArgumentsException(errorMessage);
                }
                if (args[i + 1].startsWith("--")) {
                    String errorMessage = String.format("Oh no! Missing parameter for %s command!", args[i]);
                    throw new WrongArgumentsException(errorMessage);
                }
                if (args[i].equals("--complete-todo")) {
                    String currentId = parsedArgs.get("--complete-todo");
                    // currentId = currentId == null ? args[i + 1] : currentId.concat(",").concat(args[i + 1]);
                    if (currentId == null) {
                        currentId = args[i + 1];
                    } else {
                        currentId = currentId.concat(",").concat(args[i + 1]);
                    }
                    parsedArgs.put(args[i], currentId);
                } else {
                    parsedArgs.put(args[i], args[i + 1]);
                }
                i++;
            } else {
                parsedArgs.put(args[i], null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandLineParser)) return false;
        CommandLineParser parser = (CommandLineParser) o;
        return Objects.equals(parsedArgs, parser.parsedArgs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parsedArgs);
    }

    @Override
    public String toString() {
        return "CommandLineParser{" +
                "parsedArgs=" + parsedArgs +
                '}';
    }
}