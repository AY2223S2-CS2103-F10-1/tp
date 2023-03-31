package seedu.address.model;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;


/**
 * Unmodifiable view of a repository.
 */
public interface ReadOnlyRepository<T> {
    /**
     * Returns an unmodifiable view of the repository.
     */
    ObservableList<T> getData();

    List<T> getFilterData(Predicate<T> predicate);
}
