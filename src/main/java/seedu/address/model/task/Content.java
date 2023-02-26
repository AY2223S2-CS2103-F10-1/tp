package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's content in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidContent(String)}
 */
public class Content {

    public static final String MESSAGE_CONSTRAINTS = "Content can take any values, and it should not be blank";

    /*
     * The first character of the content must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String value;

    /**
     * Constructs an {@code Content}.
     *
     * @param value A valid content.
     */
    public Content(String value) {
        requireNonNull(value);
        checkArgument(isValidContent(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Returns true if a given string is a valid content.
     */
    public static boolean isValidContent(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Content // instanceof handles nulls
            && value.equals(((Content) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
