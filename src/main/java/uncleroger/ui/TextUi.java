package uncleroger.ui;

import uncleroger.task.Task;

import java.util.Scanner;

import static uncleroger.UncleRoger.tasks;

/**
 * The TextUi class handles all user interface interactions for
 * the Uncle Roger task management application.
 * <p>
 * This class provides methods for printing various messages and prompts to the user,
 * as well as reading user input.
 * It is designed to provide a friendly and interactive command-line interface for managing tasks.
 *
 * @author Chen Yiyang
 */
public class TextUi {

    private static void printLineSeparator() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public final Scanner in;

    public TextUi() {
        in = new Scanner(System.in);
    }

    /**
     * Prints the list of tasks.
     * <p>
     * This method prints a header, iterates through the list of tasks, and prints each task
     * with its index. It also prints a line separator before and after the list.
     */
    public static void printList() {
        printLineSeparator();
        System.out.println("Come. Uncle Roger remind you what tasks you have: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printLineSeparator();
    }

    /**
     * Prints a message indicating that the task list is empty.
     * <p>
     * This method prints a line separator, a message indicating that no tasks have been added,
     * and another line separator.
     */
    public static void printEmptyList() {
        printLineSeparator();
        System.out.println("Haiya! You haven't added anything yet.");
        printLineSeparator();
    }

    /**
     * Prints the status of a task after it has been marked as done or undone.
     *
     * @param t The task whose status is being printed.
     */
    public static void printTaskStatus(Task t) {
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

    /**
     * Prints a message after a task has been removed from the list.
     *
     * @param t The task that is being removed.
     */
    public static void printTaskRemove(Task t) {
        printLineSeparator();
        System.out.println("Ok. Uncle Roger help you remove this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in your list.");
        printLineSeparator();
    }

    /**
     * Prints a greeting message when the program starts.
     */
    public static void printGreeting() {
        printLineSeparator();
        System.out.println("Hallo, I am Uncle Roger.\nWhat you want me to do?");
        printLineSeparator();
    }

    /**
     * Prints a goodbye message when the program exits.
     */
    public static void printGoodbye() {
        printLineSeparator();
        System.out.println("Haiya...Goodbye.\nDon't like Uncle Roger just say.");
        printLineSeparator();
    }

    /**
     * Prints a message indicating that a task does not exist.
     */
    public static void printNoEntryYet() {
        printLineSeparator();
        System.out.println("Aiyoo...don't even have this task yet!\nKan Cheong Spider!");
        printLineSeparator();
    }

    /**
     * Prints a message indicating that a task is already marked as done.
     */
    public static void printAlreadyMarked() {
        printLineSeparator();
        System.out.println("Haiya...this task already mark as done.\n" +
                "Go do something else");
        printLineSeparator();
    }

    /**
     * Prints a message indicating that a task is already marked as undone.
     */
    public static void printAlreadyUnmarked() {
        printLineSeparator();
        System.out.println("Haiya...this task is already unmarked.\n" +
                "Stop procrastinating!");
        printLineSeparator();
    }

    /**
     * Prints a message after a new task has been added to the list.
     */
    public static void printTaskEntry() {
        printLineSeparator();
        System.out.println("Ok. Uncle Roger add this task for you:\n  " +
                tasks.get(tasks.size() - 1) + "\nDon't forget, now you have " +
                tasks.size() + " tasks in your list.");
        printLineSeparator();
    }

    /**
     * Prints a message indicating that the deadline entry is invalid.
     */
    public static void printInvalidDeadline() {
        printLineSeparator();
        System.out.println("Haiya...invalid entry for Deadline!\n" +
                "Go include \"/by\" in your entry!");
        printLineSeparator();
    }

    /**
     * Prints a message indicating that the event entry is missing fields.
     */
    public static void printMissingEventFields() {
        printLineSeparator();
        System.out.println("Haiya...invalid entry for Event!\n" +
                "Go include both \"/from\" and \"/to\" in your entry!");
        printLineSeparator();
    }

    /**
     * Prints a message indicating that the event entry has fields in the wrong order.
     */
    public static void printInvalidEventFieldOrder() {
        printLineSeparator();
        System.out.println("Haiya...wrong entry order for Event!\n" +
                "Go enter \"/from\" before \"/to\" in your entry!");
        printLineSeparator();
    }

    /**
     * Prints a message indicating that the task description is missing.
     */
    public static void printNoDescription() {
        printLineSeparator();
        System.out.println("Haiya...your entry got no description!\n" +
                "Go re-type for Uncle Roger again!");
        printLineSeparator();
    }

    /**
     * Prints a message indicating that the task index is not positive.
     */
    public static void printNonPositiveIndex() {
        printLineSeparator();
        System.out.println("Haiya...Uncle Roger remind you again,\n" +
                "task number entered must be more than 0!");
        printLineSeparator();
    }

    /**
     * Prints a message indicating that the command is invalid.
     */
    public static void printInvalidCommand() {
        printLineSeparator();
        System.out.println("""
            Haiya...Uncle Roger don't know what you typing!
            Uncle Roger remind you again. Begin your entry with:
            "list", "mark", "unmark", "todo",
            "deadline", "event" or "delete".""");
        printLineSeparator();
    }
}
