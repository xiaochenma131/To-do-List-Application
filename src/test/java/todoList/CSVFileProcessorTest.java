package todoList;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class CSVFileProcessorTest {
  private CSVFileProcessor testCSVFileProcessor1;
  private CSVFileProcessor testCSVFileProcessor2;
  private CSVFileProcessor testCSVFileProcessor3;

  @Before
  public void setUp() throws Exception {
    testCSVFileProcessor1 = new CSVFileProcessor("test.csv");
    testCSVFileProcessor2 = new CSVFileProcessor("test.csv");
    testCSVFileProcessor3 = testCSVFileProcessor2;
  }

  @Test
  public void getProcessedInfo() {
    assertEquals(testCSVFileProcessor1.getProcessedInfo().get(0).get(0), "id");
  }

  @Test
  public void getPath() {
    assertEquals(testCSVFileProcessor1.getPath(), "test.csv");
  }

  @Test
  public void testEqualsReflexivity() {
    assertTrue(testCSVFileProcessor1.equals(testCSVFileProcessor1));
  }

  @Test
  public void testEqualsSymmetry() {
    assertTrue(testCSVFileProcessor1.equals(testCSVFileProcessor2) == testCSVFileProcessor2.equals(testCSVFileProcessor1));
  }

  @Test
  public void testEqualsTransitivity() {
    assertTrue(testCSVFileProcessor1.equals(testCSVFileProcessor2) && testCSVFileProcessor2.equals(testCSVFileProcessor3) && testCSVFileProcessor1.equals(testCSVFileProcessor3));
  }

  @Test
  public void testEqualsNullReference() {
    assertFalse(testCSVFileProcessor1.equals(null));
  }

  @Test
  public void testHashCodeConsistency() {
    int testHashCode = testCSVFileProcessor1.hashCode();
    assertEquals(testHashCode,testCSVFileProcessor1.hashCode());
  }

  @Test
  public void testHashCode() {
    assertFalse(0 == testCSVFileProcessor1.hashCode());
  }

  @Test
  public void testHashCodeConsistency2() {
    assertTrue(testCSVFileProcessor1.equals(testCSVFileProcessor2) == (testCSVFileProcessor1.hashCode() == testCSVFileProcessor2.hashCode()));
  }

  @Test
  public void testToString() {
    assertEquals("CSVFileProcessor{processedInfo=[[id, text, completed, due, priority, category]], processedListOfHashMap=[], path='test.csv'}", testCSVFileProcessor1.toString());
  }

  @Test
  public void csvFileIsEmpty() {
    assertTrue(testCSVFileProcessor1.csvFileIsEmpty());
  }
}