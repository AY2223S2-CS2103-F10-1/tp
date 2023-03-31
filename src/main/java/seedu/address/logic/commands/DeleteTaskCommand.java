package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.shared.Id;
import seedu.address.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from the repository.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "deletet";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";

    private final Index targetIndex;

    /**
     * Creates a DeleteTaskCommand object to delete the task at specified {@code Index}.
     */
    public DeleteTaskCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) throws CommandException {
        requireAllNonNull(model, officeConnectModel);
        List<Task> lastShownList = officeConnectModel.getTaskModelManagerFilteredItemList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        Task taskToDelete = lastShownList.get(targetIndex.getZeroBased());
        Id tId = taskToDelete.getId();
        List<Id> personIdList = getPersonIdList(officeConnectModel, tId);

        personIdList.forEach(pId -> officeConnectModel.deleteAssignTaskModelManagerItem(new AssignTask(pId, tId)));

        officeConnectModel.deleteTaskModelManagerItem(taskToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
    }

    private static List<Id> getPersonIdList(OfficeConnectModel officeConnectModel, Id tId) {
        RepositoryModelManager<AssignTask> assignTaskModelManager = officeConnectModel.getAssignTaskModelManager();
        List<Id> idList = assignTaskModelManager.filter(assignment -> assignment.getTaskId().equals(tId))
                .stream().map(assignTask -> assignTask.getPersonId()).collect(Collectors.toList());
        return idList;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTaskCommand) other).targetIndex)); // state check
    }
}
