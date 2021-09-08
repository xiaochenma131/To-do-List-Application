package todoList;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class ControllerTest {
    protected String testTextOfAddNew = "testAdd1";
    protected Integer testPriority = 2;
    protected String testCategory = "school";

    String[] argsAddOnly1 = {"--csv-file", "testCSV.csv", "--add-todo","--todo-text", "testAdd1", "--completed",
            "--due", "2021-04-30", "--priority", "2", "--category", "school"};
    Validator testValidator1;
    CommandLineParser testCommandLineParser1;

    @Before
    public void setUp() throws Exception {
        testCommandLineParser1 = new CommandLineParser(argsAddOnly1);
        testValidator1 = new Validator(testCommandLineParser1);
    }

    @Test
    public void executeAddOnly1() throws IOException {
        testValidator1.getStrings();
        Controller.execute(testValidator1);
        assertEquals(testCategory, testValidator1.categoryOfAddNew);
        assertEquals(testTextOfAddNew, testValidator1.textOfAddNew);
        assertEquals(testPriority, testValidator1.priorityOfAddNew);
    }
}