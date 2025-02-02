import java.util.Scanner;

public class Beru {

    private static final String LINE_SEPARATOR = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    private static void printList(Task[] items, int itemsLength) {
        System.out.print(LINE_SEPARATOR);
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < itemsLength; i++) {
            System.out.println((i + 1) +
                    ".[" + items[i].getStatusIcon() + "] " + items[i].getDescription());
        }
        System.out.print(LINE_SEPARATOR);
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

    public static void main(String[] args) {
        System.out.print(
                LINE_SEPARATOR + "Hello! I'm Beru\n" +
                "What can I do for you?\n" + LINE_SEPARATOR
        );
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        int i = 0;
        int tasksArrIndex = 0;  // Index for arrays, starts from 0
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                printList(tasks, i);
            } else if (command.startsWith("mark")) {
                tasksArrIndex = Integer.parseInt(command.substring(5)) - 1;
                if (tasksArrIndex >= i) {
                    System.out.print(LINE_SEPARATOR + "No entry for task " +
                            (tasksArrIndex + 1) + " yet! :(\n" + LINE_SEPARATOR);
                } else if (tasks[tasksArrIndex].getIsDone()) {
                    System.out.print(LINE_SEPARATOR +
                            "Entry has already been marked as done!\n" + LINE_SEPARATOR);
                } else {
                    tasks[tasksArrIndex].setIsDone(true);
                    printTaskStatus(tasks[tasksArrIndex]);
                }
            } else if (command.startsWith("unmark")) {
                tasksArrIndex = Integer.parseInt(command.substring(7)) - 1;
                if (tasksArrIndex >= i) {
                    System.out.print(LINE_SEPARATOR + "No entry for task " +
                            (tasksArrIndex + 1) + " yet! :(\n" + LINE_SEPARATOR);
                } else if (!tasks[tasksArrIndex].getIsDone()) {
                    System.out.print(LINE_SEPARATOR +
                            "Entry is already unmarked!\n" + LINE_SEPARATOR);
                } else {
                    tasks[tasksArrIndex].setIsDone(false);
                    printTaskStatus(tasks[tasksArrIndex]);
                }
            } else {
                tasks[i++] = new Task(command);
                System.out.print(LINE_SEPARATOR + "added: " + command + '\n' + LINE_SEPARATOR);
            }
            command = in.nextLine();
        }
        System.out.print(LINE_SEPARATOR +
                "Bye... :(\nHope to see you again soon! >///<\n" + LINE_SEPARATOR);
    }
}
