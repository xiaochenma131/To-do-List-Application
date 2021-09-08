package todoList;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Class represent a TodoObject.
 */
public class Todo implements Comparable<Todo>{
    private static final boolean DEFAULT_COMPLETED = false;
    private static final int DEFAULT_PRIORITY = 3;
    protected Integer id;
    protected String text;
    protected Boolean completed;
    protected LocalDate due;
    protected Integer priority;
    protected String category;

    /**
     * Represents a Todo class with a number of default/optional parameters. Uses the builder pattern to handle construction.
     *
     */
    private Todo(Builder builder) {
        this.text = builder.text;
        this.completed = builder.completed;
        this.due = builder.due;
        this.priority = builder.priority;
        this.category = builder.category;
    }

    /**
     * Set the completed status of a TodoObject to true.
     */
    public void setCompleted() {
        this.completed = true;
    }

    /**
     * Set id.
     * @param id An ID generated by the system.
     */
    public void setID(Integer id) {
        this.id = id;
    }

    /**
     * Get a description of the task to be done.
     * @return a description of the task to be done.
     */
    public String getText() {
        return text;
    }

    /**
     * Get indicates whether the task is completed or incomplete.
     * @return indicates whether the task is completed or incomplete.
     */
    public Boolean getCompleted() {
        return completed;
    }

    /**
     * Get the ID.
     * @return ID.
     */
    public Integer getID() {
        return id;
    }

    /**
     * Get a due date.
     * @return a due date.
     */
    public LocalDate getDue() {
        return due;
    }

    /**
     * Get an integer indicating the priority of the todoObject.
     * @return an integer indicating the priority of the todoObject.
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Get category of the todoObject.
     * @return category of the todoObject.
     */
    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo)) return false;
        Todo todo = (Todo) o;
        //return Objects.equals(getID(), todo.getID()) && Objects.equals(getText(), todo.getText()) && Objects.equals(getCompleted(), todo.getCompleted()) && Objects.equals(getDue(), todo.getDue()) && Objects.equals(getPriority(), todo.getPriority()) && Objects.equals(getCategory(), todo.getCategory());
        return Objects.equals(getText(), todo.getText()) && Objects.equals(getDue(), todo.getDue()) && Objects.equals(getPriority(), todo.getPriority()) && Objects.equals(getCategory(), todo.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getText(), getCompleted(), getDue(), getPriority(), getCategory());
    }

    @Override
    public String toString() {
        return "Todo{" +
                "ID=" + id +
                ", text='" + text + '\'' +
                ", completed=" + completed +
                ", due=" + due +
                ", priority=" + priority +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public int compareTo(Todo o) {//compare by text
        return this.getText().compareTo(o.getText());
    }

    public static class Builder {
        // ID is not here since it's not included in the constructor but generated by the system.
        private String text;
        private Boolean completed;
        private LocalDate due;
        private Integer priority;
        private String category;

        public Builder(String text) {
            this.text = text;
            this.completed = DEFAULT_COMPLETED;
            this.priority = DEFAULT_PRIORITY;
        }

        public Builder markCompleted(boolean completed) {
            this.completed = completed;
            return this;
        }

        public Builder addDue(LocalDate due) {
            this.due = due;
            return this;
        }

        public Builder addPriority(Integer priority) {
            this.priority = priority;
            return this;
        }

        public Builder addCategory(String category) {
            this.category = category;
            return this;
        }

        public Todo build() {
            return new Todo(this);
        }
    }
}