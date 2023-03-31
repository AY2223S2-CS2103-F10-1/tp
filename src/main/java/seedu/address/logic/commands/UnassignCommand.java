package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;


/**
 * Unassigned a person identified to a task identified using their displayed indices from lists.
 */
public class UnassignCommand extends Command {
    public static final String COMMAND_WORD = "unassign";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unassigns a person from a task."
        + "Parameters "
        + PREFIX_PERSON_INDEX + "PERSON INDEX "
        + PREFIX_TASK_INDEX + "TASK INDEX";
    public static final String MESSAGE_SUCCESS = "Unassigned: %1$s to %2$s";
    public static final String MESSAGE_NON_EXIST_ASSIGNMENT = "This person has not been assigned to this task.";

    private final Index personIndex;
    private final Index taskIndex;

    /**
     * Creates UnassignCommand object with given personIndex and taskIndex.
     */
    public UnassignCommand(Index personIndex, Index taskIndex) {
        requireAllNonNull(personIndex, taskIndex);

        this.personIndex = personIndex;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes UnassignCommand with given personIndex and taskIndex.
     */
    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) throws CommandException {
        requireAllNonNull(model, officeConnectModel);

        if (!model.isValidFilterPersonListIndexRange(personIndex.getZeroBased())) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (!officeConnectModel.isValidFilterTaskListIndexRange(taskIndex.getZeroBased())) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Person person = model.getFilterPerson(personIndex.getZeroBased());
        Task task = officeConnectModel.deleteAssignment(person, taskIndex.getZeroBased());
        model.updateFilteredPersonList(p -> p.getId().equals(person.getId()));
        return new CommandResult(String.format(MESSAGE_SUCCESS,
            person.getName(), task.getTitle()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UnassignCommand // instanceof handles nulls
            && personIndex.equals(((UnassignCommand) other).personIndex)
            && taskIndex.equals(((UnassignCommand) other).taskIndex));
    }
}
