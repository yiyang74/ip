import java.util.Scanner;

public class Baus {

    private static final String lineSeparator = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    private static void printList(Task[] items, int itemsLength) {
        System.out.print(lineSeparator);
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < itemsLength; i++) {
            System.out.println((i + 1) +
                    ".[" + items[i].getStatusIcon() + "] " + items[i].getDescription());
        }
        System.out.print(lineSeparator);
    }

    private static void printTaskStatus(Task t) {
        System.out.print(lineSeparator);
        if (t.getIsDone()) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  [" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.print(lineSeparator);
    }

    public static void main(String[] args) {
        System.out.print(
                lineSeparator + "Hello! I'm Baus\n" +
                "What can I do for you?\n" + lineSeparator
        );
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        int i = 0;
        int taskIndex = 0;
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                printList(tasks, i);
            } else if (command.startsWith("mark")) {
                taskIndex = Integer.parseInt(command.substring(5)) - 1;
                if (taskIndex >= i) {
                    System.out.print(lineSeparator + "No entry for task " +
                            (taskIndex + 1) + " yet! :(\n" + lineSeparator);
                } else if (tasks[taskIndex].getIsDone()) {
                    System.out.print(lineSeparator +
                            "Entry has already been marked as done!\n" + lineSeparator);
                } else {
                    tasks[taskIndex].setIsDone(true);
                    printTaskStatus(tasks[taskIndex]);
                }
            } else if (command.startsWith("unmark")) {
                taskIndex = Integer.parseInt(command.substring(7)) - 1;
                if (taskIndex >= i) {
                    System.out.print(lineSeparator + "No entry for task " +
                            (taskIndex + 1) + " yet! :(\n" + lineSeparator);
                } else if (!tasks[taskIndex].getIsDone()) {
                    System.out.print(lineSeparator +
                            "Entry is already unmarked!\n" + lineSeparator);
                } else {
                    tasks[taskIndex].setIsDone(false);
                    printTaskStatus(tasks[taskIndex]);
                }
            } else {
                tasks[i++] = new Task(command);
                System.out.print(lineSeparator + "added: " + command + '\n' + lineSeparator);
            }
            command = in.nextLine();
        }
        System.out.print(lineSeparator +
                "Bye... :(\nHope to see you again soon! >///<\n" + lineSeparator);
    }
}
