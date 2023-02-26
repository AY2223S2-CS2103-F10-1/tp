package seedu.address.model.mapping.exceptions;

/**
 * Signals that the operation will result in duplicate PersonTasks (PersonTasks
 * are considered duplicates if they have the same identity).
 */
public class DuplicatePersonTaskException extends RuntimeException {
    public DuplicatePersonTaskException() {
        super("Operation would result in duplicate personTasks");
    }
}
