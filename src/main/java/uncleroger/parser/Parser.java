package uncleroger.parser;

import uncleroger.exception.AlreadyMarkedException;
import uncleroger.exception.AlreadyUnmarkedException;
import uncleroger.exception.EmptyListException;
import uncleroger.exception.InvalidCommandException;
import uncleroger.exception.InvalidDeadlineException;
import uncleroger.exception.InvalidEventFieldOrderException;
import uncleroger.exception.MissingEventFieldsException;
import uncleroger.exception.NoDescriptionException;
import uncleroger.exception.NoEntryYetException;
import uncleroger.exception.NonPositiveIndexException;
import uncleroger.exception.EmptyFindException;
import uncleroger.exception.NoTaskWithSubstringException;
import uncleroger.task.Deadline;
import uncleroger.task.Event;
import uncleroger.task.Task;
import uncleroger.task.Todo;
import uncleroger.ui.TextUi;

import java.util.ArrayList;
import java.util.Arrays;

import static uncleroger.UncleRoger.tasks;

/**
 * The Parser class handles the parsing and execution of user commands
 * in the Uncle Roger task management application.
 * <p>
 * This class provides methods for handling various commands
 * such as marking tasks as done, unmarking tasks, deleting tasks,
 * listing tasks, and adding new tasks (Todo, Deadline and Event).
 * It ensures that user input is correctly interpreted and the appropriate actions are taken.
 *
 * @author Chen Yiyang
 */
public class Parser {

    private static String combineWordsToSentence(String[] words, int start, int end) {
        words = Arrays.copyOfRange(words, start, end);
        return String.join(" ", words);
    }

    private static void handleMarkCommand(String[] words)
            throws NoEntryYetException, AlreadyMarkedException,
            NonPositiveIndexException {
        int userEnteredEntry = Integer.parseInt(words[1]);
        int userEnteredArrIndex = userEnteredEntry - 1;
        if (userEnteredEntry <= 0) {
            throw new NonPositiveIndexException();
        } else if (userEnteredArrIndex >= tasks.size()) {
            throw new NoEntryYetException();
        } else if (tasks.get(userEnteredArrIndex).getIsDone()) {
            throw new AlreadyMarkedException();
        } else {
            tasks.get(userEnteredArrIndex).setIsDone(true);
            TextUi.printTaskStatus(tasks.get(userEnteredArrIndex));
        }
    }

    private static void handleUnmarkedCommand(String[] words)
            throws NoEntryYetException, AlreadyUnmarkedException, NonPositiveIndexException {
        int userEnteredEntry = Integer.parseInt(words[1]);
        int userEnteredArrIndex = userEnteredEntry - 1;
        if (userEnteredEntry <= 0) {
            throw new NonPositiveIndexException();
        } else if (userEnteredArrIndex >= tasks.size()) {
            throw new NoEntryYetException();
        } else if (!tasks.get(userEnteredArrIndex).getIsDone()) {
            throw new AlreadyUnmarkedException();
        } else {
            tasks.get(userEnteredArrIndex).setIsDone(false);
            TextUi.printTaskStatus(tasks.get(userEnteredArrIndex));
        }
    }

    private static void handleDeleteCommand(String[] words)
            throws NonPositiveIndexException, NoEntryYetException {
        int userEnteredEntry = Integer.parseInt(words[1]);
        int userEnteredArrIndex = userEnteredEntry - 1;
        if (userEnteredEntry <= 0) {
            throw new NonPositiveIndexException();
        } else if (userEnteredArrIndex >= tasks.size()) {
            throw new NoEntryYetException();
        } else {
            TextUi.printTaskRemove(tasks.get(userEnteredArrIndex));
            tasks.remove(userEnteredArrIndex);
        }
    }

