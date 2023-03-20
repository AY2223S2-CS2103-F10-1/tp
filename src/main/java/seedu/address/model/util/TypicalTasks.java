package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Repository;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Tasks} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task SEND_EMAIL_TO_CLIENT = new TaskBuilder().withTitle("Send email to client")
            .withContent("Email Mr. Chan about the recent updates on Project X.").build();
    public static final Task COMPLETE_SLIDES = new TaskBuilder().withTitle("Complete slides").withStatus(true)
            .withContent("Finish up slides for meeting on 1st March.").build();
    public static final Task STOCK_PANTRY = new TaskBuilder().withTitle("Stock pantry")
            .withContent("Purchase food for the pantry on level 6.").build();
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
