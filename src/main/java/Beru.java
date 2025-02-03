import java.util.Arrays;
import java.util.Scanner;

public class Beru {

    private static final String LINE_SEPARATOR =
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private static final int MAX_ARRAY_LEN = 100;

    private static void printList(Task[] tasks) {
        System.out.print(LINE_SEPARATOR);
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
        System.out.print(LINE_SEPARATOR);
    }

    private static void printEmptyList() {
        System.out.print(LINE_SEPARATOR +
                "You have no items in your list yet!\n" + LINE_SEPARATOR);
    }

    private static void printTaskStatus(Task t) {
        System.out.print(LINE_SEPARATOR);
        if (t.getIsDone()) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + t);
        System.out.print(LINE_SEPARATOR);
    }

    private static void printGreeting() {
        System.out.print(LINE_SEPARATOR + "Hello! I'm Beru\n" +
                        "What can I do for you?\n" + LINE_SEPARATOR);
    }

    private static void printGoodbye() {
        System.out.print(LINE_SEPARATOR +
                "Bye... :(\nHope to see you again soon! >///<\n" + LINE_SEPARATOR);
    }

    private static void printNoEntryYet(int entryIndex) {
        System.out.print(LINE_SEPARATOR + "No entry for task " +
                entryIndex + " yet! :(\n" + LINE_SEPARATOR);
    }

    private static void printAlreadyDone() {
        System.out.print(LINE_SEPARATOR +
                "Entry has already been marked as done!\n" + LINE_SEPARATOR);
    }

    private static void printAlreadyUnmarked() {
        System.out.print(LINE_SEPARATOR +
                "Entry is already unmarked!\n" + LINE_SEPARATOR);
    }

    private static void printTaskEntry(Task[] tasks) {
        System.out.print(LINE_SEPARATOR + "Got it! I've added this task:\n  " +
                tasks[Task.getTaskCount() - 1] + "\nNow you have " +
                Task.getTaskCount() + " tasks in the list.\n" + LINE_SEPARATOR);
    }

    private static void printInvalidDeadline() {
        System.out.print(LINE_SEPARATOR +
                "Invalid entry for Deadline!\nPlease include \"/by\"!\n" + LINE_SEPARATOR);
    }

    private static void printMissingEventFields() {
        System.out.print(LINE_SEPARATOR +
                "Invalid entry for Event!\nPlease include both \"/from\" and \"/to\"!\n" +
                LINE_SEPARATOR);
    }

    private static void printInvalidEventFieldOrder() {
        System.out.print(LINE_SEPARATOR +
                "Invalid entry for Event!\nPlease enter \"/from\" before \"/to\"!\n" +
                LINE_SEPARATOR);
    }

    private static void handleMarkCommand(Task[] tasks, String[] words) {
        int userEnteredEntry = Integer.parseInt(words[1]);
        int userEnteredArrIndex = userEnteredEntry - 1;
        if (userEnteredArrIndex >= Task.getTaskCount()) {
            printNoEntryYet(userEnteredEntry);
        } else if (tasks[userEnteredArrIndex].getIsDone()) {
            printAlreadyDone();
        } else {
            tasks[userEnteredArrIndex].setIsDone(true);
            printTaskStatus(tasks[userEnteredArrIndex]);
        }
    }

    private static void handleUnmarkedCommand(Task[] tasks, String[] words) {
        int userEnteredEntry = Integer.parseInt(words[1]);
        int userEnteredArrIndex = userEnteredEntry - 1;
        if (userEnteredArrIndex >= Task.getTaskCount()) {
            printNoEntryYet(userEnteredEntry);
        } else if (!tasks[userEnteredArrIndex].getIsDone()) {
            printAlreadyUnmarked();
        } else {
            tasks[userEnteredArrIndex].setIsDone(false);
            printTaskStatus(tasks[userEnteredArrIndex]);
        }
    }

    private static void handleListCommand(Task[] tasks) {
        if (Task.getTaskCount() == 0) {
            printEmptyList();
        } else {
            printList(tasks);
        }
    }

    private static void handleTodoCommand(Task[] tasks, String[] words) {
        String description = combineWordsToSentence(words, 1, words.length);
        tasks[Task.getTaskCount()] = new Todo(description);
        printTaskEntry(tasks);
    }

    private static void handleDeadlineCommand(Task[] tasks, String[] words) {
        int indexOfBy = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase("/by")) {
                indexOfBy = i;
                break;
            }
        }
        if (indexOfBy == -1) {
            printInvalidDeadline();
            return;
        }
        String description = combineWordsToSentence(words, 1, indexOfBy);
        String by = combineWordsToSentence(words, indexOfBy + 1, words.length);
        tasks[Task.getTaskCount()] = new Deadline(by, description);
        printTaskEntry(tasks);
    }

    private static void handleEventCommand(Task[] tasks, String[] words) {
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
            printMissingEventFields();
            return;
        }
        if (indexOfFrom > indexOfTo) {
            printInvalidEventFieldOrder();
            return;
        }
        String description = combineWordsToSentence(words, 1, indexOfFrom);
        String from = combineWordsToSentence(words, indexOfFrom + 1, indexOfTo);
        String to = combineWordsToSentence(words, indexOfTo + 1, words.length);
        tasks[Task.getTaskCount()] = new Event(from, to, description);
        printTaskEntry(tasks);
    }

    private static String combineWordsToSentence(String[] words, int start, int end) {
        words = Arrays.copyOfRange(words, start, end);
        return String.join(" ", words);
    }

    private static void parseUserSentence(String sentence, Task[] tasks) {
        String[] words = sentence.split(" ");
        String command = words[0];
        switch (command.toLowerCase()) {
        case "list":
            handleListCommand(tasks);
            break;
        case "mark":
            handleMarkCommand(tasks, words);
            break;
        case "unmark":
            handleUnmarkedCommand(tasks, words);
            break;
        case "todo":
            handleTodoCommand(tasks, words);
            break;
        case "deadline":
            handleDeadlineCommand(tasks, words);
            break;
        case "event":
            handleEventCommand(tasks, words);
            break;
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
            parseUserSentence(sentence, tasks);
        }
        printGoodbye();
    }
}
