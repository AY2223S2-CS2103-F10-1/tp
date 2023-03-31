package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.LogsCenter;

/**
 * Represents the in-memory model of the repository data.
 */
public class RepositoryModelManager<T extends Relationship<T>> {
    private static final Logger logger = LogsCenter.getLogger(RepositoryModelManager.class);
    private final Repository<T> repo;
    private final FilteredList<T> itemFilteredList;
    // private final ObservableList<T> internalUnmodifiableList;

    /**
     * Initializes an empty RepositoryModelManager.
     */
    public RepositoryModelManager() {

        logger.fine("Initializing with repo: ");
        this.repo = Repository.of(new Repository<T>());
        this.itemFilteredList = new FilteredList<>(this.repo.getData());
        // this.internalUnmodifiableList = FXCollections.unmodifiableObservableList(itemFilteredList);
    }

    /**
     * Initializes a RepositoryModelManager with the given repo.
     */
    public RepositoryModelManager(ReadOnlyRepository<T> repo) {
        requireNonNull(repo);

        logger.fine("Initializing with repo: " + repo);

        this.repo = Repository.of(repo);
        itemFilteredList = new FilteredList<>(this.repo.getData());
        // this.internalUnmodifiableList = FXCollections.unmodifiableObservableList(itemFilteredList);

    }

    public void setRepository(Repository<T> repo) {
        this.repo.resetData(repo);
    }

    public void setRepository(List<T> items) {
        this.repo.setItems(items);
    }

    public ReadOnlyRepository<T> getReadOnlyRepository() {
        return repo;
    }

    public List<T> filter(Predicate<T> predicate) {
        return repo.getFilterData(predicate);
    }

    public List<T> filterReadOnlyList(Predicate<T> predicate) {
        return repo.getData().filtered(predicate);
    }


    /**
     * Returns true if an item with the same identity as {@code item} exists in the repo.
     */
    public boolean hasItem(T item) {
        requireNonNull(item);
        return repo.hasItem(item);
    }

    /**
     * Deletes the given item.
     * {@code target} must exist in the repo.
     */
    public void deleteItem(T target) {
        repo.removeItem(target);
    }

    /**
     * Adds the given item.
     * {@code item} must not already exist in the repo.
     */
    public void addItem(T item) {
        repo.addItem(item);
        showAllItems();
    }

    /**
     * Edits the given item.
     * {@code target} must exist in the repo.
     */

    public void setItem(T target, T editedItem) {
        requireAllNonNull(target, editedItem);

        repo.setItem(target, editedItem);
    }

    //=========== Filtered Person List Accessors =============================================================
    public ObservableList<T> filterItemList(Predicate<T> predicate) {
        return itemFilteredList.filtered(predicate);
    }

    public int getFilteredListSize() {
        return itemFilteredList.size();
    }

    public ObservableList<T> getFilteredItemList() {
        return itemFilteredList;
    }

    public FilteredList<T> getFilteredItemList(Predicate<T> predicate) {
        updateFilteredItemList(predicate);
        return itemFilteredList;
    }
    public T getFilterItem(int index) {
        return itemFilteredList.get(index);
    }

    /**
     * Updates the filter of the filtered item list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    public void updateFilteredItemList(Predicate<T> predicate) {
        requireNonNull(predicate);
        itemFilteredList.setPredicate(predicate);
    }

    public void showAllItems() {
        updateFilteredItemList(x -> true);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof RepositoryModelManager<?>)) {
            return false;
        }

        // state check
        RepositoryModelManager<?> other = (RepositoryModelManager<?>) obj;
        return repo.equals(other.repo)
            && itemFilteredList.equals(other.itemFilteredList);
    }

}
