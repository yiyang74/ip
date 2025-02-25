package uncleroger.storage;

import uncleroger.task.Deadline;
import uncleroger.task.Event;
import uncleroger.task.Task;
import uncleroger.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static uncleroger.UncleRoger.tasks;

/**
 * The Storage class handles the persistence of tasks in the
 * Uncle Roger task management application.
 * <p>
 * This class provides methods for creating directories and data files,
 * processing tasks from a data file,
 * and loading tasks into a data file. It ensures that tasks are saved and loaded correctly,
 * maintaining the state of the task list across application runs.
 *
 * @author Chen Yiyang
 */
public class Storage {

    private static final String filePath = "data/UncleRoger.txt";

    private static void handleTodoData(String description, String status) {
        boolean isDone = status.equals("1");
        tasks.add(new Todo(description, isDone));
    }

    private static void handleDeadlineData(String description, String status) {
        boolean isDone = status.equals("1");
        String[] fields = description.split("-");
        tasks.add(new Deadline(fields[1].trim(), fields[0].trim(), isDone));
    }

    private static void handleEventData(String description, String status) {
        boolean isDone = status.equals("1");
        String[] fields = description.split("-");
        tasks.add(new Event(fields[1].trim(), fields[2].trim(),
                fields[0].trim(), isDone));
    }

    private static void insertTaskFromData(String task) {
        String[] words = task.split(" ", 3);
        String command = words[0];
        String status = words[1];
        String description = words[2];
        switch (command) {
        case "T":
            handleTodoData(description, status);
            break;
        case "D":
            handleDeadlineData(description, status);
            break;
        case "E":
            handleEventData(description, status);
            break;
        }
    }

    private static void loadDeadlineToFile(Deadline task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String status = task.getIsDone() ? "1 " : "0 ";
        String toAppend = "D "+ status + task.getDescription() +
                " - " + task.getBy() + System.lineSeparator();
        fw.write(toAppend);
        fw.close();
    }

    private static void loadEventToFile(Event task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String status = task.getIsDone() ? "1 " : "0 ";
        String toAppend = "E "+ status + task.getDescription() + " - " +
                task.getFrom() + " - " + task.getTo() + System.lineSeparator();
        fw.write(toAppend);
        fw.close();
    }

    private static void loadTodoToFile(Todo task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String status = task.getIsDone() ? "1 " : "0 ";
        String toAppend = "T "+ status + task.getDescription() + System.lineSeparator();
        fw.write(toAppend);
        fw.close();
    }

    private static void clearFile() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.close();
    }

    /**
     * Creates a directory named "data" if it does not already exist.
     * <p>
     * This method checks if a directory named "data" exists in the current working directory.
     * If the directory does not exist, it attempts to create it. If the creation fails, it prints an error message.
     */
    public static void createDirectory() {
        Path path = Paths.get("data");
        if (Files.isDirectory(path)) {
            return;
        }
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            System.err.println("Failed to create directory: " + e.getMessage());
        }
    }

    /**
     * Creates a data file if it does not already exist.
     * <p>
     * This method checks if a file specified by the path stored in the `filePath` variable exists.
     * If the file does not exist, it attempts to create it. If the creation fails, it prints an error message.
     */
    public static void createDataFile() {
        File f = new File(filePath);
        if (f.exists()) {
            return;
        }
        try {
            boolean isFileCreated = f.createNewFile();
        } catch (IOException e) {
            System.err.println("Failed to create file: " + e.getMessage());
        }
    }

    /**
     * Processes tasks from the data file and inserts them into the task list.
     * <p>
     * This method reads tasks from the file named "UncleRoger.txt" located in the "data" directory.
     * Each line in the file is assumed to represent a single task. The method reads each line and
     * calls the `insertTaskFromData` method to insert the task into the task list.
     *
     * @throws FileNotFoundException If the file "data/UncleRoger.txt" does not exist.
     */
    public static void processDataFromFile() throws FileNotFoundException {
        File f = new File("data/UncleRoger.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String task = s.nextLine();
            insertTaskFromData(task);
        }
    }

    /**
     * Loads all tasks from the in-memory task list into a data file.
     * <p>
     * This method first clears the existing data file to ensure a fresh start.
     * It then iterates through the list of tasks and writes each task to the file.
     * The method handles different types of tasks (Deadline, Event, Todo) and calls
     * the appropriate method to format and write each task to the file.
     */
    public static void loadDataIntoFile() {
        try {
            clearFile();
        } catch (IOException e) {
            System.err.println("Failed to clear File: " + e.getMessage());
        }
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                try {
                    loadDeadlineToFile((Deadline) task);
                } catch (IOException e) {
                    System.err.println("Failed to enter Deadline into File: " + e.getMessage());
                }
            } else if (task instanceof Event) {
                try {
                    loadEventToFile((Event) task);
                } catch (IOException e) {
                    System.err.println("Failed to enter Event into File: " + e.getMessage());
                }
            } else {
                try {
                    loadTodoToFile((Todo) task);
                } catch (IOException e) {
                    System.err.println("Failed to enter Todo into File: " + e.getMessage());
                }
            }
        }
    }
}
