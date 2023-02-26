package seedu.address.model.shared;

import java.util.Objects;
import java.util.UUID;


/**
 * Represents an ID to identify the object
 */
public class Id {
    /*
     * Must be valid UUID representation
     */
    public static final String VALIDATION_REGEX = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$";
    public static final String MESSAGE_CONSTRAINTS =
        "UUIDs format is in 5 groups of hexadecimal digits, "
            + "separated by hyphens. The length of each group is: 8-4-4-4-12";
    private final UUID value;
    /**
     * Constructs a {@code Id}.
     **/
    public Id() {
        this.value = UUID.randomUUID();
    }

    public Id(String id) {
        this.value = UUID.fromString(id);
    }

    public UUID getValue() {
        return value;
    }
    /**
     * Returns true if a given string is a valid id.
     */
    public static boolean isInValidId(String test) {
        return !test.matches(VALIDATION_REGEX);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Id) // instanceof handles nulls
            && value.equals(((Id) other).value); // state check
    }
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
