package seedu.address.ui;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.Task;

/**
 * A TaskCard Controller class
 */
public class TaskCard extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(TaskCard.class);

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
    @FXML
    private VBox cardVbox;
    @FXML
    private Label title;
    @FXML
    private Label content;
    @FXML
    private Label status;
    @FXML
    private Label id;
    @FXML
    private Label createDate;

    @FXML
    private Label deadline;


    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        title.setText(task.getTitle().getValue());
        content.setText(task.getContent().getValue());
        setStatusSymbol(task.getStatus().isValue());
        createDate.setText("Create Date: " + task.getCreateDateTime().getTimestamp().map(timestamp -> {
            LocalDateTime datetime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
            return datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }).orElse(""));

        if (task.getDeadline().getTimestamp().isPresent()) {
            LocalDateTime endTime = LocalDateTime.ofInstant(Instant
                .ofEpochMilli(task.getDeadline().getTimestamp().get()), ZoneId.systemDefault());
            LocalDateTime startTime = LocalDateTime.ofInstant(Instant
                .ofEpochMilli(task.getCreateDateTime().getTimestamp().get()), ZoneId.systemDefault());

            deadline.setText("Deadline: " + endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            long totalDiff = ChronoUnit.SECONDS.between(startTime, endTime);
            long currDiff = ChronoUnit.SECONDS.between(startTime, LocalDateTime.now());

            double timeLapse = totalDiff == 0 ? 1 : (double) currDiff / totalDiff;

            logger.info("Progress time :" + timeLapse);
            ProgressBar timeProgress = new ProgressBar(timeLapse);
            timeProgress.getStyleClass().add("time_progress");
            cardVbox.getChildren().add(timeProgress);
            Region progressBarParent = (Region) timeProgress.getParent();
            timeProgress.prefWidthProperty().bind(progressBarParent.widthProperty());
            if (task.getStatus().isValue()) {
                timeProgress.setStyle("-fx-accent: green;"); // set the color to blue

            } else if (timeLapse >= 1) {
                timeProgress.setStyle("-fx-accent: red;"); // set the color to blue
            }
        } else {
            deadline.setText("Deadline: -");
        }


        task.getPeoples()
            .forEach(person -> {
                Label label = new Label(String.valueOf(person.getName()));
                label.getStyleClass().add("cell_small_label");
                label.getStyleClass().add("person-index");
                FlowPane.setMargin(label, new Insets(0, 5, 5, 0));
                label.setMaxWidth(200);
                label.setWrapText(true);
                personIndexes.setMinWidth(210);
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
