public class Task {
    protected boolean isDone;
    protected static int taskCount = 0;

    public Task() {
        this.isDone = false;
        taskCount++;
    }

    public static int getTaskCount() {
        return taskCount;
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
        return String.format("[ ][%s]", getStatusIcon());
    }
}