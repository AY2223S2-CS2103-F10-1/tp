package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

/**
 * A TaskCard Controller class
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    public final Task task;

    @FXML
    private FlowPane personIndexes;
    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label content;
    @FXML
    private Label status;
    @FXML
    private Label id;



    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        title.setText(task.getTitle().getValue());
        content.setText(task.getContent().getValue());
        setStatusSymbol(task.getStatus().isValue());

        task.getPeoples()
            .forEach(person -> {
                Label label = new Label(String.valueOf(person.getName()));
                label.getStyleClass().add("cell_small_label");
                label.getStyleClass().add("person-index");
                FlowPane.setMargin(label, new Insets(0, 5, 5, 0));

                personIndexes.getChildren().add(label);
            });


    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText())
            && task.equals(card.task);
    }

    private void setStatusSymbol(boolean isDone) {
        if (isDone) {
            status.setText("✓"); // Unicode character for the tick symbol
            status.setStyle("-fx-text-fill: green;"); // Color the tick symbol green
        } else {
            status.setText("✗"); // Unicode character for the cross symbol
            status.setStyle("-fx-text-fill: red;"); // Color the cross symbol red
        }
    }
}
