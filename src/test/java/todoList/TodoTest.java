package todoList;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TodoTest {
    Todo finalExam;
    Todo travel;
    LocalDate may2019;
    LocalDate may2021;

    @Before
    public void setUp() throws Exception {
        may2019 = LocalDate.of(2019, 5, 1);
        may2021 = LocalDate.of(2021, 5, 1);
        finalExam = new Todo.Builder("final exam").addDue(may2021).addPriority(1).addCategory("school").build();
        travel = new Todo.Builder("go to Shanghai").addDue(may2019).addCategory("holiday").build();
    }

    @Test
    public void setCompleted() {
        finalExam.setCompleted();
        assertTrue(finalExam.getCompleted());
    }

    @Test
    public void setID() {
        finalExam.setID(1);
        assertEquals(1, finalExam.getID(), 0.0);
    }

    @Test
    public void getText() {
        assertEquals("final exam", finalExam.getText());
    }

    @Test
    public void getDue() {
        assertEquals(may2021, finalExam.getDue());
    }

    @Test
    public void getPriority() {
        assertEquals(1, finalExam.getPriority(), 0.0);
    }

    @Test
    public void getCategory() {
        assertEquals("school", finalExam.getCategory());
    }

    @Test
    public void testEquals() {
        Todo finalExam2 = new Todo.Builder("final exam").addDue(may2021).addPriority(1).addCategory("school").build();
        assertTrue(finalExam.equals(finalExam2));
        assertFalse(finalExam.equals(travel));
    }

    @Test
    public void testHashCode() {
        Todo finalExam2 = new Todo.Builder("final exam").addDue(may2021).addPriority(1).addCategory("school").build();
        assertEquals(finalExam.hashCode(), finalExam2.hashCode());
        assertFalse(finalExam.hashCode() == travel.hashCode());
    }

    @Test
    public void compareTo() {
        assertEquals(-1, finalExam.compareTo(travel));
    }
}