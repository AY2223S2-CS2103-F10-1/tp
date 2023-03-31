package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.ModelManager.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;

/**
 * Lists all persons in OfficeConnect to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "listp";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) {
        requireAllNonNull(model, officeConnectModel);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
