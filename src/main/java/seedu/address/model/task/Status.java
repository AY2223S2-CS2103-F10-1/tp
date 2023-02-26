package seedu.address.model.task;

import java.util.Objects;

/**
 * Represents a Task's status in the address book.
 * Guarantees: immutable;
 */
public class Status {
    /*
     * Must be valid boolean representation
     */
    public static final String VALIDATION_REGEX = "(?i)(true|false)";

    public static final String MESSAGE_CONSTRAINTS =
        "Status is true or false value";
    private final boolean value;

    /**
     * Constructs a {@code Status}.
     *
     * @param value status of a task;
     */
    public Status(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }
    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    @Override
    public String toString() {

        return value ? "Done" : "Undone";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Status // instanceof handles nulls
            && value == ((Status) other).value); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

}
