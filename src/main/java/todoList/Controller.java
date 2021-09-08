package todoList;

import java.io.IOException;

/**
 * Controller represents a coordinator to call relevant methods according to user's requirements.
 */
public class Controller {
    /**
     * Static method to call methods according to clients requirements.
     *
     * @param validator Validator class instance.
     * @throws IOException if I/O is not working.
     */
    public static void execute(Validator validator) throws IOException {
        boolean validated = validator.validateLogics();

        if (validated) {
            CSVFileProcessor csvFileProcessor = new CSVFileProcessor(validator.pathToCSV);
            TodoList todoList = new TodoList(csvFileProcessor);
            // If --add-todo passed in
            if (validator.addNew) {
                Todo todo = new Todo.Builder(validator.textOfAddNew)
                        .markCompleted(validator.completedAddNew)
                        .addDue(validator.dueOfAddNew)
                        .addPriority(validator.priorityOfAddNew)
                        .addCategory(validator.categoryOfAddNew)
                        .build();
                todoList.addTodo(todo);
                String addMessage = String.format("Congratulations! %s command added successfully!", validator.textOfAddNew);
                System.out.println(addMessage);
            }
            // If --complete-todo passed in
            if (validator.toCompleteIDList != null) {
                for (int i = 0; i < validator.toCompleteIDList.length; i++) {
                    int currentId = Integer.parseInt(validator.toCompleteIDList[i]);
                    if (currentId < 1 || currentId > todoList.getTodoList().size()) {
                        throw new WrongArgumentsException("The ID to complete is not right!");
                    }
                    todoList.setCompleted(currentId);
                    String addMessage = String.format("Congratulations! Todo No.%d set to completed successfully!", currentId);
                    System.out.println(addMessage);
                }
            }
            // If --display passed in
            if (validator.display) {
                System.out.println("Displaying todos as requested:");
                DisplaySetting displaySetting = new DisplaySetting
                        .Builder()
                        .filterByCompleted(validator.displayIncomplete)
                        .filterByCategory(validator.categoryToDisplay)
                        .sortByDate(validator.displaySortedByDue)
                        .sortByPriority(validator.displaySortedByPriority)
                        .build();
                todoList.display(displaySetting);
            }
        }
    }
}
