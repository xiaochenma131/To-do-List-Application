package todoList;

import java.util.stream.Stream;

public class DisplaySetting {

    private boolean filterByCompleted;//filter by completed
    private String filterByCategory;//filter by category
    private boolean sortByDate;//sort by date
    private boolean sortByPriority;//sort by priority

    /**
     * Constructor
     * @param builder Builder object.
     */
    public DisplaySetting(Builder builder) {
        this.sortByPriority = builder.sortByPriority;
        this.sortByDate = builder.sortByDate;
        this.filterByCompleted = builder.filterByCompleted;
        this.filterByCategory = builder.filterByCategory;
    }

    public boolean isFilterByCompleted() {
        return this.filterByCompleted;
    }

    public String isFilterByCategory() {
        return this.filterByCategory;
    }

    public boolean isSortByDate() {
        return this.sortByDate;
    }

    public boolean isSortByPriority() {
        return this.sortByPriority;
    }

    /**
     * Process todoList stream according to builder object.
     * @param todoList original todoList stream.
     * @return a new processed todoList steam.
     */
    public Stream<Todo> buildDisplay(Stream<Todo> todoList) {//use stream to process todoList
        Stream<Todo> todoStream = todoList;
        if (this.isFilterByCompleted())
            todoStream = todoStream.filter(todo -> todo.getCompleted());
        if (this.isFilterByCategory() != null)
            todoStream = todoStream.filter(todo -> this.filterByCategory.equals(todo.getCategory()));
        if (this.isSortByDate())
            todoStream = todoStream.sorted(new DateComparator());
        if (this.isSortByPriority())
            todoStream = todoStream.sorted(new PriorityComparator());
        return todoStream;
    }

    /**
     * Inner Build class.
     */
    public static class Builder {//documentation?
        private boolean filterByCompleted;
        private String filterByCategory;
        private boolean sortByDate;
        private boolean sortByPriority;

        /**
         * Builder Constructor.
         */
        public Builder() {
            this.filterByCompleted = false;
            this.filterByCategory = null;
            this.sortByDate = false;
            this.sortByPriority = false;
        }

        public Builder sortByPriority(Boolean sortByPriority) {
            this.sortByPriority = sortByPriority;
            return this;
        }

        public Builder sortByDate(Boolean sortByDate) {
            this.sortByDate = sortByDate;
            return this;
        }

        public Builder filterByCompleted(Boolean filterByCompleted) {
            this.filterByCompleted = filterByCompleted;
            return this;
        }

        public Builder filterByCategory(String category) {
            this.filterByCategory = category;
            return this;
        }

        public DisplaySetting build() {
            return new DisplaySetting(this);
        }
    }
}
