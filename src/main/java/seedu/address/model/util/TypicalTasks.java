package seedu.address.model.util;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Repository;
import seedu.address.model.task.Task;


/**
 * A utility class containing a list of {@code Tasks} objects to be used in tests.
 */
public class TypicalTasks {

    public static final String VALID_TITLE_SEND_EMAIL = "Send email to superior";
    public static final String VALID_TITLE_SUBMIT_REPORT = "Submit report for approval";

    public static final String VALID_CONTENT_SEND_EMAIL = "Email Mr. Chan about the recent updates on Project X.";
    public static final String VALID_CONTENT_SUBMIT_REPORT = "Get Mr. Tan to look through the reports and send them";
    public static final String VALID_STATUS_UNDONE = "false";
    public static final String VALID_STATUS_DONE = "true";
    public static final String VALID_ID = "d9cc62ca-0f19-11ec-82a8-0242ac131234";
    public static final String INVALID_TITLE = " " + PREFIX_TITLE + "Invalid title&";
    public static final String INVALID_CONTENT = " " + PREFIX_CONTENT + " ";
    public static final String INVALID_STATUS = " " + PREFIX_STATUS + " ";
    public static final String VALID_DESC_TITLE_EMAIL = " " + PREFIX_TITLE + VALID_TITLE_SEND_EMAIL;
    public static final String VALID_DESC_TITLE_REPORT = " " + PREFIX_TITLE + VALID_TITLE_SUBMIT_REPORT;
    public static final String VALID_DESC_CONTENT_EMAIL = " " + PREFIX_CONTENT + VALID_CONTENT_SEND_EMAIL;
    public static final String VALID_DESC_CONTENT_REPORT = " " + PREFIX_CONTENT + VALID_CONTENT_SUBMIT_REPORT;
    public static final String VALID_DESC_STATUS_DONE = " " + PREFIX_STATUS + VALID_STATUS_DONE;
    public static final String VALID_DESC_STATUS_UNDONE = " " + PREFIX_STATUS + VALID_STATUS_UNDONE;
    public static final String INVALID_DESC_TITLE_EMAIL = " " + PREFIX_TITLE + INVALID_TITLE;
    public static final String INVALID_DESC_CONTENT_EMAIL = " " + PREFIX_CONTENT + INVALID_CONTENT;


    public static final Task SEND_EMAIL_TO_CLIENT = new TaskBuilder().withTitle("Send email to client")
        .withContent("Email Mr. Chan about the recent updates on Project X.").withDeadline("2023-03-27 18:00:00")
        .withDCreateTime("2023-02-27 17:00:00").build();
    public static final Task COMPLETE_SLIDES = new TaskBuilder().withTitle("Complete slides")
        .withDeadline("2023-04-27 18:00:00").withDCreateTime("2023-02-27 17:00:00").withStatus(true)
        .withContent("Finish up slides for meeting on 1st March.").build();
    public static final Task STOCK_PANTRY = new TaskBuilder().withTitle("Stock pantry")
        .withContent("Purchase food for the pantry on level 6.").withDeadline("2023-03-29 18:00:00")
        .withDCreateTime("2023-02-27 17:00:00").build();
    public static final Task CHECK_BALANCES = new TaskBuilder().withTitle("Check balances").withStatus(true)
        .withContent("Check all bank statements.").build();
    public static final Task DEPOSIT_CASH = new TaskBuilder().withTitle("Deposit cash")
        .withContent("Deposit cash from last week at the bank.").build();
    public static final Task SET_APPOINTMENT = new TaskBuilder().withTitle("Set appointment")
        .withContent("Set up meeting with Mdm Tay for next Monday").withStatus(true).build();
    public static final Task DATA_ENTRY = new TaskBuilder().withTitle("Data entry")
            .withContent("Update spreadsheet with client information.").withStatus(true).build();

    private TypicalTasks() {}

    /**
     * Returns a {@code Repository} with all the typical tasks.
     */
    public static Repository<Task> getTypicalTaskRepository() {
        Repository<Task> tl = new Repository<>();
        for (Task task : getTypicalTasks()) {
            tl.addItem(task);
        }
        return tl;
    }

    public static Repository<Task> getTypicalTaskRepository(List<Task> typicalTasks) {
        Repository<Task> tl = new Repository<>();
        for (Task task : typicalTasks) {
            tl.addItem(task);
        }
        return tl;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(SEND_EMAIL_TO_CLIENT, COMPLETE_SLIDES, STOCK_PANTRY,
                CHECK_BALANCES, DEPOSIT_CASH, SET_APPOINTMENT, DATA_ENTRY));
    }

}
