package seedu.address.model.shared;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * The Datetime class represents a date and time value.
 * The class accepts date and time input as a string in various formats, validates the input,
 * and stores the resulting timestamp if the input is valid and not in the past.
 * The class provides a getter for the stored timestamp as an Optional.
 */
public class Datetime {

    public static final String MESSAGE_CONSTRAINTS_TIMESTAMP = "Invalid Timestamp!";
    public static final String MESSAGE_CONSTRAINTS_DATETIME = "Invalid datetime format!\n"
        + "e.g: 2023-03-27 15:30:00\nNote the leading zeros!\nTime is optional."
        + "\nWe are too lazy to support more format...";
    private final Long timestamp;

    /**
     * Constructs a Datetime object with a null timestamp.
     * This constructor can be used when no input string is provided or when creating an empty Datetime object.
     */
    public Datetime() {
        timestamp = null;
    }


    /**
     * Constructs a Datetime object with the given input string.
     * The input string is validated, and if valid and not in the past,
     * the corresponding timestamp is stored.
     *
     * @param input the date and time input string
     */
    public Datetime(String input) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime dateTime = validateInput(input);
        if (dateTime != null) {
            this.timestamp = dateTime.toInstant(zoneId.getRules().getOffset(dateTime)).toEpochMilli();
        } else {
            this.timestamp = null;
        }
    }

    /**
     * Validates the input string and returns a LocalDateTime object if the input is valid.
     * If the input is not valid or is in the past, returns null.
     *
     * @param input the date and time input string
     * @return a LocalDateTime object if the input is valid and not in the past, otherwise null
     */
    public static LocalDateTime validateInput(String input) {
        if (input == null) {
            return null;
        }

        DateTimeFormatter dateOnlyFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter humanDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            return LocalDateTime.parse(input, dateTimeFormat);
        } catch (DateTimeParseException e1) {
            try {
                LocalDate date = LocalDate.parse(input, dateOnlyFormat);
                return date.atStartOfDay();
            } catch (DateTimeParseException e2) {
                try {
                    return LocalDateTime.parse(input, humanDateTimeFormat);
                } catch (DateTimeParseException e3) {
                    return null;
                }
            }
        }
    }

    /**
     * Checks if the given string input is a valid timestamp.
     *
     * @param input the timestamp string to check
     * @return true if the input string represents a valid timestamp, otherwise false
     */
    public static boolean isValidTimestamp(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        try {
            long timestamp = Long.parseLong(input);
            LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns the stored timestamp as an Optional
     *
     * @return an Optional containing the stored timestamp if it exists, otherwise an empty Optional
     */
    public Optional<Long> getTimestamp() {
        return Optional.ofNullable(timestamp);
    }

    /**
     * Checks if the given LocalDateTime object represents a past date and time.
     *
     * @param dateTime the LocalDateTime object to check
     * @param zoneId   the time zone to use for comparison
     * @return true if the LocalDateTime object is before the current date and time
     *     in the given time zone, otherwise false
     */
    public static boolean isPastDateTime(LocalDateTime dateTime, ZoneId zoneId) {
        LocalDateTime currentDate = LocalDateTime.now(zoneId);
        return dateTime.isBefore(currentDate);
    }
}
