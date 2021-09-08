package todoList;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import static java.lang.Integer.parseInt;

/**
 *
 */
public class Validator{
    public CommandLineParser commandLineParser;
    protected boolean addNew; //Indicate whether or not to create a new Todo.
    protected boolean completedAddNew; //Indicate whether or not The Todo is completed.
    protected boolean display; //Indicate whether or not to display Todo.
    protected boolean displayIncomplete; //Indicate whether or not to display all Todo.
    protected boolean displayCategory; //Indicate whether or not to display Todo list by category.
    protected boolean displaySortedByDue; //Indicate whether or not to display Todo list by due date.
    protected boolean displaySortedByPriority; //Indicate whether or not to display Todo list by priority.

    protected String pathToCSV; // CSV file name which stores the todos.
    protected String textOfAddNew; // A description of the Todo.
    protected String[] toCompleteIDList; // The id of the Todo.
    protected Integer priorityOfAddNew; // The priority of the Todo.
    protected String categoryOfAddNew; // The category of the Todo.
    protected LocalDate dueOfAddNew; // Path to the output directory, which store the .txt files.
    protected String categoryToDisplay;

    public Validator(CommandLineParser commandLineParser) throws WrongArgumentsException{
        this.commandLineParser = commandLineParser;
        this.addNew = false;
        this.completedAddNew = false;
        this.display = false;
        this.displayCategory = false;
        this.displayIncomplete = false;
        this.displaySortedByDue = false;
        this.displaySortedByPriority = false;

        this.pathToCSV = null;
        this.textOfAddNew = null;
        this.toCompleteIDList = null;
        this.priorityOfAddNew = null;
        this.categoryOfAddNew = null;
        this.dueOfAddNew = null;
        this.categoryToDisplay = null;
    }
    /**
     * Helper Method to put flag information into boolean variables, so that Controller can work accordingly.
     */
    protected void getBooleans() {
        Map<String, String> shortCutMap = this.commandLineParser.parsedArgs;
        this.addNew = shortCutMap.containsKey("--add-todo");
        this.completedAddNew = shortCutMap.containsKey("--completed");
        this.display = shortCutMap.containsKey("--display");
        this.displayCategory = shortCutMap.containsKey("--show-category");
        this.displayIncomplete = shortCutMap.containsKey("--show-incomplete");
        this.displaySortedByDue = shortCutMap.containsKey("--sort-by-date");
        this.displaySortedByPriority = shortCutMap.containsKey("--sort-by-priority");
    }

    /**
     * Method to put information into String variables, so that Controller can work on them.
     */
    protected void getStrings() {
        Map<String, String> shortCutMap = this.commandLineParser.parsedArgs;
        if (shortCutMap.containsKey("--csv-file")) {
            this.pathToCSV = shortCutMap.get("--csv-file");
        }
        if (shortCutMap.containsKey("--todo-text")) {
            this.textOfAddNew = shortCutMap.get("--todo-text");
        }
        if (shortCutMap.containsKey("--complete-todo")) {
            this.toCompleteIDList = shortCutMap.get("--complete-todo").split(",");
        }
        if (shortCutMap.containsKey("--priority")) {
            this.priorityOfAddNew = parseInt(shortCutMap.get("--priority"));
        }
        if (shortCutMap.containsKey("--category")) {
            this.categoryOfAddNew = shortCutMap.get("--category");
        }
        if (shortCutMap.containsKey("--due")) {
            this.dueOfAddNew = LocalDate.parse(shortCutMap.get("--due"));
        }
        if (shortCutMap.containsKey("--show-category")) {
            this.categoryToDisplay = shortCutMap.get("--show-category");
        }
    }

    /**
     * Method to check whether or not the logics of the arguments passed in are correct.
     * @return true if the logics are right, false other wise.
     */
    protected boolean validateLogics() {
        getBooleans();
        getStrings();
        if (this.pathToCSV== null) {
            throw new WrongArgumentsException("CSV file not provided!");
        }
        if (this.addNew && this.textOfAddNew == null) {
            throw new WrongArgumentsException("Description of the new Todo is not provided!");
        }
        if (this.displaySortedByPriority && this.displaySortedByDue) {
            throw new WrongArgumentsException("Cannot sort by priority and by due date at the same time!");
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Validator)) return false;
        Validator validator = (Validator) o;
        return addNew == validator.addNew && completedAddNew == validator.completedAddNew && display == validator.display && displayIncomplete == validator.displayIncomplete && displayCategory == validator.displayCategory && displaySortedByDue == validator.displaySortedByDue && displaySortedByPriority == validator.displaySortedByPriority && Objects.equals(commandLineParser, validator.commandLineParser) && Objects.equals(pathToCSV, validator.pathToCSV) && Objects.equals(textOfAddNew, validator.textOfAddNew) && Arrays.equals(toCompleteIDList, validator.toCompleteIDList) && Objects.equals(priorityOfAddNew, validator.priorityOfAddNew) && Objects.equals(categoryOfAddNew, validator.categoryOfAddNew) && Objects.equals(dueOfAddNew, validator.dueOfAddNew) && Objects.equals(categoryToDisplay, validator.categoryToDisplay);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(commandLineParser, addNew, completedAddNew, display, displayIncomplete, displayCategory, displaySortedByDue, displaySortedByPriority, pathToCSV, textOfAddNew, priorityOfAddNew, categoryOfAddNew, dueOfAddNew, categoryToDisplay);
        result = 31 * result + Arrays.hashCode(toCompleteIDList);
        return result;
    }

    @Override
    public String toString() {
        return "Validator{" +
                "commandLineParser=" + commandLineParser +
                ", addNew=" + addNew +
                ", completedAddNew=" + completedAddNew +
                ", display=" + display +
                ", displayIncomplete=" + displayIncomplete +
                ", displayCategory=" + displayCategory +
                ", displaySortedByDue=" + displaySortedByDue +
                ", displaySortedByPriority=" + displaySortedByPriority +
                ", pathToCSV='" + pathToCSV + '\'' +
                ", textOfAddNew='" + textOfAddNew + '\'' +
                ", toCompleteIDList=" + Arrays.toString(toCompleteIDList) +
                ", priorityOfAddNew=" + priorityOfAddNew +
                ", categoryOfAddNew='" + categoryOfAddNew + '\'' +
                ", dueOfAddNew=" + dueOfAddNew +
                ", categoryToDisplay='" + categoryToDisplay + '\'' +
                '}';
    }
}