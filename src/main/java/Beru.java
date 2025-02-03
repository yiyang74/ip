import java.util.Scanner;

public class Beru {

    private static final String LINE_SEPARATOR =
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private static final int MAX_ARRAY_LEN = 100;

    private static void printList(Task[] tasks) {
        System.out.print(LINE_SEPARATOR);
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println((i + 1) +
                    ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
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
        System.out.println("  [" + t.getStatusIcon() + "] " + t.getDescription());
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
        default:
            tasks[Task.getTaskCount()] = new Task(sentence);
            System.out.print(LINE_SEPARATOR + "added: " + sentence + '\n' + LINE_SEPARATOR);
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
