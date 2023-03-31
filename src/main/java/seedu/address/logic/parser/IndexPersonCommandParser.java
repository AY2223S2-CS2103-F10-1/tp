package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.IndexCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new IndexCommand object.
 */
public class IndexPersonCommandParser implements Parser<IndexCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the IndexCommand
     * and returns a IndexCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public IndexCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new IndexCommand(index, IndexCommand.COMMAND_WORD_PERSON);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, IndexCommand.MESSAGE_USAGE), pe);
        }
    }

}
