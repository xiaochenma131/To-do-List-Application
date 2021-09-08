package todoList;

import java.util.stream.Stream;

/**
 * Represents a display interface.
 */
public interface IDisplay {
    /**
     * Display a todoList according to display settings.
     * @param todoList a stream of todoList.
     */
    public void specifiedDisplay(Stream<Todo> todoList);
}
