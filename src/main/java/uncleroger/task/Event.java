package uncleroger.task;

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
