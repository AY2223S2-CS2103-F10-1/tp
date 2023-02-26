package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.shared.Id;
import seedu.address.model.task.Content;
import seedu.address.model.task.Status;
import seedu.address.model.task.Subject;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    @JsonProperty("id")

    private final String id;
    @JsonProperty("subject")
    private final String subject;
    @JsonProperty("content")

    private final String content;
    @JsonProperty("status")

    private final String status;


    /**
     * Constructs a {@code JsonAdaptedTask} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(String id, String subject, String content, String status) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.status = status;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        subject = source.getSubject().getValue();
        content = source.getContent().getValue();
        status = String.valueOf(source.getStatus().isValue());
        id = source.getId().getValue().toString();

    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        if (Id.isInValidId(id)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }
        if (subject == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Subject.isValidSubject(subject)) {
            throw new IllegalValueException(Subject.MESSAGE_CONSTRAINTS);
        }

        if (content == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Content.isValidContent(content)) {
            throw new IllegalValueException(Content.MESSAGE_CONSTRAINTS);
        }

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }

        final Id modeId = new Id(id);
        final Subject modelSubject = new Subject(subject);
        final Content modelContent = new Content(content);
        final Status modelStatus = new Status(Boolean.parseBoolean(status));


        return new Task(modelSubject, modelContent, modelStatus, modeId);
    }

}
