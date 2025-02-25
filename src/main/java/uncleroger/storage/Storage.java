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

    public static void processDataFromFile() throws FileNotFoundException {
        File f = new File("data/UncleRoger.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String task = s.nextLine();
            insertTaskFromData(task);
        }
    }

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
