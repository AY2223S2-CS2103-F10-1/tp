package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.IndexCommand;
import seedu.address.logic.commands.ListAllCommand;
import seedu.address.logic.commands.ListAssignment;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListTaskCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.QuickstartCommand;
import seedu.address.logic.commands.UnassignCommand;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord.toLowerCase()) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);

        case AssignCommand.COMMAND_WORD:
            return new AssignCommandParser().parse(arguments);

        case UnassignCommand.COMMAND_WORD:
            return new UnassignCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindTaskCommand.COMMAND_WORD:
            return new FindTaskCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);

        case ListAllCommand.COMMAND_WORD:
            return new ListAllCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListTaskCommand.COMMAND_WORD:
            return new ListTaskCommand();

        case MarkCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case QuickstartCommand.COMMAND_WORD:
            return new QuickstartCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ListAssignment.COMMAND_WORD_ASSIGNED_TASK:
            return new ListAssignment(ListAssignment.TYPE_TASK, true);

        case ListAssignment.COMMAND_WORD_ASSIGNED_PERSON:
            return new ListAssignment(ListAssignment.TYPE_PERSON, true);

        case ListAssignment.COMMAND_WORD_UNASSIGNED_PERSON:
            return new ListAssignment(ListAssignment.TYPE_PERSON, false);

        case ListAssignment.COMMAND_WORD_UNASSIGNED_TASK:
            return new ListAssignment(ListAssignment.TYPE_TASK, false);

        case ListAssignment.COMMAND_WORD_ASSIGNED_ALL:
            return new ListAssignment("", true);

        case ListAssignment.COMMAND_WORD_UNASSIGNED_ALL:
            return new ListAssignment("", false);

        case IndexCommand.COMMAND_WORD_PERSON:
            return new IndexPersonCommandParser().parse(arguments);

        case IndexCommand.COMMAND_WORD_TASK:
            return new IndexTaskCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
