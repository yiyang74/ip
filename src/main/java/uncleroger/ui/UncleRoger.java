package uncleroger.ui;

import uncleroger.exception.*;
import uncleroger.task.*;
import java.util.Arrays;
import java.util.Scanner;


public class UncleRoger {

    private static final String LINE_SEPARATOR =
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private static final int MAX_ARRAY_LEN = 100;

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

    private static void handleMarkCommand(Task[] tasks, String[] words)
            throws NoEntryYetException, AlreadyMarkedException, NonPositiveIndexException {
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
    }

    private static void handleListCommand(Task[] tasks) throws EmptyListException {
        if (Task.getTaskCount() == 0) {
            throw new EmptyListException();
        } else {
            printList(tasks);
        }
    }

    private static void handleTodoCommand(Task[] tasks, String[] words)
            throws NoDescriptionException {
        if (words.length < 2) {
            throw new NoDescriptionException();
        }
        String description = combineWordsToSentence(words, 1, words.length);
        tasks[Task.getTaskCount()] = new Todo(description);
        printTaskEntry(tasks);
    }

    private static void handleDeadlineCommand(Task[] tasks, String[] words)
            throws NoDescriptionException, InvalidDeadlineException {
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
        printTaskEntry(tasks);
    }

    private static void handleEventCommand(Task[] tasks, String[] words)
            throws NoDescriptionException, MissingEventFieldsException,
            InvalidEventFieldOrderException {
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
            }
            break;
        default:
            throw new InvalidCommandException();
        }
    }

    public static void main(String[] args) {
        printGreeting();
        Task[] tasks = new Task[MAX_ARRAY_LEN];
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
