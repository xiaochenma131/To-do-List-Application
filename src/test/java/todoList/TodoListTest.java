package todoList;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class TodoListTest {
    Todo finalExam;
    Todo travel;
//    Todo webDevelopment;
//    Todo skiing;
    Todo travel2;
    TodoList list1;
    TodoList list2;
    TodoList list3;
    TodoList list4;
    TodoList list5;


    @Before
    public void setUp() throws Exception {
        CSVFileProcessor dataBase = new CSVFileProcessor("csvFile.csv");
        CSVFileProcessor dataBase2 = new CSVFileProcessor("csvFile2.csv");
        CSVFileProcessor dataBase3 = new CSVFileProcessor("csvFile3.csv");
        CSVFileProcessor dataBase4 = new CSVFileProcessor("csvFile4.csv");
        CSVFileProcessor dataBase5 = new CSVFileProcessor("csvFile5.csv");


        LocalDate may2019 = LocalDate.of(2019, 5, 1);
        LocalDate may2021 = LocalDate.of(2021, 5, 1);


        finalExam = new Todo.Builder("final exam").addDue(may2021).addPriority(1).addCategory("school").build();
        travel = new Todo.Builder("go to Hawaii").addCategory("holiday").addDue(may2019).addPriority(3).build();
        travel2 = new Todo.Builder("go to Hawaii").addCategory("holiday").addDue(may2019).addPriority(3).build();
        list1 = new TodoList(dataBase);
        list2 = new TodoList(dataBase2);
        list3 = new TodoList(dataBase3);
        list4 = new TodoList(dataBase4);
        list5 = new TodoList(dataBase5);

    }

    @Test
    public void addTodo() throws IOException {
        list1.addTodo(travel);
        assertEquals(travel, list1.getTodo(1));
    }

    @Test
    public void setCompleted() throws IOException {
        list1.setCompleted(1);
        assertTrue(list1.getTodo(1).getCompleted());
    }

    @Test
    public void testEquals() throws IOException { // lili: every run adds one more final exam to list 2, leads to not equal to list one a second run.
        list2.addTodo(travel);
        list5.addTodo(travel);
        assertTrue(list2.equals(list5));
//        list2.addTodo(finalExam);
//        assertFalse(list1.equals(list2));
    }

    @Test
    public void testHashCode() throws IOException {// Fixed-lili
        assertTrue(list3.hashCode() == list4.hashCode());
//        list3.addTodo(finalExam);
//        assertFalse(list3.hashCode() == list4.hashCode());
    }

    @Test
    public void testToString() { // Fixed-Lili
        int size = list1.getTodoList().size();
        String expectedToString = String.format("TodoList{List Name: csvFile.csv\nTotal Items: %d}", size);
        assertEquals(expectedToString, list1.toString());
    }
}