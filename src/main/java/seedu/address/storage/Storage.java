package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyRepository;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.mapping.PersonTask;
import seedu.address.model.task.Task;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    Optional<ReadOnlyRepository<Task>> readTaskBook() throws DataConversionException, IOException;

    void saveTaskBook(ReadOnlyRepository<Task> taskBook) throws IOException;

    Optional<ReadOnlyRepository<PersonTask>> readPersonTaskBook() throws DataConversionException, IOException;

    void savePersonTaskBook(ReadOnlyRepository<PersonTask> personTaskBook) throws IOException;

}
