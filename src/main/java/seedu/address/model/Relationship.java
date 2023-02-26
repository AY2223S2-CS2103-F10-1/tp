package seedu.address.model;

/**
 * The relationship logic for OfficeConnect Model
 */
public interface Relationship<T> {
    boolean isSame(T obj);
}
