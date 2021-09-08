package todoList;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ValidatorTest {
    protected String testPathToCSV = "testCSV.csv";
    protected String testTextOfAddNew = "testAll2";
    protected String[] testToCompleteIDList = {"3"};
    protected String[] testToCompleteIDList1 = {"6"};

    protected Integer testPriorityOfAddNew = null;
    protected String testCategoryOfAddNew = null;
    protected LocalDate testDueOfAddNew = null;
    protected String testCategoryToDisplay = null;

    protected String[] argsAddOnly1 = {"--csv-file", "testCSV.csv", "--add-todo","--todo-text", "testAdd1", "--completed",
            "--due", "2021-04-30", "--priority", "2", "--category", "school"};

    protected String[] argsAllOperations1 = {"--csv-file", "testCSV.csv", "--add-todo","--todo-text", "testAll1",
            "--complete-todo", "2", "--display"};
    protected String[] argsAllOperations2 = {"--csv-file", "testCSV.csv", "--add-todo","--todo-text", "testAll2",
            "--complete-todo", "3", "--display", "--show-incomplete"};
    protected String[] argsAllOperations3 = {"--csv-file", "testCSV.csv", "--add-todo","--todo-text", "testAll3",
            "--complete-todo", "4", "--display", "--sort-by-date"};

    protected String[] argsDisplayOnly1 = {"--csv-file", "testCSV.csv", "--display", "--sort-by-date", "--sort-by-priority"};
    protected String[] argsDisplayOnly2 = {"--csv-file", "testCSV.csv", "--display", "--sort-by-date", "--show-category", "school", "--show-incomplete"};

    protected String[] argsDisplayOnlyException = {"--csv-file", "testCSV.csv", "--display", "--sort-by-date", "--sort-by-priority"};
    protected String[] argsNoCSVException = {"--display", "--sort-by-date", "--sort-by-priority"};
    protected String[] argsNoTextException = {"--csv-file", "testCSV.csv","--add-todo", "--display", "--sort-by-date", "--sort-by-priority"};

    protected String[] argsCompleteOnly = {"--csv-file", "testCSV.csv", "--complete-todo", "6"};

    protected Validator testValidatorArgsAddOnly1;
    protected Validator testValidatorArgsAddOnly2;
    protected Validator testValidatorArgsAddOnly3;
    protected Validator testValidatorArgsAllOperations1;
    protected Validator testValidatorArgsAllOperations2;
    protected Validator testValidatorArgsAllOperations3;
    protected Validator testValidatorArgsDisplayOnly1;
    protected Validator testValidatorArgsDisplayOnly2;
    protected Validator testValidatorArgsDisplayOnlyException;
    protected Validator testValidatorArgsNoCSVException;
    protected Validator testValidatorArgsNoTextException;

    protected Validator testValidatorArgsCompleteOnly;

    protected CommandLineParser testParserArgsAddOnly1;
    protected CommandLineParser testParserArgsAddOnly2;
    protected CommandLineParser testParserArgsAddOnly3;
    protected CommandLineParser testParserArgsAllOperations1;
    protected CommandLineParser testParserArgsAllOperations2;
    protected CommandLineParser testParserArgsAllOperations3;
    protected CommandLineParser testParserArgsDisplayOnly1;
    protected CommandLineParser testParserArgsDisplayOnly2;
    protected CommandLineParser testParserArgsDisplayOnlyException;
    protected CommandLineParser testParserArgsNoCSVException;
    protected CommandLineParser testParserArgsNoTextException;

    protected CommandLineParser testParserArgsCompleteOnly;

    @Before
    public void setUp() throws Exception {
        testParserArgsAddOnly1 = new CommandLineParser(argsAddOnly1);
        testParserArgsAddOnly2 = new CommandLineParser(argsAddOnly1);
        testParserArgsAddOnly3 = new CommandLineParser(argsAddOnly1);

        testParserArgsAllOperations1 = new CommandLineParser(argsAllOperations1);
        testParserArgsAllOperations2 = new CommandLineParser(argsAllOperations2);
        testParserArgsAllOperations3 = new CommandLineParser(argsAllOperations3);
        testParserArgsDisplayOnly1 = new CommandLineParser(argsDisplayOnly1);
        testParserArgsDisplayOnly2 = new CommandLineParser(argsDisplayOnly2);
        testParserArgsDisplayOnlyException = new CommandLineParser(argsDisplayOnlyException);
        testParserArgsNoCSVException = new CommandLineParser(argsNoCSVException);
        testParserArgsNoTextException = new CommandLineParser(argsNoTextException);

        testParserArgsCompleteOnly = new CommandLineParser(argsCompleteOnly);

        testValidatorArgsAddOnly1 = new Validator(testParserArgsAddOnly1);
        testValidatorArgsAddOnly2 = new Validator(testParserArgsAddOnly1);
        testValidatorArgsAddOnly3 = new Validator(testParserArgsAddOnly1);
        testValidatorArgsAllOperations1 = new Validator(testParserArgsAllOperations1);
        testValidatorArgsAllOperations2 = new Validator(testParserArgsAllOperations2);
        testValidatorArgsAllOperations3 = new Validator(testParserArgsAllOperations3);
        testValidatorArgsDisplayOnly1 = new Validator(testParserArgsDisplayOnly1);
        testValidatorArgsDisplayOnly2 = new Validator(testParserArgsDisplayOnly2);
        testValidatorArgsDisplayOnlyException = new Validator(testParserArgsDisplayOnlyException);
        testValidatorArgsNoCSVException = new Validator(testParserArgsNoCSVException);
        testValidatorArgsNoTextException = new Validator(testParserArgsNoTextException);

        testValidatorArgsCompleteOnly = new Validator(testParserArgsCompleteOnly);
    }

    @Test
    public void getBooleansAddOnly() {
        testValidatorArgsAddOnly1.getBooleans();
        assertEquals(true, testValidatorArgsAddOnly1.addNew);
        assertEquals(true, testValidatorArgsAddOnly1.completedAddNew);
        assertEquals(false, testValidatorArgsAddOnly1.display);
        assertEquals(false, testValidatorArgsAddOnly1.displayIncomplete);
        assertEquals(false, testValidatorArgsAddOnly1.displayCategory);
        assertEquals(false, testValidatorArgsAddOnly1.displaySortedByDue);
        assertEquals(false, testValidatorArgsAddOnly1.displaySortedByPriority);
    }

    @Test
    public void getBooleansAllOperations() {
        testValidatorArgsAllOperations1.getBooleans();
        assertEquals(true, testValidatorArgsAllOperations1.addNew);
        assertEquals(false, testValidatorArgsAllOperations1.completedAddNew);
        assertEquals(true, testValidatorArgsAllOperations1.display);
        assertEquals(false, testValidatorArgsAllOperations1.displayIncomplete);
        assertEquals(false, testValidatorArgsAllOperations1.displayCategory);
        assertEquals(false, testValidatorArgsAllOperations1.displaySortedByDue);
        assertEquals(false, testValidatorArgsAllOperations1.displaySortedByPriority);
    }

    @Test
    public void getStringsAllOperations() {
        testValidatorArgsAllOperations2.getStrings();
        assertEquals(testPathToCSV, testValidatorArgsAllOperations2.pathToCSV);
        assertEquals(testTextOfAddNew, testValidatorArgsAllOperations2.textOfAddNew);
        assertEquals(testToCompleteIDList, testValidatorArgsAllOperations2.toCompleteIDList);
        assertEquals(testPriorityOfAddNew, testValidatorArgsAllOperations2.priorityOfAddNew);
        assertEquals(testCategoryOfAddNew, testValidatorArgsAllOperations2.categoryOfAddNew);
        assertEquals(testDueOfAddNew, testValidatorArgsAllOperations2.dueOfAddNew);
        assertEquals(testCategoryToDisplay, testValidatorArgsAllOperations2.categoryToDisplay);
    }

    @Test
    public void getStringsCompleteOnly() {
        testValidatorArgsCompleteOnly.getStrings();
        assertEquals(testToCompleteIDList1, testValidatorArgsCompleteOnly.toCompleteIDList);

    }

    @Test (expected = WrongArgumentsException.class)
    public void validateLogicsSortByDateAndPriority() {
        testValidatorArgsDisplayOnlyException.validateLogics();
    }

    @Test (expected = WrongArgumentsException.class)
    public void validateLogicsNoCSVException() {
        testValidatorArgsNoCSVException.validateLogics();
    }

    @Test (expected = WrongArgumentsException.class)
    public void validateLogicsNoTextException() {
        testValidatorArgsNoTextException.validateLogics();
    }
    @Test
    public void testEqualsReflexivity() {
        assertTrue(testValidatorArgsAddOnly1.equals(testValidatorArgsAddOnly1));
    }

    @Test
    public void testEqualsSymmetry() {
        assertTrue(testValidatorArgsAddOnly1.equals(testValidatorArgsAddOnly2) == testValidatorArgsAddOnly2.equals(testValidatorArgsAddOnly1));
    }

    @Test
    public void testEqualsTransitivity() {
        assertTrue(testValidatorArgsAddOnly1.equals(testValidatorArgsAddOnly2) && testValidatorArgsAddOnly2.equals(testValidatorArgsAddOnly3) && testValidatorArgsAddOnly3.equals(testValidatorArgsAddOnly1));
    }

    @Test
    public void testEqualsNullReference() {
        assertFalse(testValidatorArgsAddOnly1.equals(null));
    }

    @Test
    public void testHashCode() {
        assertFalse(0 == testValidatorArgsAddOnly1.hashCode());
    }

    @Test
    public void testHashCodeConsistency() {
        int testHashCode = testValidatorArgsAddOnly1.hashCode();
        assertEquals(testHashCode, testValidatorArgsAddOnly1.hashCode());
    }

    @Test
    public void testHashCodeConsistency2() {
        assertTrue(testValidatorArgsAddOnly1.equals(testValidatorArgsAddOnly2) == (testValidatorArgsAddOnly1.hashCode() == testValidatorArgsAddOnly2.hashCode()));
    }

    @Test
    public void testToString() {
        assertEquals("Validator{commandLineParser=CommandLineParser" +
                "{parsedArgs={--csv-file=testCSV.csv, --add-todo=null, --due=2021-04-30, --completed=null, --priority=2, --category=school, --todo-text=testAdd1}}, " +
                "addNew=false, completedAddNew=false, display=false, displayIncomplete=false, displayCategory=false, displaySortedByDue=false, displaySortedByPriority=false, " +
                "pathToCSV='null', textOfAddNew='null', toCompleteIDList=null, priorityOfAddNew=null, categoryOfAddNew='null', dueOfAddNew=null, categoryToDisplay='null'}", testValidatorArgsAddOnly1.toString());
    }
}