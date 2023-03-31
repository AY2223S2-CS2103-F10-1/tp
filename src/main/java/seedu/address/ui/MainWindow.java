package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    //@@author Guo-KeCheng
    private static final String DARK_THEME = "/css/DarkTheme.css";
    //@@author Guo-KeCheng
    private static final String LIGHT_THEME = "/css/LightTheme.css";
    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;

    private ResultDisplay resultDisplay;
    private final HelpWindow helpWindow;

    private final QuickstartWindow quickstartWindow;

    @FXML
    private StackPane commandBoxPlaceholder;
    @FXML
    private VBox mainVbox;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem quickMenuItem;

    @FXML
    private MenuItem baseMenuItem;
    @FXML
    private MenuItem darkMenuItem;

    @FXML
    private MenuItem lightMenuItem;
    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane taskListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private Cursor customCursor;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
        quickstartWindow = new QuickstartWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(baseMenuItem, KeyCombination.valueOf("F1"));
        setAccelerator(lightMenuItem, KeyCombination.valueOf("F2"));
        setAccelerator(darkMenuItem, KeyCombination.valueOf("F3"));
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F4"));
        setAccelerator(quickMenuItem, KeyCombination.valueOf("F5"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
        //@@author Guo-KeCheng
        EventHandler<KeyEvent> keyEventHandler = event -> {
            if (event.getCode() == KeyCode.F1) {
                handleExit();
                event.consume();
            } else if (event.getCode() == KeyCode.F2) {
                setLightTheme();
                event.consume();
            } else if (event.getCode() == KeyCode.F3) {
                setDarkTheme();
                event.consume();
            } else if (event.getCode() == KeyCode.F4) {
                handleHelp();
                event.consume();
            } else if (event.getCode() == KeyCode.F4) {
                handleQuickstart();
                event.consume();
            }
        };
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, keyEventHandler);


        // set event filter on menu to set custom cursor on hover
        primaryStage.getScene().addEventFilter(MouseEvent.MOUSE_ENTERED, e
            -> primaryStage.getScene().setCursor(customCursor));
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        // Independent Ui parts residing in this Ui container
        PersonListPanel personListPanel = new PersonListPanel(logic);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        TaskListPanel taskListPanel = new TaskListPanel(logic.getOfficeConnectModel()
            .getTaskModelManager().getFilteredItemList());
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        if (quickstartWindow.getFirstTimeFocus()) {
            quickstartWindow.show();
            quickstartWindow.focus();
        }
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Opens the quickstart window or focuses on it if it's already opened.
     */
    @FXML
    public void handleQuickstart() {
        if (!quickstartWindow.isShowing()) {
            quickstartWindow.show();
        } else {
            quickstartWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
        quickstartWindow.hide();
        assert (!(helpWindow.isShowing() && quickstartWindow.isShowing() && primaryStage.isShowing()));
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isShowQuickstart()) {
                handleQuickstart();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }

    }

    //@@author Guo-KeCheng

    /**
     * Loads the light themed css.
     */
    @FXML
    public void setLightTheme() {
        primaryStage.getScene().getStylesheets().clear();
        primaryStage.getScene().getStylesheets().add(LIGHT_THEME);
    }

    //@@author Guo-KeCheng

    /**
     * Loads the dark themed css.
     */
    @FXML
    public void setDarkTheme() {
        primaryStage.getScene().getStylesheets().clear();
        primaryStage.getScene().getStylesheets().add(DARK_THEME);
    }
}
