package uncleroger.task;

public abstract class Task {
    protected static int taskCount = 0;

    public Task() {
        taskCount++;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public abstract boolean getIsDone();

    public abstract void setIsDone(boolean isDone);
}