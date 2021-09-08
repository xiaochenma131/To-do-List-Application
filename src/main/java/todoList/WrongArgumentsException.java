package todoList;

public class WrongArgumentsException extends IllegalArgumentException{
    /**
     * Constructs an <code>IllegalArgumentException</code> with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public WrongArgumentsException(String s) {
        super(s);
    }
}