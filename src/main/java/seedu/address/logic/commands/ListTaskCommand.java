package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.OfficeConnectModel.PREDICATE_SHOW_ALL_TASKS;

import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;

/**
 * Lists all tasks in OfficeConnect to the user.
 */
public class ListTaskCommand extends Command {
    public static final String COMMAND_WORD = "listt";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) {
        requireAllNonNull(model, officeConnectModel);
        officeConnectModel.updateTaskModelManagerFilteredItemList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
