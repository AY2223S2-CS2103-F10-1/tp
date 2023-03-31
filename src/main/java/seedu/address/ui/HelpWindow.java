package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page.
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "\nhttps://ay2223s2-cs2103-f10-1.github.io/tp/UserGuide.html";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";


    @FXML
    private TreeView<String> treeView;

    @FXML
    private TextArea methodDescription;

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        methodDescription.setWrapText(true);
        treeView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(new ChangeListener<TreeItem<String>>() {
                    @Override
                    public void changed(ObservableValue<? extends TreeItem<String>> observable,
                                        TreeItem<String> oldValue, TreeItem<String> newValue) {
                        TreeItem<String> selectedTreeItem = (TreeItem<String>) newValue;
                        methodDescription.setText(instructionMap(selectedTreeItem.getValue()));
                    }
                });
        helpMessage.setText(HelpStrings.HELP_MESSAGE);
    }

    /**
     * Generates and displays a String of information based on which TreeItem was clicked in the help interface.
     *
     * @param treeItemValue from the TreeItem's getValue(), we will process the String and return help text.
     */
    public static String instructionMap(String treeItemValue) {
        switch (treeItemValue) {
        case "Add commands":
            return HelpStrings.ADDMETHODS_HELP;
        case "addp":
            return HelpStrings.ADDPERSON_HELP;
        case "addt":
            return HelpStrings.ADDTASK_HELP;
        case "Delete commands":
            return HelpStrings.DELETEMETHODS_HELP;
        case "deletep":
            return HelpStrings.DELETEPERSON_HELP;
        case "deletet":
            return HelpStrings.DELETETASK_HELP;
        case "Edit commands":
            return HelpStrings.EDITMETHODS_HELP;
        case "editp":
            return HelpStrings.EDITPERSON_HELP;
        case "editt":
            return HelpStrings.EDITTASK_HELP;
        case "List commands":
            return HelpStrings.LISTMETHODS_HELP;
        case "listp":
            return HelpStrings.LISTPERSON_HELP;
        case "listt":
            return HelpStrings.LISTTASK_HELP;
        case "listall":
            return HelpStrings.LISTALL_HELP;
        case "Find commands":
            return HelpStrings.FINDMETHODS_HELP;
        case "findp":
            return HelpStrings.FINDPERSON_HELP;
        case "findt":
            return HelpStrings.FINDTASK_HELP;
        case "Marking commands":
            return HelpStrings.MARKMETHODS_HELP;
        case "mark":
            return HelpStrings.MARK_HELP;
        case "unmark":
            return HelpStrings.UNMARK_HELP;
        case "Assign commands":
            return HelpStrings.ASSIGNMETHODS_HELP;
        case "assign":
            return HelpStrings.ASSIGN_HELP;
        case "unassign":
            return HelpStrings.UNASSIGN_HELP;
        case "Filter commands":
            return HelpStrings.FILTERMETHODS_HELP;
        case "filterp":
            return HelpStrings.FILTERPERSON_HELP;
        case "View commands":
            return HelpStrings.VIEWMETHODS_HELP;
        case "pi":
            return HelpStrings.VIEWPERSON_HELP;
        case "ti":
            return HelpStrings.VIEWTASK_HELP;
        case "viewassignedall":
            return HelpStrings.VIEWASSIGNEDALL_HELP;
        case "viewassignedp":
            return HelpStrings.VIEWASSIGNEDPERSON_HELP;
        case "viewassignedt":
            return HelpStrings.VIEWASSIGNEDTASK_HELP;
        case "viewunassignedall":
            return HelpStrings.VIEWUNASSIGNEDALL_HELP;
        case "viewunassignedp":
            return HelpStrings.VIEWUNASSIGNEDPERSON_HELP;
        case "viewunassignedt":
            return HelpStrings.VIEWUNASSIGNEDTASK_HELP;
        case "Guide commands":
            return HelpStrings.GUIDEMETHODS_HELP;
        case "help":
            return HelpStrings.HELP_HELP;
        case "quickstart":
            return HelpStrings.QUICKSTART_HELP;
        default:
            return "";
        }
    }


    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
