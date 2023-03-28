package seedu.address.storage;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.shared.Datetime;
import seedu.address.model.shared.Id;
import seedu.address.model.task.Content;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    @JsonProperty("id")

    private final String id;
    @JsonProperty("title")
    private final String title;
    @JsonProperty("content")

    private final String content;
    @JsonProperty("status")

    private final String status;

    @JsonProperty("createDatetime")
    private final String createDatetime;

    @JsonProperty("deadline")
    private final String deadline;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("id") String id, @JsonProperty("title") String title,
                           @JsonProperty("status") String status, @JsonProperty("content") String content,
                           @JsonProperty("createDatetime") String createDatetime,
                           @JsonProperty("deadline") String deadline) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.createDatetime = createDatetime;
        this.deadline = deadline;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        title = source.getTitle().getValue();
        content = source.getContent().getValue();
        status = String.valueOf(source.getStatus().isValue());
        id = source.getId().getValue().toString();
        deadline = source.getDeadline().getTimestamp().map(Object::toString).orElse(null);
        createDatetime = source.getCreateDateTime().getTimestamp().map(Object::toString).orElse(null);

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
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
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

        if (deadline != null) {
            if (!Datetime.isValidTimestamp(deadline)) {
                throw new IllegalValueException(Datetime.MESSAGE_CONSTRAINTS_TIMESTAMP);
            }
        }

        if (createDatetime != null) {
            if (!Datetime.isValidTimestamp(createDatetime)) {
                throw new IllegalValueException(Datetime.MESSAGE_CONSTRAINTS_TIMESTAMP);
            }
        }


        final Id modeId = new Id(id);
        final Title modelTitle = new Title(title);
        final Content modelContent = new Content(content);
        final Status modelStatus = new Status(Boolean.parseBoolean(status));
        final Datetime modelDeadline = new Datetime(deadline == null ? null
            : LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(deadline)),
            ZoneId.systemDefault()).toString());
        final Datetime modelCreateDatetime = new Datetime(createDatetime == null ? null
            : LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(createDatetime)),
            ZoneId.systemDefault()).toString());

        return new Task(modelTitle, modelContent, modelStatus, modelCreateDatetime, modelDeadline, modeId);
    }

}
