package todoList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a CSV file processor. The processor will take in a path to the CSV file as the input
 * and return a list of list of the information stored in the CSV file.
 */
public class CSVFileProcessor {

    private List<List<String>> processedInfo;
    private List<HashMap<String, String>> processedListOfHashMap;
    private String path;
    public static final int columnIndexOfComplete = 2;

    /**
     * The constructor of the CSV file processor.
     * @param path the path to the CSV file.
     * @throws IOException throws this exception if something went wrong.
     */
    public CSVFileProcessor(String path) throws IOException {
        File tmpDir = new File(path);
        boolean exists = tmpDir.exists();
        if (!exists) {
            this.createCSVFile(path);
        }
        this.path = path;
        this.processedInfo = this.processInput(this.readFile());
        this.processedListOfHashMap = this.processInputToHashMap(this.processedInfo);
    }

    /**
     * A helper function to read the file from the provided path.
     *
     * @return a list of string with each string representing a line in the CSV file.
     */
    private List<String> readFile() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("A file was not found : " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Something went wrong! : " + e.getMessage());
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * A helper function to process the list of string from the readFile function. The function will
     * split the string into a list of string with each element representing the information enclosed
     * in double quotes.
     *
     * @param lines a list of string returned from the readFile function.
     * @return a list of list of string.
     */
    private List<List<String>> processInput(List<String> lines) {
        List<List<String>> processedInfo = new ArrayList<List<String>>();
        for (String line : lines) {
            String[] parts = line.split(",");//regex
            List<String> processedParts = new ArrayList<>();
//            System.out.println(Arrays.toString(parts));
            for (String word : parts) {
                String processedWord = word.replaceAll("\"", "");
                processedParts.add(processedWord);
            }
            processedInfo.add(processedParts);
        }
        return processedInfo;
    }

    /**
     * Process the information from the CSV file into a list of HashMap. With the information on each
     * row stored in a HashMap in the list. The keys of the HashMap are the header name of the CSV
     * file, like "first_name", "last_name" and so on. The values of the HashMap are the information
     * stored on the column of this header on a specific row.
     *
     * @param processedInfo a list of list of information from the processInput function
     * @return a list of HashMap with the information from CSV file.
     */

    private List<HashMap<String, String>> processInputToHashMap(List<List<String>> processedInfo) {
        List<HashMap<String, String>> processedListOfHashMap = new ArrayList<>();
        for (int i = 1; i < processedInfo.size(); i++) {
            HashMap<String, String> eachRecipientInfo = new HashMap<>();
            for (int j = 0; j < processedInfo.get(0).size(); j++) {
                eachRecipientInfo.put(processedInfo.get(0).get(j), processedInfo.get(i).get(j));
            }
            processedListOfHashMap.add(eachRecipientInfo);
        }
        return processedListOfHashMap;
    }

    /**
     * Gets the processedInfo of the CSVFileProcessor
     *
     * @return a list of list of string with information from the CSV file.
     */
    public List<List<String>> getProcessedInfo() {
        this.processedInfo = this.processInput(this.readFile());
        return this.processedInfo;
    }

    /**
     * Gets the processedListOfHashMap of this CSV file
     *
     * @return the processedListOfHashMap of this CSV file
     */

    public List<HashMap<String, String>> getProcessedListOfHashMap() {
        this.processedListOfHashMap = this.processInputToHashMap(this.getProcessedInfo());
        return this.processedListOfHashMap;
    }

    /**
     * Get the path to the csv file of this CSVFileProcessor.
     * @return the path to the csv file.
     */
    public String getPath() {
        return path;
    }

    /**
     * Checks if this CSVFileProcessor is equal to another CSVFileProcessor.
     *
     * @param o another CSVFileProcessor that this is compared to.
     * @return return true if this two CSVFileProcessors are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CSVFileProcessor)) {
            return false;
        }
        CSVFileProcessor processor = (CSVFileProcessor) o;
        return Objects.equals(getProcessedInfo(), processor.getProcessedInfo())
                && Objects
                .equals(getProcessedListOfHashMap(), processor.getProcessedListOfHashMap())
                && Objects.equals(getPath(), processor.getPath());
    }

    /**
     * Gets the hashCode of this CSVFileProcessor
     *
     * @return the hashCode of this CSVFileProcessor
     */
    @Override
    public int hashCode() {
        return Objects.hash(getProcessedInfo(), getProcessedListOfHashMap(), getPath());
    }

    /**
     * Gets the string expression of this CSVFileProcessor
     *
     * @return the string expression of this CSVFileProcessor
     */
    @Override
    public String toString() {
        return "CSVFileProcessor{" +
                "processedInfo=" + processedInfo +
                ", processedListOfHashMap=" + processedListOfHashMap +
                ", path='" + path + '\'' +
                '}';
    }

    /**
     * Create a new csv file.
     * @param path the path to the created csv file.
     * @throws IOException throws this exception if something went wrong.
     */
    public void createCSVFile(String path) throws IOException {
        try {
            File newFile = new File(path);
            newFile.createNewFile();
            List<String> headers = new ArrayList<>();
            headers.add("\"" + "id" + "\"");
            headers.add("\"" + "text" + "\"");
            headers.add("\"" + "completed" + "\"");
            headers.add("\"" + "due" + "\"");
            headers.add("\"" + "priority" + "\"");
            headers.add("\"" + "category" + "\"");
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true))) {
                writer.append(String.join(",", headers));
                writer.append("\n");
            } catch(IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
                e.printStackTrace();
            }
        } catch(IOException e) {
            System.out.println("An error occurred while creating a new file");
            e.printStackTrace();
        }
    }

    /**
     * A function that will update the CSV file when a Todo object is added.
     * @param newTodo the added Todo obejct
     * @throws IOException throws IOException
     */
    public void add(Todo newTodo) throws IOException {
        List<String> newRow = new ArrayList<>();
        newRow.add("\"" + newTodo.getID() + "\"");
        newRow.add("\"" + newTodo.getText() + "\"");
        newRow.add("\"" + newTodo.getCompleted() + "\"");
        if (newTodo.getDue() == null) {
            newRow.add("\"" + "?" + "\"");
        } else {
            newRow.add("\"" + newTodo.getDue() + "\"");
        }
        if (newTodo.getPriority() == null) {
            newRow.add("\"" + "?" + "\"");
        } else {
            newRow.add("\"" + newTodo.getPriority() + "\"");
        }
        if (newTodo.getCategory() == null) {
            newRow.add("\"" + "?" + "\"");
        } else {
            newRow.add("\"" + newTodo.getCategory() + "\"");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path, true))) {
            writer.append(String.join(",", newRow));
            writer.append("\n");
        } catch (FileNotFoundException e) {
            System.out.println("A file was not found : " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Something went wrong! : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This function will update the complete status of a Todo object in the CSV file
     * @param ID the ID of the Todo object whose complet status will be changed
     * @throws IOException
     */
    public void setCompleted(Integer ID) throws IOException {
        List<List<String>> processedInfo = this.getProcessedInfo();
        processedInfo.get(ID).set(columnIndexOfComplete,"true");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path))) {
            for (List<String> row : processedInfo) {
                row.set(0, ("\"" + row.get(0)));
                row.set(row.size() - 1, row.get(row.size() - 1) + "\"");
                writer.write(String.join("\",\"", row));
                writer.write("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("A file was not found : " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Something went wrong! : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Checks that if the csv file is empty or not.
     * @return true if there is no data stored in the csv file. Otherwise, false.
     */
    public boolean csvFileIsEmpty() {
        if (this.getProcessedInfo().size() <= 1) {
            return true;
        }
        return false;
    }
}