import java.util.Scanner;

public class Baus {

    private static final String lineSeparator = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    private static void printList(String[] items, int itemsLength) {
        System.out.print(lineSeparator);
        for (int i = 0; i < itemsLength; i++) {
            System.out.println((i + 1) + ". " + items[i]);
        }
        System.out.print(lineSeparator);
    }

    public static void main(String[] args) {
        System.out.print(
                lineSeparator +
                "Hello! I'm Baus\n" +
                "What can I do for you?\n" +
                lineSeparator +
                "Bye. Hope to see you again soon!\n" +
                lineSeparator
        );
        String[] tasks = new String[100];
        Scanner in = new Scanner(System.in);
        String task = in.nextLine();
        int i = 0;
        while (!task.equals("bye")) {
            if (task.equals("list")) {
                printList(tasks, i);
            } else {
                tasks[i++] = task;
                System.out.print(lineSeparator + "added: " + task + '\n' + lineSeparator);
            }
            task = in.nextLine();
        }
        System.out.print(lineSeparator + "Bye... :(\nHope to see you again soon! >///<\n" + lineSeparator);
    }
}
