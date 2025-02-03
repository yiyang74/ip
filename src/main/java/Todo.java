public class Todo extends Task {
    protected String description;

    public Todo(String description) {
        super();
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }
}
