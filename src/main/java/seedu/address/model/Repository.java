package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;

/**
 * Wraps all data at the repository level.
 */
public class Repository<T extends Relationship<T>> implements ReadOnlyRepository<T> {

    private final UniqueItemList<T> items;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        items = new UniqueItemList<>();
    }

    public Repository() {
    }

    /**
     * Creates a Repository using the data in the {@code toBeCopied}.
     */
    private Repository(ReadOnlyRepository<T> toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public static <S extends Relationship<S>> Repository<S> of(ReadOnlyRepository<S> toBeCopied) {
        return new Repository<>(toBeCopied);
    }
    //// list overwrite operations

    /**
     * Replaces the contents of the item list with {@code items}.
     * {@code items} must not contain duplicate items.
     */
    public void setItems(List<T> items) {
        this.items.setItems(items);
    }

    /**
     * Resets the existing data of this {@code items} with {@code newData}.
     */
    public void resetData(ReadOnlyRepository<T> newData) {
        requireNonNull(newData);

        setItems(newData.getData());
    }

    //// task-level operations

    /**
     * Returns true if a item with the same identity as {@code item} exists in the repository.
     */
    public boolean hasItem(T item) {
        requireNonNull(item);
        return items.contains(item);
    }

    /**
     * Adds an item to the repository.
     * The item must not already exist in the repository.
     */
    public void addItem(T item) {
        items.add(item);
    }

    /**
     * Replaces the given item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the repository.
     * The item identity of {@code editedItem} must not be the same as another existing task in the repository.
     */
    public void setItem(T target, T editedItem) {
        requireNonNull(editedItem);

        items.setItem(target, editedItem);
    }

    /**
     * Removes {@code key} from this {@code Repository}.
     * {@code key} must exist in the repository.
     */
    public void removeItem(T key) {
        items.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return items.asUnmodifiableObservableList().size() + " tasks";
        // TODO: refine later

    }

    @Override
    public ObservableList<T> getData() {
        return items.asUnmodifiableObservableList();
    }

    @Override
    public List<T> getFilterData(Predicate<T> predicate) {
        return items.asUnmodifiableObservableList().filtered(predicate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Repository<?> // instanceof handles nulls
            && items.equals(((Repository<?>) other).items));
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}
