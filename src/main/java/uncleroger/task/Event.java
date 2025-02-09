package uncleroger.task;

public class Event extends Todo {
    protected String from;
    protected String to;

    public Event(String from, String to, String description) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                getStatusIcon(), description, from, to);
    }
}