    private static void handleListCommand() throws EmptyListException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        } else {
            TextUi.printList();
        }
    }

    private static void handleTodoCommand(String[] words)
            throws NoDescriptionException {
        if (words.length < 2) {
            throw new NoDescriptionException();
        }
        String description = combineWordsToSentence(words, 1, words.length);
        tasks.add(new Todo(description));
        TextUi.printTaskEntry();
    }

    private static void handleDeadlineCommand(String[] words)
            throws NoDescriptionException, InvalidDeadlineException {
        if (words.length < 2) {
            throw new NoDescriptionException();
        }
        int indexOfBy = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase("/by")) {
                indexOfBy = i;
                break;
            }
        }
        if (indexOfBy == -1) {
            throw new InvalidDeadlineException();
        }
        String description = combineWordsToSentence(words, 1, indexOfBy);
        String by = combineWordsToSentence(words, indexOfBy + 1, words.length);
        tasks.add(new Deadline(by, description));
        TextUi.printTaskEntry();
    }

    private static void handleEventCommand(String[] words)
            throws NoDescriptionException, MissingEventFieldsException,
            InvalidEventFieldOrderException {
        if (words.length < 2) {
            throw new NoDescriptionException();
        }
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
            throw new MissingEventFieldsException();
        }
        if (indexOfFrom > indexOfTo) {
            throw new InvalidEventFieldOrderException();
        }
        String description = combineWordsToSentence(words, 1, indexOfFrom);
        String from = combineWordsToSentence(words, indexOfFrom + 1, indexOfTo);
        String to = combineWordsToSentence(words, indexOfTo + 1, words.length);
        tasks.add(new Event(from, to, description));
        TextUi.printTaskEntry();
    }

    private static void handleFindCommand(String[] words)
            throws EmptyListException, EmptyFindException, NoTaskWithSubstringException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }
        if (words.length == 1) {
            throw new EmptyFindException();
        }
        String substring = combineWordsToSentence(words, 1, words.length).toLowerCase();
        ArrayList<Task> tasksWithSubstring = new ArrayList<>();
        for (Task task : tasks) {
            String description = task.getDescription().toLowerCase();
            if (description.contains(substring)) {
                tasksWithSubstring.add(task);
            }
        }
        if (tasksWithSubstring.isEmpty()) {
            throw new NoTaskWithSubstringException();
        }
        TextUi.printTasksWithSubstring(tasksWithSubstring);
    }

    /**
     * Parses a user input sentence and executes the corresponding command.
     * <p>
     * This method takes a user input sentence, splits it into words, and identifies the command.
     * It then delegates the execution to specific command handlers based on the command type.
     * Each command handler is responsible for performing the necessary actions and handling any exceptions.
     *
     * @param sentence The user input sentence to be parsed and executed.
     * @throws InvalidCommandException If the command is not recognized.
     */
    public static void parseUserSentence(String sentence)
            throws InvalidCommandException {
        String[] words = sentence.split(" ");
        String command = words[0];
        switch (command.toLowerCase()) {
        case "list":
            try {
                handleListCommand();
            } catch (EmptyListException e) {
                TextUi.printEmptyList();
            }
            break;
        case "mark":
            try {
                handleMarkCommand(words);
            } catch (NoEntryYetException e) {
                TextUi.printNoEntryYet();
            } catch (AlreadyMarkedException e) {
                TextUi.printAlreadyMarked();
            } catch (NonPositiveIndexException e) {
                TextUi.printNonPositiveIndex();
            }
            break;
        case "unmark":
            try {
                handleUnmarkedCommand(words);
            } catch (NoEntryYetException e) {
                TextUi.printNoEntryYet();
            } catch (AlreadyUnmarkedException e) {
                TextUi.printAlreadyUnmarked();
            } catch (NonPositiveIndexException e) {
                TextUi.printNonPositiveIndex();
            }
            break;
        case "todo":
            try {
                handleTodoCommand(words);
            } catch (NoDescriptionException e) {
                TextUi.printNoDescription();
            }
            break;
        case "deadline":
            try {
                handleDeadlineCommand(words);
            } catch (NoDescriptionException e) {
                TextUi.printNoDescription();
            } catch (InvalidDeadlineException e) {
                TextUi.printInvalidDeadline();
            }
            break;
        case "event":
            try {
                handleEventCommand(words);
            } catch (NoDescriptionException e) {
                TextUi.printNoDescription();
            } catch (MissingEventFieldsException e) {
                TextUi.printMissingEventFields();
            } catch (InvalidEventFieldOrderException e) {
                TextUi.printInvalidEventFieldOrder();
            }
            break;
        case "delete":
            try {
                handleDeleteCommand(words);
            } catch (NonPositiveIndexException e) {
                TextUi.printNonPositiveIndex();
            } catch (NoEntryYetException e) {
                TextUi.printNoEntryYet();
            }
            break;
        case "find":
            try {
                handleFindCommand(words);
            } catch (EmptyListException e) {
                TextUi.printEmptyList();
            } catch (EmptyFindException e) {
                TextUi.printEmptyFind();
            } catch (NoTaskWithSubstringException e) {
                TextUi.printCannotFind();
            }
            break;
        default:
            throw new InvalidCommandException();
        }
    }
}
