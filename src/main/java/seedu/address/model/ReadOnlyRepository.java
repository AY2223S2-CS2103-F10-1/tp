package seedu.address.model;

import javafx.collections.ObservableList;


/**
 * Unmodifiable view of a repository
 */
public interface ReadOnlyRepository<T> {
    /**
     * Returns an unmodifiable view of the repository.
     */
    ObservableList<T> getReadOnlyRepository();

}
