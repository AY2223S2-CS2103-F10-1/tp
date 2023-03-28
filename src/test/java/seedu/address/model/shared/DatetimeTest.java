package seedu.address.model.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class DatetimeTest {
    private static final String VALID_DATETIME_1 = "2023-03-27T15:30:00";
    private static final String VALID_DATE = "2023-03-27";
    private static final String INVALID_DATETIME = "2023/03/27 15:30:00";
    private static final String INVALID_DATE = "2023.03.27";
    private static final String VALID_TIMESTAMP = "1731033000000";
    private static final String INVALID_TIMESTAMP = "abc";

    @Test
    void constructor_validInput_successfulCreation() {
        Datetime datetime = new Datetime(VALID_DATETIME_1);
        Optional<Long> timestamp = datetime.getTimestamp();
        assertTrue(timestamp.isPresent());
        LocalDateTime expectedDateTime = LocalDateTime.parse(VALID_DATETIME_1);
        assertEquals(expectedDateTime, LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.get()),
            ZoneId.systemDefault()));
    }

    @Test
    void constructor_nullInput_successfulCreation() {
        Datetime datetime = new Datetime(null);
        Optional<Long> timestamp = datetime.getTimestamp();
        assertFalse(timestamp.isPresent());
    }

    @Test
    void constructor_invalidInput_successfulCreation() {
        Datetime datetime = new Datetime(INVALID_DATETIME);
        Optional<Long> timestamp = datetime.getTimestamp();
        assertFalse(timestamp.isPresent());
    }

    @Test
    void isValidTimestamp_validTimestamp_returnsTrue() {
        assertTrue(Datetime.isValidTimestamp(VALID_TIMESTAMP));
    }

    @Test
    void isValidTimestamp_invalidTimestamp_returnsFalse() {
        assertFalse(Datetime.isValidTimestamp(INVALID_TIMESTAMP));
    }

    @Test
    void validateInput_validDatetime_returnsLocalDateTime() {
        LocalDateTime expectedDateTime = LocalDateTime.parse(VALID_DATETIME_1);
        assertEquals(expectedDateTime, Datetime.validateInput(VALID_DATETIME_1));
    }

    @Test
    void validateInput_validDate_returnsLocalDateTime() {
        LocalDateTime expectedDateTime = LocalDateTime.parse(VALID_DATE + "T00:00:00");
        assertEquals(expectedDateTime, Datetime.validateInput(VALID_DATE));
    }

    @Test
    void validateInput_invalidDatetime_returnsNull() {
        assertNull(Datetime.validateInput(INVALID_DATETIME));
    }

    @Test
    void validateInput_invalidDate_returnsNull() {
        assertNull(Datetime.validateInput(INVALID_DATE));
    }

    @Test
    void isPastDateTime_currentDateTime_returnsFalse() {
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.systemDefault()).plusMonths(1);
        assertFalse(Datetime.isPastDateTime(currentDateTime, ZoneId.systemDefault()));
    }

    @Test
    void isPastDateTime_pastDateTime_returnsTrue() {
        LocalDateTime pastDateTime = LocalDateTime.parse("2022-02-27T15:30:00");
        assertTrue(Datetime.isPastDateTime(pastDateTime, ZoneId.systemDefault()));
    }

    @Test
    void isPastDateTime_futureDateTime_returnsFalse() {
        LocalDateTime futureDateTime = LocalDateTime.parse("2124-03-27T15:30:00");
        assertFalse(Datetime.isPastDateTime(futureDateTime, ZoneId.systemDefault()));
    }

}
