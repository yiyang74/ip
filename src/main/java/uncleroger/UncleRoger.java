package uncleroger;

import uncleroger.exception.InvalidCommandException;
import uncleroger.storage.Storage;
import uncleroger.task.Task;
import uncleroger.ui.TextUi;
import uncleroger.parser.Parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The main class for the Uncle Roger task management application.
 * <p>
 * This class is responsible for initializing the application, processing user input,
 * and managing the task list. It integrates with other classes such as
 * `TextUi`, `Storage`, and `Parser` to provide a complete task management experience.
 *
 * @author Chen Yiyang
 */
public class UncleRoger {

    public static final ArrayList<Task> tasks = new ArrayList<>();
    private static final TextUi ui = new TextUi();

    public static void main(String[] args) {
        TextUi.printGreeting();
        Storage.createDirectory();
        Storage.createDataFile();
        try {
            Storage.processDataFromFile();
        } catch (FileNotFoundException e) {
            System.out.println("UncleRoger.txt not found");
        }
        while (true) {
            String sentence = ui.in.nextLine().trim();
            if (sentence.equalsIgnoreCase("bye")) {
                break;
            }
            try {
                Parser.parseUserSentence(sentence);
            } catch (InvalidCommandException e) {
                TextUi.printInvalidCommand();
            }
        }
        Storage.loadDataIntoFile();
        TextUi.printGoodbye();
    }
}
