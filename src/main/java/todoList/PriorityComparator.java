package todoList;

import java.util.Comparator;

/**
 * Represents a priority comparator.
 */
public class PriorityComparator implements Comparator<Todo> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     * @throws ClassCastException   if the arguments' types prevent them from
     *                              being compared by this comparator.
     */
    @Override
    public int compare(Todo o1, Todo o2) {
        if (o1.getPriority() == null) {
            return 1;
        } else if (o2.getPriority() == null) {
            return -1;
        } else {
            return o1.getPriority().compareTo(o2.getPriority());
        }
    }
}
