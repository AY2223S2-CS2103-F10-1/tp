package seedu.address.model;

/**
 * The relationship logic for OfficeConnectModel.
 */
public interface Relationship<T> {
    boolean isSame(T obj);
    boolean hasSameId(T obj);
}
