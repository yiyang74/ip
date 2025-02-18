package uncleroger.ui;

import uncleroger.exception.*;
import uncleroger.task.*;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class UncleRoger {

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "data/UncleRoger.txt";

    private static void printLineSeparator() {
        System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    private static void printList() {
        printLineSeparator();
        System.out.println("Come. Uncle Roger remind you what tasks you have: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printLineSeparator();
    }

    private static void printEmptyList() {
        printLineSeparator();
        System.out.println("Haiya! You haven't added anything yet.");
        printLineSeparator();
    }

    private static void printTaskStatus(Task t) {
        printLineSeparator();
        if (t.getIsDone()) {
            System.out.println("Uncle Roger help you mark this as done:");
            System.out.println("  " + t);
            System.out.println("Fuiyoh! Uncle Roger proud of you, good job.");
        } else {
            System.out.println("Uncle Roger help you mark this as undone:");
            System.out.println("  " + t);
            System.out.println("Haiya...Uncle Roger disappointed in you, don't be lazy!:");
        }
        printLineSeparator();
    }

    private static void printTaskRemove(Task t) {
        printLineSeparator();
        System.out.println("Ok. Uncle Roger help you remove this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in your list.");
        printLineSeparator();
    }

    private static void printGreeting() {
        printLineSeparator();
        System.out.println("Hallo, I am Uncle Roger.\nWhat you want me to do?");
        printLineSeparator();
    }

    private static void printGoodbye() {
        printLineSeparator();
        System.out.println("Haiya...Goodbye.\nDon't like Uncle Roger just say.");
        printLineSeparator();
    }

    private static void printNoEntryYet() {
        printLineSeparator();
        System.out.println("Aiyoo...don't even have this task yet!\nKan Cheong Spider!");
        printLineSeparator();
    }

    private static void printAlreadyMarked() {
        printLineSeparator();
        System.out.println("Haiya...this task already mark as done.\n" +
                "Go do something else");
        printLineSeparator();
    }

    private static void printAlreadyUnmarked() {
        printLineSeparator();
        System.out.println("Haiya...this task is already unmarked.\n" +
                "Stop procrastinating!");
        printLineSeparator();
    }

    private static void printTaskEntry() {
        printLineSeparator();
        System.out.println("Ok. Uncle Roger add this task for you:\n  " +
                tasks.get(tasks.size() - 1) + "\nDon't forget, now you have " +
                tasks.size() + " tasks in your list.");
        printLineSeparator();
    }

    private static void printInvalidDeadline() {
        printLineSeparator();
        System.out.println("Haiya...invalid entry for Deadline!\n" +
                "Go include \"/by\" in your entry!");
        printLineSeparator();
    }

    private static void printMissingEventFields() {
        printLineSeparator();
        System.out.println("Haiya...invalid entry for Event!\n" +
                "Go include both \"/from\" and \"/to\" in your entry!");
        printLineSeparator();
    }

    private static void printInvalidEventFieldOrder() {
        printLineSeparator();
        System.out.println("Haiya...wrong entry order for Event!\n" +
                "Go enter \"/from\" before \"/to\" in your entry!");
        printLineSeparator();
    }

    private static void printNoDescription() {
        printLineSeparator();
        System.out.println("Haiya...your entry got no description!\n" +
                "Go re-type for Uncle Roger again!");
        printLineSeparator();
    }

    private static void printNonPositiveIndex() {
        printLineSeparator();
        System.out.println("Haiya...Uncle Roger remind you again,\n" +
                "task number entered must be more than 0!");
        printLineSeparator();
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

    private static void handleMarkCommand(String[] words)
            throws NoEntryYetException, AlreadyMarkedException,
            NonPositiveIndexException, IOException {
        int userEnteredEntry = Integer.parseInt(words[1]);
        int userEnteredArrIndex = userEnteredEntry - 1;
        if (userEnteredEntry <= 0) {
            throw new NonPositiveIndexException();
        } else if (userEnteredArrIndex >= tasks.size()) {
            throw new NoEntryYetException();
        } else if (tasks.get(userEnteredArrIndex).getIsDone()) {
            throw new AlreadyMarkedException();
        } else {
            tasks.get(userEnteredArrIndex).setIsDone(true);
            printTaskStatus(tasks.get(userEnteredArrIndex));
        }
        modifyStatusInData(userEnteredArrIndex, true);
    }

    private static void handleUnmarkedCommand(String[] words)
            throws NoEntryYetException, AlreadyUnmarkedException, NonPositiveIndexException {
        int userEnteredEntry = Integer.parseInt(words[1]);
        int userEnteredArrIndex = userEnteredEntry - 1;
        if (userEnteredEntry <= 0) {
            throw new NonPositiveIndexException();
        } else if (userEnteredArrIndex >= tasks.size()) {
            throw new NoEntryYetException();
        } else if (!tasks.get(userEnteredArrIndex).getIsDone()) {
            throw new AlreadyUnmarkedException();
        } else {
            tasks.get(userEnteredArrIndex).setIsDone(false);
            printTaskStatus(tasks.get(userEnteredArrIndex));
        }
        modifyStatusInData(userEnteredArrIndex, false);

    }

    private static void handleDeleteCommand(String[] words)
            throws NonPositiveIndexException, NoEntryYetException {
        int userEnteredEntry = Integer.parseInt(words[1]);
        int userEnteredArrIndex = userEnteredEntry - 1;
        if (userEnteredEntry <= 0) {
            throw new NonPositiveIndexException();
        } else if (userEnteredArrIndex >= tasks.size()) {
            throw new NoEntryYetException();
        } else {
            printTaskRemove(tasks.get(userEnteredArrIndex));
            tasks.remove(userEnteredArrIndex);
        }
    }

    private static void handleListCommand() throws EmptyListException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        } else {
            printList();
        }
    }

    private static void insertTodoToData(String description) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        String toAppend = "T 0 " + description + System.lineSeparator();
        fw.write(toAppend);
        fw.close();
    }

    private static void handleTodoCommand(String[] words)
            throws NoDescriptionException {
        if (words.length < 2) {
            throw new NoDescriptionException();
        }
        String description = combineWordsToSentence(words, 1, words.length);
        tasks.add(new Todo(description));
        try {
            insertTodoToData(description);
        } catch (IOException e) {
            System.err.println("Failed to enter Todo into Data: " + e.getMessage());
        }
        printTaskEntry();
    }


    private static void insertDeadlineToData(String description, String by)
            throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        String toAppend = "D 0 " + description + " - " + by + System.lineSeparator();
        fw.write(toAppend);
        fw.close();
    }

    private static void handleDeadlineCommand(String[] words)
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
        tasks.add(new Deadline(by, description));
        insertDeadlineToData(description, by);
        printTaskEntry();
    }

    private static void insertEventToData(String description, String from, String to)
            throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        String toAppend = "E 0 " + description + " - " + from + " - " + to + System.lineSeparator();
        fw.write(toAppend);
        fw.close();
    }

    private static void handleEventCommand(String[] words)
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
        tasks.add(new Event(from, to, description));
        insertEventToData(description, from, to);
        printTaskEntry();
    }

    private static void printInvalidCommand() {
        printLineSeparator();
        System.out.println("""
                Haiya...Uncle Roger don't know what you typing!
                Uncle Roger remind you again. Begin your entry with:
                "list", "mark", "unmark", "todo",
                "deadline", "event" or "delete".""");
        printLineSeparator();
    }

    private static String combineWordsToSentence(String[] words, int start, int end) {
        words = Arrays.copyOfRange(words, start, end);
        return String.join(" ", words);
    }

    private static void parseUserSentence(String sentence)
            throws InvalidCommandException {
        String[] words = sentence.split(" ");
        String command = words[0];
        switch (command.toLowerCase()) {
        case "list":
            try {
                handleListCommand();
            } catch (EmptyListException e) {
                printEmptyList();
            }
            break;
        case "mark":
            try {
                handleMarkCommand(words);
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
                handleUnmarkedCommand(words);
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
                handleTodoCommand(words);
            } catch (NoDescriptionException e) {
                printNoDescription();
            }
            break;
        case "deadline":
            try {
                handleDeadlineCommand(words);
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
                handleEventCommand(words);
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
        case "delete":
            try {
                handleDeleteCommand(words);
            } catch (NonPositiveIndexException e) {
                printNonPositiveIndex();
            } catch (NoEntryYetException e) {
                printNoEntryYet();
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

    private static void loadDataFromFile() throws FileNotFoundException {
        File f = new File("data/UncleRoger.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String task = s.nextLine();
            insertTaskFromData(task);
        }
    }

    public static void main(String[] args) {
        printGreeting();
        createDirectory();
        createDataFile();
        try {
            loadDataFromFile();
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
                parseUserSentence(sentence);
            } catch (InvalidCommandException e) {
                printInvalidCommand();
            }
        }
        printGoodbye();
    }
}
