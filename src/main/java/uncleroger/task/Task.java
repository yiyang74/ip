package uncleroger.task;

/**
 * The Task class represents a generic task in the Uncle Roger task management application.
 * <p>
 * This abstract class provides a basic structure for tasks,
 * including methods to check if a task is done
 * and to set the task's completion status.
 * Concrete subclasses should implement specific types of tasks,
 * such as Todo, Deadline, and Event.
 *
 * @author Chen Yiyang
 */
public abstract class Task {

    public abstract boolean getIsDone();

    public abstract void setIsDone(boolean isDone);

    public abstract String getDescription();
}