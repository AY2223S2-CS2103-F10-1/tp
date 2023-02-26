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
import seedu.address.model.task.Task;


/**
 * An Immutable task_book that is serializable to JSON format.
 */
@JsonRootName(value = "task_book")
class JsonSerializableTaskBook {

    public static final String MESSAGE_DUPLICATE = "Tasks list contains duplicate task(s).";

    @JsonProperty("tasks")
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskBook} with the given tasks.
     */
    @JsonCreator
    public JsonSerializableTaskBook(List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyRepository} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskBook}.
     */
    public JsonSerializableTaskBook(ReadOnlyRepository<Task> source) {
        tasks.addAll(source.getReadOnlyRepository().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this task book into the model's {@code TaskBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Repository<Task> toModelType() throws IllegalValueException {
        Repository<Task> taskBook = new Repository<>();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (taskBook.hasItem(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE);
            }
            taskBook.addItem(task);
        }
        return taskBook;
    }

}
