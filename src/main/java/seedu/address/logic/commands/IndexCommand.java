package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;


/**
 * Represents an IndexCommand that focuses on a person or a task identified using their displayed index
 * from OfficeConnect.
 */
public class IndexCommand extends Command {
    public static final String COMMAND_WORD_PERSON = "pi";
    public static final String COMMAND_WORD_TASK = "ti";

    public static final String MESSAGE_USAGE = "Index Command identified by the index number "
        + "used in the displayed person or task.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example 1: pi 1"
        + "Example 2: ti 1";

    private final Index index;
    private final String type;

    /**
     * Creates an IndexCommand with the specified index and type.
     *
     * @param index The index of the item to be focused on.
     * @param type  The type of item to be focused on, either a person or a task.
     */
    public IndexCommand(Index index, String type) {
        super();
        this.index = index;
        this.type = type;
    }


    /**
     * Executes the IndexCommand, focusing on the specified person or task in the model and
     * OfficeConnectModel.
     *
     * @param model              The model object containing the application's state.
     * @param officeConnectModel The OfficeConnectModel object containing the office connection state.
     * @return A CommandResult object containing the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) throws CommandException {
        requireAllNonNull(model, officeConnectModel);
        if (type.equals(COMMAND_WORD_TASK)) {
            List<Task> lastShownList = officeConnectModel.getTaskModelManagerFilteredItemList();

            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }
            Task taskToFocus = lastShownList.get(index.getZeroBased());
            officeConnectModel.focusTask(taskToFocus, model);

            return new CommandResult("Focus on task : " + taskToFocus.getTitle());


        } else {
            List<Person> lastShownList = model.getFilteredPersonList();

            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToFocus = lastShownList.get(index.getZeroBased());


            model.focusPerson(personToFocus, officeConnectModel);
            return new CommandResult("Focus on person : " + personToFocus.getName());
        }
    }


}
