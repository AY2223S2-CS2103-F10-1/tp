package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyRepository;

/**
 * Represents a general storage.
 */
public interface RepositoryStorage<T> {


    /**
     * Returns Repository data as a {@link ReadOnlyRepository}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyRepository<T>> readRepository() throws DataConversionException, IOException;


    Optional<ReadOnlyRepository<T>> readRepository(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyRepository} to the storage.
     * @param repository cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveRepository(ReadOnlyRepository<T> repository) throws IOException;

    /**
     * @see #saveRepository(ReadOnlyRepository)
     */
    void saveRepository(ReadOnlyRepository<T> repository, Path filePath) throws IOException;

}
