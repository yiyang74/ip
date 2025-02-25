package uncleroger.ui;

import uncleroger.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

import static uncleroger.UncleRoger.tasks;

public class TextUi {

    private static void printLineSeparator() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public final Scanner in;

    public TextUi() {
        in = new Scanner(System.in);
    }

    public static void printList() {
        printLineSeparator();
        System.out.println("Come. Uncle Roger remind you what tasks you have: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printLineSeparator();
    }

    public static void printEmptyList() {
        printLineSeparator();
        System.out.println("Haiya! You haven't added anything to your list yet.");
        printLineSeparator();
    }

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

    public static void printTaskRemove(Task t) {
        printLineSeparator();
        System.out.println("Ok. Uncle Roger help you remove this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in your list.");
        printLineSeparator();
    }

    public static void printGreeting() {
        printLineSeparator();
        System.out.println("Hallo, I am Uncle Roger.\nWhat you want me to do?");
        printLineSeparator();
    }

    public static void printGoodbye() {
        printLineSeparator();
        System.out.println("Haiya...Goodbye.\nDon't like Uncle Roger just say.");
        printLineSeparator();
    }

    public static void printNoEntryYet() {
        printLineSeparator();
        System.out.println("Aiyoo...don't even have this task yet!\nKan Cheong Spider!");
        printLineSeparator();
    }

    public static void printAlreadyMarked() {
        printLineSeparator();
        System.out.println("Haiya...this task already mark as done.\n" +
                "Go do something else");
        printLineSeparator();
    }

    public static void printAlreadyUnmarked() {
        printLineSeparator();
        System.out.println("Haiya...this task is already unmarked.\n" +
                "Stop procrastinating!");
        printLineSeparator();
    }

    public static void printTaskEntry() {
        printLineSeparator();
        System.out.println("Ok. Uncle Roger add this task for you:\n  " +
                tasks.get(tasks.size() - 1) + "\nDon't forget, now you have " +
                tasks.size() + " tasks in your list.");
        printLineSeparator();
    }

    public static void printInvalidDeadline() {
        printLineSeparator();
        System.out.println("Haiya...invalid entry for Deadline!\n" +
                "Go include \"/by\" in your entry!");
        printLineSeparator();
    }

    public static void printMissingEventFields() {
        printLineSeparator();
        System.out.println("Haiya...invalid entry for Event!\n" +
                "Go include both \"/from\" and \"/to\" in your entry!");
        printLineSeparator();
    }

    public static void printInvalidEventFieldOrder() {
        printLineSeparator();
        System.out.println("Haiya...wrong entry order for Event!\n" +
                "Go enter \"/from\" before \"/to\" in your entry!");
        printLineSeparator();
    }

    public static void printNoDescription() {
        printLineSeparator();
        System.out.println("Haiya...your entry got no description!\n" +
                "Go re-type for Uncle Roger again!");
        printLineSeparator();
    }

    public static void printNonPositiveIndex() {
        printLineSeparator();
        System.out.println("Haiya...Uncle Roger remind you again,\n" +
                "task number entered must be more than 0!");
        printLineSeparator();
    }

    public static void printInvalidCommand() {
        printLineSeparator();
        System.out.println("""
                Haiya...Uncle Roger don't know what you typing!
                Uncle Roger remind you again. Begin your entry with:
                "list", "mark", "unmark", "todo",
                "deadline", "event" or "delete".""");
        printLineSeparator();
    }

    public static void printEmptyFind() {
        printLineSeparator();
        System.out.println("Haiya! You need to tell Uncle Roger what to find.");
        printLineSeparator();
    }

    public static void printCannotFind() {
        printLineSeparator();
        System.out.println("Your list don't have any tasks with that keyword!\n" +
                "Waste Uncle Roger time haiya.");
        printLineSeparator();
    }

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
