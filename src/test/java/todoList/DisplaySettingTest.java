package todoList;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class DisplaySettingTest {
    DisplaySetting priority;
    DisplaySetting date;
    DisplaySetting holidayFilter;
    DisplaySetting completeFilter;
    TodoList list;
    @Before
    public void setUp() throws Exception {
        CSVFileProcessor dataBase = new CSVFileProcessor("displaySettingTest.csv");

        LocalDate may2019 = LocalDate.of(2019, 5, 1);
        LocalDate may2021 = LocalDate.of(2021, 5, 1);
        LocalDate sep2021 = LocalDate.of(2021, 9, 1);
        LocalDate dec2021 = LocalDate.of(2021, 12, 15);

        Todo finalExam = new Todo.Builder("final exam").addPriority(1).addCategory("school").build();
        Todo travel = new Todo.Builder("go to Shanghai").addDue(may2019).addPriority(3).markCompleted(true).build();
        Todo webDevelopment = new Todo.Builder("learn web development").addDue(sep2021).addCategory("school").build();
        Todo skiing = new Todo.Builder("go skiing").addCategory("holiday").addDue(dec2021).addPriority(2).build();
        Todo travel2 = new Todo.Builder("go to Hawaii").addCategory("holiday").addDue(may2021).build();

        list = new TodoList(dataBase);
        list.addTodo(travel2);
        list.addTodo(travel);
        list.addTodo(skiing);
        list.addTodo(finalExam);
        list.addTodo(webDevelopment);
        list.setCompleted(4);

         priority = new DisplaySetting.Builder().sortByPriority(true).build();
         date = new DisplaySetting.Builder().sortByDate(true).build();
         holidayFilter = new DisplaySetting.Builder().filterByCategory("holiday").build();
         completeFilter = new DisplaySetting.Builder().filterByCompleted(true).build();

         //categoryPriority = new DisplaySetting.Builder().sortByPriority(true).filterByCategory("holiday").build();
         //categoryPriorComplete = new DisplaySetting.Builder().sortByPriority(true).filterByCompleted(true).filterByCategory("holiday").build();
    }

    @Test
    public void isFilterByCompleted() {
        Stream<Todo> newList = completeFilter.buildDisplay(list.getTodoList().stream());
        assertEquals(list.getTodo(4), newList.toArray()[1]);
        //newList.forEach(System.out::println);
    }

    @Test
    public void isFilterByCategory() {
        Stream<Todo> newList = holidayFilter.buildDisplay(list.getTodoList().stream());
        assertEquals(list.getTodo(1), newList.toArray()[0]);
    }

    @Test
    public void isSortByDate() {
        Stream<Todo> newList = date.buildDisplay(list.getTodoList().stream());
        assertEquals(list.getTodo(2), newList.toArray()[0]);
    }

    @Test
    public void isSortByPriority() { //TODO: will fail at the third time, success at 4th, then keeps failing.?
        Stream<Todo> newList = priority.buildDisplay(list.getTodoList().stream());
        assertEquals(list.getTodo(4), newList.toArray()[0]);
    }
}