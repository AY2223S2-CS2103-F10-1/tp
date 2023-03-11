package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.task.Content;
import seedu.address.model.task.Status;
import seedu.address.model.task.Subject;
import seedu.address.model.task.Task;

/**
 * Unmarks a marked task as uncompleted
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the task identified by the index number as uncompleted.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "Unmarked Task: %1$s";
    private final Index taskIndex;

    public UnmarkCommand(Index taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) throws CommandException {
        requireAllNonNull(model, officeConnectModel);

        RepositoryModelManager<Task> taskManager = officeConnectModel.getTaskModelManager();
        List<Task> taskList = taskManager.getFilteredItemList();

        if (taskIndex.getZeroBased() >= taskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToUnmark = taskList.get(taskIndex.getZeroBased());
        if (taskToUnmark.getStatus().equals(new Status(false))) {
            throw new CommandException(Messages.MESSAGE_TASK_ALREADY_NOT_DONE);
        }

        Task unmarkedTask = createUnmarkedTask(taskToUnmark);
        taskManager.setItem(taskToUnmark, unmarkedTask);

        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask));
    }

    private static Task createUnmarkedTask(Task taskToUnmark) {
        assert taskToUnmark != null;

        Subject subject = taskToUnmark.getSubject();
        Content content = taskToUnmark.getContent();
        Status doneStatus = new Status(false);
        return new Task(subject, content, doneStatus);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkCommand // instanceof handles nulls
                && taskIndex.equals(((UnmarkCommand) other).taskIndex)); // state check
    }
}
