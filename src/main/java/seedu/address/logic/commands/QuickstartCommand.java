package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;

/**
 * Generates a QuickStart guide.
 */
public class QuickstartCommand extends Command {

    public static final String COMMAND_WORD = "quickstart";

    public static final String MESSAGE_QUICKSTART_ACKNOWLEDGEMENT = "Quickstart guide opened.";

    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) {
        return new CommandResult(MESSAGE_QUICKSTART_ACKNOWLEDGEMENT, false, false, true);
    }

}
