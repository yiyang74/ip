package uncleroger.task;

/**
 * The Todo class represents a simple to-do task in the Uncle Roger task management application.
 * <p>
 * This class extends the abstract Task class and provides a concrete implementation for a to-do task.
 * It includes methods to get and set the task's description and completion status, as well as a method
 * to get a status icon representing the task's completion status.
 *
 * @author Chen Yiyang
 */
public class Todo extends Task {
    protected boolean isDone;
    protected String description;

    public Todo(String description) {
        this.description = description;
    }

    public Todo(String description, boolean isDone) {
        super();
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }
}
