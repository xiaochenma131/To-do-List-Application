package todoList;

import java.util.stream.Stream;

/**
 * Represent a display class.
 */
public class Display implements IDisplay{
    /**
     * Display a todoList according to display settings.
     * @param todoList a stream of todoList.
     */
    @Override
    public void specifiedDisplay(Stream<Todo> todoList) {
        todoList.forEach(System.out::println);
    }
}
