package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Assigns a person identified to a task identified using their displayed index from the lists.
 */
public class AssignCommand extends Command {
    public static final String COMMAND_WORD = "assign";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns a person to a task."
            + "Parameters "
            + PREFIX_PERSON_INDEX + "PERSON INDEX "
            + PREFIX_TASK_INDEX + "TASK INDEX";
    public static final String MESSAGE_SUCCESS = "Assigned: %1$s to %2$s";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This person is already assigned to this task.";

    private final Index personIndex;
    private final Index taskIndex;

    /**
     * Creates AssignCommand object with given personIndex and taskIndex.
     */
    public AssignCommand(Index personIndex, Index taskIndex) {
        requireAllNonNull(personIndex, taskIndex);

        this.personIndex = personIndex;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes AssignCommand with given personIndex and taskIndex.
     */
    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) throws CommandException {
        requireAllNonNull(model, officeConnectModel);

        List<Person> personList = model.getFilteredPersonList();
        if (personIndex.getZeroBased() >= personList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        List<Task> taskList = officeConnectModel.getTaskModelManager().getFilteredItemList();
        if (taskIndex.getZeroBased() >= taskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Person person = personList.get(personIndex.getZeroBased());
        Task task = taskList.get(taskIndex.getZeroBased());

        AssignTask toAdd = new AssignTask(person, task);

        if (officeConnectModel.hasAssignTaskModelManagerItem(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        officeConnectModel.addAssignTaskModelManagerItem(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, person, task));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignCommand // instanceof handles nulls
                && personIndex.equals(((AssignCommand) other).personIndex)
                && taskIndex.equals(((AssignCommand) other).taskIndex));
    }
}
