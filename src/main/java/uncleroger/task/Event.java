package uncleroger.task;

/**
 * The Event class represents a task with a specific time range
 * in the Uncle Roger task management application.
 * <p>
 * This class extends the Todo class and adds attributes for the start and end times of the event.
 * It provides methods to get the start and end times and overrides the toString method to include
 * these times in the task representation.
 *
 * @author Chen Yiyang
 */
public class Event extends Todo {
    protected String from;
    protected String to;

    public Event(String from, String to, String description) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String from, String to, String description, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                getStatusIcon(), description, from, to);
    }
}
