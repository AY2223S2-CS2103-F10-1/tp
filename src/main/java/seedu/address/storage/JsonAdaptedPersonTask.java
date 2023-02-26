package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.mapping.PersonTask;
import seedu.address.model.shared.Id;

/**
 * Jackson-friendly version of {@link PersonTask}.
 */
public class JsonAdaptedPersonTask {
    @JsonProperty("person_id")

    private final String personId;
    @JsonProperty("task_id")

    private final String taskId;

    /**
     * Constructs a {@code JsonAdaptedPersonTask} with the given {@code personTask}.
     */
    @JsonCreator
    public JsonAdaptedPersonTask(String personId, String taskId) {

        this.personId = personId;
        this.taskId = taskId;
    }

    /**
     * Converts a given {@code PersonTask} into this class for Jackson use.
     */
    public JsonAdaptedPersonTask(PersonTask source) {
        personId = source.getPersonId().getValue().toString();
        taskId = source.getTaskId().getValue().toString();
    }


    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code PersonTask} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted personTask.
     */
    public PersonTask toModelType() throws IllegalValueException {
        if (Id.isInValidId(personId)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }

        if (Id.isInValidId(taskId)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }

        Id modePersonId = new Id(personId);
        Id modeTaskId = new Id(taskId);

        return new PersonTask(modePersonId, modeTaskId);
    }
}
