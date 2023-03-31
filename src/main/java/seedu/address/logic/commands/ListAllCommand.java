package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.task.Task;

/**
 * Lists all persons and tasks in the address book.
 */
public class ListAllCommand extends Command {
    public static final String COMMAND_WORD = "listall";

    public static final String MESSAGE_SUCCESS = "Listed all persons and tasks";

    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) {
        requireAllNonNull(model, officeConnectModel);

        listAllPersons(model);

        listAllTasks(officeConnectModel);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    private static void listAllTasks(OfficeConnectModel officeConnectModel) {
        RepositoryModelManager<Task> taskModelManager = officeConnectModel.getTaskModelManager();
        taskModelManager.updateFilteredItemList(OfficeConnectModel.PREDICATE_SHOW_ALL_TASKS);
    }

    private static void listAllPersons(Model model) {
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }
}
