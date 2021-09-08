package todoList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Class represent a TodoList.
 */
public class TodoList {
    protected CSVFileProcessor dataBase;
    protected List<Todo> todoList;

    protected static final int ID_INDEX_DIFF = 1;
    protected static final int FIRST_LINE = 1;

    protected static final int ID = 0;
    protected static final int TEXT = 1;
    protected static final int COMPLETED = 2;
    protected static final int DATE = 3;
    protected static final int PRIORITY = 4;
    protected static final int CATEGORY =5;

    protected static final int YEAR_INDEX = 0;
    protected static final int MONTH_INDEX = 1;
    protected static final int DATE_INDEX = 2;
    /**
     * Constructor
     * @param dataBase modular.
     */
    public TodoList(CSVFileProcessor dataBase) {
        this.dataBase = dataBase;
        this.todoList = new ArrayList<>();
        if(!dataBase.csvFileIsEmpty()){//build todoList from csv file
            List<List<String>> data = dataBase.getProcessedInfo();
            this.buildList(data);
        }
    }

    /**
     * Helper function to build todoObject from csv file and then add to todoList.
     * @param data List of todoObject information.
     */
    private void buildList(List<List<String>> data){
        for(int i = FIRST_LINE; i < dataBase.getProcessedInfo().size(); i++){
            List<String> cur = data.get(i);

            LocalDate curDate = null;
            if(!cur.get(DATE).equals("?")){
                String[] date = cur.get(DATE).split("-");

                curDate = LocalDate.of(Integer.parseInt(date[YEAR_INDEX]),
                        Integer.parseInt(date[MONTH_INDEX]),
                        Integer.parseInt(date[DATE_INDEX]));
            }

            Integer priority = null;
            if(!cur.get(PRIORITY).equals("?")){
                priority = Integer.valueOf(cur.get(PRIORITY));
            }

            String category = null;
            if (!cur.get(CATEGORY).equals("?")) {
                category = cur.get(CATEGORY);
            }

            Todo curTodo = new Todo.Builder(cur.get(TEXT)).addCategory(category).addPriority(priority).addDue(curDate).build();

            curTodo.setID(Integer.valueOf(cur.get(ID)));
            if (Boolean.parseBoolean(cur.get(COMPLETED))) {
                curTodo.setCompleted();
            }
            this.todoList.add(curTodo);
        }
    }

    /**
     * Add a new TodoObject to database.
     * @param todoObject a new TodoObject
     * @throws IOException throws this exception if something went wrong.
     */
    public void addTodo(Todo todoObject) throws IOException {
        int idx = this.todoList.size() + ID_INDEX_DIFF;
        todoObject.setID(idx);
        this.todoList.add(todoObject);
        dataBase.add(todoObject);
    }

    /**
     * Set the completed status of an existing TodoObject to true and update CSV file.
     * @param id todoObject id.
     * @throws IOException throws this exception if something went wrong.
     */
    public void setCompleted(Integer id) throws IOException {
        int idx = id - ID_INDEX_DIFF;
        this.todoList.get(idx).setCompleted();
        dataBase.setCompleted(id);
    }

    /**
     * Display a todoList according to display settings.
     * @param setting DisplaySetting modular.
     */
    public void display(DisplaySetting setting) {
        Stream<Todo> processedTodoList = setting.buildDisplay(this.todoList.stream());
        IDisplay display = new Display();
        display.specifiedDisplay(processedTodoList);
        }

    /**
     * Get todoList.
     * @return todoList
     */
    public List<Todo> getTodoList() {
        return todoList;
    }

    public Todo getTodo(Integer id){
        return this.todoList.get(id - ID_INDEX_DIFF);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoList)) return false;
        TodoList todoList1 = (TodoList) o;
        return Objects.equals(getTodoList(), todoList1.getTodoList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTodoList());
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "List Name: " + dataBase.getPath() + "\n" +
                "Total Items: " + this.todoList.size() +
                '}';
    }
}


