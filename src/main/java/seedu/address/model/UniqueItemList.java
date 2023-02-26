package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;


/**
 * A list of item that enforces uniqueness between its elements and does not allow nulls.
 */
public class UniqueItemList<T extends Relationship<T>> implements Iterable<T> {

    private final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSame);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        internalList.add(toAdd);
    }

    // /**
    //  * Replaces the task {@code target} in the list with {@code editedTask}.
    //  * {@code target} must exist in the list.
    //  * The task identity of {@code editedTask} must not be the same as another existing task in the list.
    //  */
    // public void setItem(T target, T editedItem) {
    //     requireAllNonNull(target, editedItem);
    //
    //     int index = internalList.indexOf(target);
    //     if (index == -1) {
    //         throw new TaskNotFoundException();
    //     }
    //
    //     if (!target.isSame(editedItem) && contains(editedItem)) {
    //         throw new DuplicateTaskException();
    //     }
    //
    //     internalList.set(index, editedItem);
    // }

    /**
     * Removes the equivalent task from the list.
     * The task must exist in the list.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setItems(List<T> items) {
        requireAllNonNull(items);
        if (!tasksAreUnique(items)) {
            throw new DuplicateTaskException();
        }

        internalList.setAll(items);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueItemList<?> // instanceof handles nulls
            && internalList.equals(((UniqueItemList<?>) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code items} contains only unique items.
     */
    private boolean tasksAreUnique(List<T> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).isSame(items.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
