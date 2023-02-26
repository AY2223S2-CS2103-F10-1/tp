package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyRepository;
import seedu.address.model.Repository;
import seedu.address.model.mapping.PersonTask;

/**
 * An Immutable person_task_book that is serializable to JSON format.
 */
@JsonRootName(value = "person_task_book")
class JsonSerializablePersonTaskBook {

    public static final String MESSAGE_DUPLICATE = "PersonTask list contains duplicate personTask(s).";

    @JsonProperty("person_task")
    private final List<JsonAdaptedPersonTask> personTasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePersonTaskBook} with the given tasks.
     */
    @JsonCreator
    public JsonSerializablePersonTaskBook(List<JsonAdaptedPersonTask> personTasks) {
        this.personTasks.addAll(personTasks);
    }

    /**
     * Converts a given {@code ReadOnlyRepository} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePersonTaskBook}.
     */
    public JsonSerializablePersonTaskBook(ReadOnlyRepository<PersonTask> source) {
        personTasks.addAll(source.getReadOnlyRepository().stream()
            .map(JsonAdaptedPersonTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this personTask book into the model's {@code PersonTaskBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Repository<PersonTask> toModelType() throws IllegalValueException {
        Repository<PersonTask> personTaskBook = new Repository<>();
        for (JsonAdaptedPersonTask jsonAdaptedPersonTask : personTasks) {
            PersonTask personTask = jsonAdaptedPersonTask.toModelType();
            if (personTaskBook.hasItem(personTask)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE);
            }
            personTaskBook.addItem(personTask);
        }
        return personTaskBook;
    }

}
