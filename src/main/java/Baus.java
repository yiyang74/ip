import java.util.Scanner;

public class Baus {
    public static void main(String[] args) {
        String lineSeparator = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        System.out.print(
                lineSeparator +
                "Hello! I'm Baus\n" +
                "What can I do for you?\n" +
                lineSeparator +
                "Bye. Hope to see you again soon!\n" +
                lineSeparator
        );
        Scanner in = new Scanner(System.in);
        String task = in.nextLine();
        while (!task.equals("bye")) {
            System.out.print(lineSeparator + task + '\n' + lineSeparator);
            task = in.nextLine();
        }
        System.out.print(lineSeparator + "Bye... :(\nHope to see you again soon! >///<\n" + lineSeparator);
    }
}
