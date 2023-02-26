package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.LogsCenter;

/**
 * Represents the in-memory model of the repository data.
 */
public class RepositoryModelManager<T extends Relationship<T>> {
    private static final Logger logger = LogsCenter.getLogger(RepositoryModelManager.class);

    private final Repository<T> repo;
    private final FilteredList<T> itemFilteredList;


    /**
     * Initializes a RepositoryModelManager with the given taskBook
     */
    public RepositoryModelManager(ReadOnlyRepository<T> repo) {
        requireNonNull(repo);

        logger.fine("Initializing with repo book: " + repo);

        this.repo = Repository.of(repo);
        itemFilteredList = new FilteredList<>(this.repo.getReadOnlyRepository());
    }

    public void setRepository(Repository<T> repo) {
        this.repo.resetData(repo);
    }

    public ReadOnlyRepository<T> getReadOnlyRepository() {
        return repo;
    }

    // public boolean hasItem(T item) {
    //     requireNonNull(item);
    //     return repo.hasItem(item);
    // }

    // public void deleteItem(T target) {
    //     repo.removeItem(target);
    // }
    //
    // public void addItem(T item) {
    //     repo.addItem(item);
    //     showAllItems();
    // }
    //
    // public void setItem(T target, T editedItem) {
    //     requireAllNonNull(target, editedItem);
    //
    //     repo.setItem(target, editedItem);
    // }

    //=========== Filtered Person List Accessors =============================================================

    // public ObservableList<T> getFilteredItemList() {
    //     return itemFilteredList;
    // }
    //
    // public void updateFilteredItemList(Predicate<T> predicate) {
    //     requireNonNull(predicate);
    //     itemFilteredList.setPredicate(predicate);
    // }

    // public void showAllItems() {
    //     updateFilteredItemList(x -> true);
    // }

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
