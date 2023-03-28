package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.shared.Datetime;
import seedu.address.model.shared.Id;
import seedu.address.model.task.Content;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

public class JsonAdaptedTaskTest {

    private static final String VALID_ID = UUID.randomUUID().toString();
    private static final String VALID_TITLE = "Math homework";
    private static final String VALID_CONTENT = "Do exercises 1 to 10 in Chapter 3";
    private static final String VALID_STATUS = "true";
    private static final String INVALID_ID = "a";
    private static final String INVALID_TITLE = "Math homework #1";
    private static final String INVALID_CONTENT = "";
    private static final String INVALID_STATUS = "yes";
    private static final String VALID_TIMESTAMP_1 = "1731033000000";
    private static final String VALID_TIMESTAMP_2 = "1731033000001";
    private static final String INVALID_TIMESTAMP_1 = "abc";
    private static final String INVALID_TIMESTAMP_2 = "";

    @Test
    void toModelType_validTaskDetails_returnsTask() throws Exception {
        Task task = new Task(new Title(VALID_TITLE), new Content(VALID_CONTENT), new Status(true),
            new Datetime("2023-03-27T15:30:00"), new Datetime("2023-03-28T15:30:00"), new Id(VALID_ID));
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(task);
        assertEquals(task, jsonAdaptedTask.toModelType());
    }

    @Test
    void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(null, VALID_TITLE, VALID_STATUS,
            VALID_CONTENT, "2023-03-27T15:30:00", "2023-03-28T15:30:00");
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, jsonAdaptedTask::toModelType);
    }

    @Test
    void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(INVALID_ID, VALID_TITLE,
            VALID_STATUS, VALID_CONTENT, "2023-03-27T15:30:00", "2023-03-28T15:30:00");
        assertThrows(IllegalValueException.class, Id.MESSAGE_CONSTRAINTS, jsonAdaptedTask::toModelType);
    }

    @Test
    void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(VALID_ID, INVALID_TITLE,
            VALID_STATUS, VALID_CONTENT, "2023-03-27T15:30:00", "2023-03-28T15:30:00");
        assertThrows(IllegalValueException.class, Title.MESSAGE_CONSTRAINTS, jsonAdaptedTask::toModelType);
    }

    @Test
    void toModelType_invalidContent_throwsIllegalValueException() {
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(VALID_ID, VALID_TITLE,
            VALID_STATUS, INVALID_CONTENT, "2023-03-27T15:30:00", "2023-03-28T15:30:00");
        assertThrows(IllegalValueException.class, Content.MESSAGE_CONSTRAINTS, jsonAdaptedTask::toModelType);
    }

    @Test
    void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(VALID_ID, VALID_TITLE,
            INVALID_STATUS, VALID_CONTENT, "2023-03-27T15:30:00", "2023-03-28T15:30:00");
        assertThrows(IllegalValueException.class, Status.MESSAGE_CONSTRAINTS, jsonAdaptedTask::toModelType);
    }

    @Test
    void isValidTimestamp_validTimestamp_returnsTrue() {
        assertTrue(Datetime.isValidTimestamp(VALID_TIMESTAMP_1));
    }

    @Test
    void isValidTimestamp_validTimestampWithDifferentValue_returnsTrue() {
        assertTrue(Datetime.isValidTimestamp(VALID_TIMESTAMP_2));
    }

    @Test
    void isValidTimestamp_invalidTimestamp_returnsFalse() {
        assertFalse(Datetime.isValidTimestamp(INVALID_TIMESTAMP_1));
    }

    @Test
    void isValidTimestamp_emptyInput_returnsFalse() {
        assertFalse(Datetime.isValidTimestamp(INVALID_TIMESTAMP_2));
    }
}
