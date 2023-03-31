package seedu.address.ui;

import static seedu.address.logic.parser.AddressBookParser.BASIC_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.commands.ListAllCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        commandTextField.textProperty().addListener((observable, oldValue, newValue)
            -> onInputChange(oldValue, newValue));
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    private void onInputChange(String oldValue, String newValue) {
        // Your logic to handle input changes here
        String trimmedNewValue = newValue.trim();
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(trimmedNewValue);

        if (matcher.matches()) {
            String commandWord = matcher.group("commandWord");
            Set<String> allowedCommands = new HashSet<>(Arrays.asList(FindCommand.COMMAND_WORD,
                FindTaskCommand.COMMAND_WORD));

            if (allowedCommands.contains(commandWord) && !trimmedNewValue.equals(FindCommand.COMMAND_WORD)
                    && !trimmedNewValue.equals(FindTaskCommand.COMMAND_WORD)) {
                executeCommand(newValue);
            } else if (allowedCommands.contains(commandWord) && trimmedNewValue.equals(FindCommand.COMMAND_WORD)
                    || trimmedNewValue.equals(FindTaskCommand.COMMAND_WORD) && oldValue.length() > newValue.length()) {
                executeCommand(ListAllCommand.COMMAND_WORD);
            } else {
                // Do nothing
            }
        }
    }

    private void executeCommand(String commandToExecute) {
        try {
            commandExecutor.execute(commandToExecute);
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }


    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
