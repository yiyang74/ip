package uncleroger.ui;

import uncleroger.task.Task;

import java.util.ArrayList;
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
        System.out.println("Haiya! You haven't added anything to your list yet.");
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
            System.out.println("Haiya...Uncle Roger disappointed in you, don't be lazy!");
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
        System.out.println("Hallo, I am\n" +
                " _   _            _       ______                      \n" +
                        "| | | |          | |      | ___ \\                     \n" +
                        "| | | |_ __   ___| | ___  | |_/ /___   __ _  ___ _ __ \n" +
                        "| | | | '_ \\ / __| |/ _ \\ |    // _ \\ / _` |/ _ \\ '__|\n" +
                        "| |_| | | | | (__| |  __/ | |\\ \\ (_) | (_| |  __/ |   \n" +
                        " \\___/|_| |_|\\___|_|\\___| \\_| \\_\\___/ \\__, |\\___|_|   \n" +
                        "                                       __/ |          \n" +
                        "                                      |___/           \n" +
                "What you want me to do?");
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
     * Prints a message indicating that the /by field is empty
     */
    public static void printNoByFieldException() {
        printLineSeparator();
        System.out.println("Haiya...let Uncle Roger remind you,\n" +
                "please include something for after /by for Deadline task");
        printLineSeparator();
    }

    /**
     * Prints a message indicating that the /from field is empty
     */
    public static void printNoFromFieldException() {
        printLineSeparator();
        System.out.println("Haiya...let Uncle Roger remind you,\n" +
                "please include something for after /from for Event task");
        printLineSeparator();
    }

    /**
     * Prints a message indicating that the /to field is empty
     */
    public static void printNoToFieldException() {
        printLineSeparator();
        System.out.println("Haiya...let Uncle Roger remind you,\n" +
                "please include something for after /to for Event task");
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
     * Prints a message indicating that the task index is missing.
     */
    public static void printNoIndex() {
        printLineSeparator();
        System.out.println("Haiya...you never enter a number,\n" +
                "waste Uncle Roger's time!");
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

    /**
     * Prints a message indicating that the user needs to specify a keyword for searching tasks.
     * <p>
     * This method prints a message prompting the user to provide a keyword.
     */
    public static void printEmptyFind() {
        printLineSeparator();
        System.out.println("Haiya! You need to tell Uncle Roger what to find.");
        printLineSeparator();
    }

    /**
     * Prints a message indicating that no tasks were found with the specified keyword.
     * <p>
     * This method prints a message indicating that no tasks match the keyword.
     */
    public static void printCannotFind() {
        printLineSeparator();
        System.out.println("Your list don't have any tasks with that keyword!\n" +
                "Waste Uncle Roger time haiya.");
        printLineSeparator();
    }

    /**
     * Prints a list of tasks that contain the specified keyword.
     * <p>
     * This method prints a message indicating that tasks have been found,
     * and then lists each task with its index.
     *
     * @param tasksWithSubstring The list of tasks that contain the specified keyword.
     */
    public static void printTasksWithSubstring(ArrayList<Task> tasksWithSubstring) {
        printLineSeparator();
        System.out.println("Uncle Roger found these tasks for you.\n" +
                "Better say thank you to Uncle Roger.");
        int i = 1;
        for (Task task : tasksWithSubstring) {
            System.out.println(i + ":" + task);
            i++;
        }
        printLineSeparator();
    }
}
