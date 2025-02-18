package uncleroger.ui;

import uncleroger.exception.*;
import uncleroger.task.*;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class UncleRoger {

    private static final String LINE_SEPARATOR =
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private static final int MAX_ARRAY_LEN = 100;
    private static final String FILE_PATH = "data/UncleRoger.txt";

    private static void printList(Task[] tasks) {
        System.out.print(LINE_SEPARATOR);
        System.out.println("Come. Uncle Roger remind you what tasks you have: ");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
        System.out.print(LINE_SEPARATOR);
    }

    private static void printEmptyList() {
        System.out.print(LINE_SEPARATOR +
                "Haiya! You haven't added anything yet.\n" + LINE_SEPARATOR);
    }

    private static void printTaskStatus(Task t) {
        System.out.print(LINE_SEPARATOR);
        if (t.getIsDone()) {
            System.out.println("Uncle Roger help you mark this as done:");
            System.out.println("  " + t);
            System.out.println("Uncle Roger proud of you, good job.");
        } else {
            System.out.println("Uncle Roger help you mark this as undone:");
            System.out.println("  " + t);
            System.out.println("Uncle Roger disappointed in you, don't be lazy!:");
        }
        System.out.print(LINE_SEPARATOR);
    }

    private static void printGreeting() {
        System.out.print(LINE_SEPARATOR + "Hallo, I am Uncle Roger.\n" +
                        "What you want me to do?\n" + LINE_SEPARATOR);
    }

    private static void printGoodbye() {
        System.out.print(LINE_SEPARATOR +
                "Haiya...Goodbye.\nDon't like Uncle Roger just say.\n" + LINE_SEPARATOR);
    }

    private static void printNoEntryYet() {
        System.out.print(LINE_SEPARATOR + "Aiyoo...don't even have this task yet!\n" +
                "Kan Cheong Spider!\n" + LINE_SEPARATOR);
    }

    private static void printAlreadyMarked() {
        System.out.print(LINE_SEPARATOR +
                "Haiya...this task already mark as done.\n" +
                "Go do something else\n" + LINE_SEPARATOR);
    }

    private static void printAlreadyUnmarked() {
        System.out.print(LINE_SEPARATOR +
                "Haiya...this task is already unmarked.\n" +
                "Stop procrastinating!\n" + LINE_SEPARATOR);
    }

    private static void printTaskEntry(Task[] tasks) {
        System.out.print(LINE_SEPARATOR + "Ok. Uncle Roger add this task for you:\n  " +
                tasks[Task.getTaskCount() - 1] + "\nDon't forget, now you have " +
                Task.getTaskCount() + " tasks in your list.\n" + LINE_SEPARATOR);
    }

    private static void printInvalidDeadline() {
        System.out.print(LINE_SEPARATOR +
                "Haiya...invalid entry for Deadline!\n" +
                "Go include \"/by\" in your entry!\n" + LINE_SEPARATOR);
    }

    private static void printMissingEventFields() {
        System.out.print(LINE_SEPARATOR +
                "Haiya...invalid entry for Event!\n" +
                "Go include both \"/from\" and \"/to\" in your entry!\n" +
                LINE_SEPARATOR);
    }

    private static void printInvalidEventFieldOrder() {
        System.out.print(LINE_SEPARATOR +
                "Haiya...wrong entry order for Event!\n" +
                "Go enter \"/from\" before \"/to\" in your entry!\n" + LINE_SEPARATOR);
    }

    private static void printNoDescription() {
        System.out.print(LINE_SEPARATOR + "Haiya...your entry got no description!\n" +
                "Go re-type for Uncle Roger again!\n" + LINE_SEPARATOR);
    }

    private static void printNonPositiveIndex() {
        System.out.print(LINE_SEPARATOR + "Haiya...Uncle Roger remind you again,\n" +
                "task number entered must be more than 0!\n" + LINE_SEPARATOR);
    }

    private static void modifyStatusInData(int userEnteredArrIndex, boolean statusResult) {
        String isDone;
        if (statusResult) {
            isDone = "1";
        } else {
            isDone = "0";
        }
        //to be completed
    }

    private static void handleMarkCommand(Task[] tasks, String[] words)
            throws NoEntryYetException, AlreadyMarkedException,
            NonPositiveIndexException, IOException {
        int userEnteredEntry = Integer.parseInt(words[1]);
        int userEnteredArrIndex = userEnteredEntry - 1;
        if (userEnteredEntry <= 0) {
            throw new NonPositiveIndexException();
        } else if (userEnteredArrIndex >= Task.getTaskCount()) {
            throw new NoEntryYetException();
        } else if (tasks[userEnteredArrIndex].getIsDone()) {
            throw new AlreadyMarkedException();
        } else {
            tasks[userEnteredArrIndex].setIsDone(true);
            printTaskStatus(tasks[userEnteredArrIndex]);
        }
        modifyStatusInData(userEnteredArrIndex, true);
    }

    private static void handleUnmarkedCommand(Task[] tasks, String[] words)
            throws NoEntryYetException, AlreadyUnmarkedException, NonPositiveIndexException {
        int userEnteredEntry = Integer.parseInt(words[1]);
        int userEnteredArrIndex = userEnteredEntry - 1;
        if (userEnteredEntry <= 0) {
            throw new NonPositiveIndexException();
        } else if (userEnteredArrIndex >= Task.getTaskCount()) {
            throw new NoEntryYetException();
        } else if (!tasks[userEnteredArrIndex].getIsDone()) {
            throw new AlreadyUnmarkedException();
        } else {
            tasks[userEnteredArrIndex].setIsDone(false);
            printTaskStatus(tasks[userEnteredArrIndex]);
        }
        modifyStatusInData(userEnteredArrIndex, false);

    }

    private static void handleListCommand(Task[] tasks) throws EmptyListException {
        if (Task.getTaskCount() == 0) {
            throw new EmptyListException();
        } else {
            printList(tasks);
        }
    }

    private static void insertTodoToData(String description) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        String toAppend = "T 0 " + description + System.lineSeparator();
        fw.write(toAppend);
        fw.close();
    }

    private static void handleTodoCommand(Task[] tasks, String[] words)
            throws NoDescriptionException {
        if (words.length < 2) {
            throw new NoDescriptionException();
        }
        String description = combineWordsToSentence(words, 1, words.length);
        tasks[Task.getTaskCount()] = new Todo(description);
        try {
            insertTodoToData(description);
        } catch (IOException e) {
            System.err.println("Failed to enter Todo into Data: " + e.getMessage());
        }
        printTaskEntry(tasks);
    }

    private static void insertDeadlineToData(String description, String by)
            throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        String toAppend = "D 0 " + description + " - " + by + System.lineSeparator();
        fw.write(toAppend);
        fw.close();
    }

    private static void handleDeadlineCommand(Task[] tasks, String[] words)
            throws NoDescriptionException, InvalidDeadlineException, IOException {
        if (words.length < 2) {
            throw new NoDescriptionException();
        }
        int indexOfBy = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase("/by")) {
                indexOfBy = i;
                break;
            }
        }
        if (indexOfBy == -1) {
            throw new InvalidDeadlineException();
        }
        String description = combineWordsToSentence(words, 1, indexOfBy);
        String by = combineWordsToSentence(words, indexOfBy + 1, words.length);
        tasks[Task.getTaskCount()] = new Deadline(by, description);
        insertDeadlineToData(description, by);
        printTaskEntry(tasks);
    }

    private static void insertEventToData(String description, String from, String to)
            throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        String toAppend = "E 0 " + description + " - " + from + " - " + to + System.lineSeparator();
        fw.write(toAppend);
        fw.close();
    }

    private static void handleEventCommand(Task[] tasks, String[] words)
            throws NoDescriptionException, MissingEventFieldsException,
            InvalidEventFieldOrderException, IOException {
        if (words.length < 2) {
            throw new NoDescriptionException();
        }
        int indexOfFrom = -1;
        int indexOfTo = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase("/from")) {
                indexOfFrom = i;
            }
            if (words[i].equalsIgnoreCase("/to")) {
                indexOfTo = i;
            }
        }
        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new MissingEventFieldsException();
        }
        if (indexOfFrom > indexOfTo) {
            throw new InvalidEventFieldOrderException();
        }
        String description = combineWordsToSentence(words, 1, indexOfFrom);
        String from = combineWordsToSentence(words, indexOfFrom + 1, indexOfTo);
        String to = combineWordsToSentence(words, indexOfTo + 1, words.length);
        tasks[Task.getTaskCount()] = new Event(from, to, description);
        insertEventToData(description, from, to);
        printTaskEntry(tasks);
    }

    private static void printInvalidCommand() {
        System.out.print(LINE_SEPARATOR + "Haiya...Uncle Roger don't know what you typing!\n" +
                "Uncle Roger remind you again. Begin your entry with:\n" +
                "\"list\", \"mark\", \"unmark\", \"todo\", \"deadline\" or \"event\".\n" +
                LINE_SEPARATOR);
    }

    private static String combineWordsToSentence(String[] words, int start, int end) {
        words = Arrays.copyOfRange(words, start, end);
        return String.join(" ", words);
    }

    private static void parseUserSentence(String sentence, Task[] tasks)
            throws InvalidCommandException {
        String[] words = sentence.split(" ");
        String command = words[0];
        switch (command.toLowerCase()) {
        case "list":
            try {
                handleListCommand(tasks);
            } catch (EmptyListException e) {
                printEmptyList();
            }
            break;
        case "mark":
            try {
                handleMarkCommand(tasks, words);
            } catch (NoEntryYetException e) {
                printNoEntryYet();
            } catch (AlreadyMarkedException e) {
                printAlreadyMarked();
            } catch (NonPositiveIndexException e) {
                printNonPositiveIndex();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            break;
        case "unmark":
            try {
                handleUnmarkedCommand(tasks, words);
            } catch (NoEntryYetException e) {
                printNoEntryYet();
            } catch (AlreadyUnmarkedException e) {
                printAlreadyUnmarked();
            } catch (NonPositiveIndexException e) {
                printNonPositiveIndex();
            }
            break;
        case "todo":
            try {
                handleTodoCommand(tasks, words);
            } catch (NoDescriptionException e) {
                printNoDescription();
            }
            break;
        case "deadline":
            try {
                handleDeadlineCommand(tasks, words);
            } catch (NoDescriptionException e) {
                printNoDescription();
            } catch (InvalidDeadlineException e) {
                printInvalidDeadline();
            } catch (IOException e) {
                System.err.println("Failed to enter Deadline into Data: " + e.getMessage());
            }
            break;
        case "event":
            try {
                handleEventCommand(tasks, words);
            } catch (NoDescriptionException e) {
                printNoDescription();
            } catch (MissingEventFieldsException e) {
                printMissingEventFields();
            } catch (InvalidEventFieldOrderException e) {
                printInvalidEventFieldOrder();
            } catch (IOException e) {
                System.err.println("Failed to enter Event into Data: " + e.getMessage());
            }
            break;
        default:
            throw new InvalidCommandException();
        }
    }

    private static void createDirectory() {
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

    private static void createDataFile() {
        File f = new File(FILE_PATH);
        if (f.exists()) {
            return;
        }
        try {
            boolean isFileCreated = f.createNewFile();
        } catch (IOException e) {
            System.err.println("Failed to create file: " + e.getMessage());
        }
    }

    private static void handleTodoData(String description, String status, Task[] tasks) {
        boolean isDone = status.equals("1");
        tasks[Task.getTaskCount()] = new Todo(description, isDone);
    }

    private static void handleDeadlineData(String description, String status, Task[] tasks) {
        boolean isDone = status.equals("1");
        String[] fields = description.split("-");
        tasks[Task.getTaskCount()] = new Deadline(fields[1].trim(), fields[0].trim(), isDone);
    }

    private static void handleEventData(String description, String status, Task[] tasks) {
        boolean isDone = status.equals("1");
        String[] fields = description.split("-");
        tasks[Task.getTaskCount()] = new Event(fields[1].trim(), fields[2].trim(),
                fields[0].trim(), isDone);
    }

    private static void insertTaskFromData(String task, Task[] tasks) {
        String[] words = task.split(" ", 3);
        String command = words[0];
        String status = words[1];
        String description = words[2];
        switch (command) {
        case "T":
            handleTodoData(description, status, tasks);
            break;
        case "D":
            handleDeadlineData(description, status, tasks);
            break;
        case "E":
            handleEventData(description, status, tasks);
            break;
        }
    }

    private static void loadDataFromFile(Task[] tasks) throws FileNotFoundException {
        File f = new File("data/UncleRoger.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String task = s.nextLine();
            insertTaskFromData(task, tasks);
        }
    }

    public static void main(String[] args) {
        printGreeting();
        createDirectory();
        createDataFile();
        Task[] tasks = new Task[MAX_ARRAY_LEN];
        try {
            loadDataFromFile(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("UncleRoger.txt not found");
        }
        Scanner in = new Scanner(System.in);
        while (true) {
            String sentence = in.nextLine().trim();
            if (sentence.equalsIgnoreCase("bye")) {
                break;
            }
            try {
                parseUserSentence(sentence, tasks);
            } catch (InvalidCommandException e) {
                printInvalidCommand();
            }
        }
        printGoodbye();
    }
}
