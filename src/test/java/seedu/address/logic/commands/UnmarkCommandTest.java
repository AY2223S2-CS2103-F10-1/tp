package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertTaskCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertTaskCommandSuccess;
import static seedu.address.model.util.TypicalPersons.getTypicalAddressBook;
import static seedu.address.model.util.TypicalTasks.getTypicalTaskRepository;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_SEVENTH;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.Repository;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.task.Task;
import seedu.address.model.util.TaskBuilder;

public class UnmarkCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final OfficeConnectModel officeConnectModel = new OfficeConnectModel(
            new RepositoryModelManager<>(getTypicalTaskRepository()),
            new RepositoryModelManager<>(new Repository<AssignTask>()));
    private final OfficeConnectModel expectedOfficeConnectModel = new OfficeConnectModel(new
            RepositoryModelManager<>(officeConnectModel.getTaskModelManager().getReadOnlyRepository()),
            new RepositoryModelManager<>(new Repository<AssignTask>()));

    @Test
    public void execute_validIndexList_success() {
        Task task = officeConnectModel.getTaskModelManager().getFilteredItemList().get(INDEX_SEVENTH.getZeroBased());
        UnmarkCommand command = new UnmarkCommand(INDEX_SEVENTH);

        Task unmarkedTask = new TaskBuilder(task).withStatus(false).build();
        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask);

        expectedOfficeConnectModel.setTaskModelManagerItem(task, unmarkedTask, model);


        assertTaskCommandSuccess(command, officeConnectModel, expectedMessage, expectedOfficeConnectModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBounds = Index.fromOneBased(officeConnectModel.getTaskModelManager()
                .getFilteredItemList().size() + 1);
        UnmarkCommand command = new UnmarkCommand(outOfBounds);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, () ->
                command.execute(model, officeConnectModel));
        assertTaskCommandFailure(command, officeConnectModel, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyUnmarkedTask_throwsCommandException() {
        UnmarkCommand command = new UnmarkCommand(INDEX_FIRST);
        assertThrows(CommandException.class, Messages.MESSAGE_TASK_ALREADY_NOT_DONE, () ->
                command.execute(model, officeConnectModel));
        assertTaskCommandFailure(command, officeConnectModel, Messages.MESSAGE_TASK_ALREADY_NOT_DONE);
    }

    @Test
    public void equals() {
        UnmarkCommand unmarkFirstCommand = new UnmarkCommand(INDEX_FIRST);
        UnmarkCommand unmarkSecondCommand = new UnmarkCommand(INDEX_SECOND);

        // same object -> returns true
        assertEquals(unmarkFirstCommand, unmarkFirstCommand);

        // same values -> returns true
        UnmarkCommand unmarkFirstCommandCopy = new UnmarkCommand(INDEX_FIRST);
        assertEquals(unmarkFirstCommand, unmarkFirstCommandCopy);

        // null -> returns false
        assertNotEquals(null, unmarkFirstCommand);

        // different task -> returns false
        assertNotEquals(unmarkFirstCommand, unmarkSecondCommand);
    }
}
