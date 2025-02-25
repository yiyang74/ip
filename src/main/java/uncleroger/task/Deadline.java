package uncleroger.task;

/**
 * The Deadline class represents a task with a specific deadline
 * in the Uncle Roger task management application.
 * <p>
 * This class extends the Todo class and adds a deadline attribute.
 * It provides methods to get the deadline
 * and override the toString method to include the deadline in the task representation.
 *
 * @author Chen Yiyang
 */
public class Deadline extends Todo {
    protected String by;

    public Deadline(String by, String description) {
        super(description);
        this.by = by;
    }

    public Deadline(String by, String description, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, by);
    }
}
