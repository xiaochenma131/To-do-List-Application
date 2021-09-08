package todoList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandLineParserTest {
    protected String[] argsNotEnoughArgsException = {"--sort-by-priority"};
    protected String[] argsUnknownException = {"-sort-by-priority"};
    protected String[] argsMissingException = {"--sort-by-category"};
    protected String[] argsMissingException1 = {"--sort-by-category", "--display"};
    protected String[] argsAllOperations1 = {"--csv-file", "testCSV.csv", "--add-todo","--todo-text", "testAll1",
            "--complete-todo", "2", "--display"};

    protected CommandLineParser testParserArgsNotEnoughArgsException;
    protected CommandLineParser testParserArgsUnknownArgsException;
    protected CommandLineParser testParserArgsMissingException;
    protected CommandLineParser testParserArgsMissingException1;

    protected CommandLineParser testParserArgsAllOperation1;
    protected CommandLineParser testParserArgsAllOperation2;
    protected CommandLineParser testParserArgsAllOperation3;

    @Before
    public void setUp() throws Exception {
        testParserArgsAllOperation1 = new CommandLineParser(argsAllOperations1);
        testParserArgsAllOperation2 = new CommandLineParser(argsAllOperations1);
        testParserArgsAllOperation3 = new CommandLineParser(argsAllOperations1);
    }

    @Test (expected = WrongArgumentsException.class)
    public void setUpNotEnoughArgs() throws Exception {
        testParserArgsNotEnoughArgsException = new CommandLineParser(argsNotEnoughArgsException);
    }

    @Test (expected = WrongArgumentsException.class)
    public void setUpUnknownArgs() throws Exception {
        testParserArgsUnknownArgsException = new CommandLineParser(argsUnknownException);
    }

    @Test (expected = WrongArgumentsException.class)
    public void setUpMissingArgs() throws Exception {
        testParserArgsMissingException = new CommandLineParser(argsMissingException);
    }

    @Test (expected = WrongArgumentsException.class)
    public void setUpMissingArgs1() throws Exception {
        testParserArgsMissingException1 = new CommandLineParser(argsMissingException1);
    }

    @Test
    public void testEqualsReflexivity() {
        assertTrue(testParserArgsAllOperation1.equals(testParserArgsAllOperation1));
    }

    @Test
    public void testEqualsSymmetry() {
        assertTrue(testParserArgsAllOperation1.equals(testParserArgsAllOperation2) == testParserArgsAllOperation2.equals(testParserArgsAllOperation1));
    }

    @Test
    public void testEqualsTransitivity() {
        assertTrue(testParserArgsAllOperation1.equals(testParserArgsAllOperation2) && testParserArgsAllOperation2.equals(testParserArgsAllOperation3) && testParserArgsAllOperation3.equals(testParserArgsAllOperation1));
    }

    @Test
    public void testEqualsNullReference() {
        assertFalse(testParserArgsAllOperation1.equals(null));
    }

    @Test
    public void testHashCode() {
        assertFalse(0 == testParserArgsAllOperation1.hashCode());
    }

    @Test
    public void testHashCodeConsistency() {
        int testHashCode = testParserArgsAllOperation1.hashCode();
        assertEquals(testHashCode, testParserArgsAllOperation1.hashCode());
    }

    @Test
    public void testHashCodeConsistency2() {
        assertTrue(testParserArgsAllOperation1.equals(testParserArgsAllOperation2) == (testParserArgsAllOperation1.hashCode() == testParserArgsAllOperation2.hashCode()));
    }

    @Test
    public void testToString() {
        assertEquals("CommandLineParser{parsedArgs={--display=null, --csv-file=testCSV.csv, --add-todo=null, --todo-text=testAll1, --complete-todo=2}}",
                testParserArgsAllOperation1.toString());
    }
}