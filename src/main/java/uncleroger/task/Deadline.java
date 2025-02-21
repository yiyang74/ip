package uncleroger.task;

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
