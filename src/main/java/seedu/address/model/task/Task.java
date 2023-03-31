package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.Relationship;
import seedu.address.model.person.Person;
import seedu.address.model.shared.Datetime;
import seedu.address.model.shared.Id;

/**
 * Represents a Task in OfficeConnect.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task implements Relationship<Task> {

    // Identity fields
    private final Id id;

    // Data fields
    private final Title title;
    private final Content content;
    private final Status status;

    private List<Person> peoples = new ArrayList<>();

    private final Datetime createDateTime;
    private final Datetime deadline;



    /**
     * Every field must be present and not null.
     */
    public Task(Title title, Content content, Status status) {
        requireAllNonNull(title, content, status);
        this.title = title;
        this.content = content;
        this.status = status;
        this.id = new Id();
        this.deadline = new Datetime();
        this.createDateTime = new Datetime(LocalDateTime.now().toString());

    }

    /**
     * Every field must be present and not null.
     */
    public Task(Title title, Content content, Status status, Datetime deadline) {
        requireAllNonNull(title, content, status, deadline);
        this.title = title;
        this.content = content;
        this.status = status;
        this.id = new Id();
        this.deadline = deadline;
        this.createDateTime = new Datetime(LocalDateTime.now().toString());

    }


    /**
     * ID must be specific when loading from local storage
     */
    public Task(Title title, Content content, Status status, Datetime deadline, Id id) {
        requireAllNonNull(title, content, status);
        this.title = title;
        this.content = content;
        this.status = status;
        this.id = id;
        this.deadline = deadline;
        this.createDateTime = new Datetime(LocalDateTime.now().toString());


    }

    /**
     * ID must be specific when loading from local storage
     */
    public Task(Title title, Content content, Status status, Datetime createDateTime, Datetime deadline, Id id) {
        requireAllNonNull(title, content, status);
        this.title = title;
        this.content = content;
        this.status = status;
        this.id = id;
        this.createDateTime = createDateTime;
        this.deadline = deadline;

    }

    public Id getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public String getStringTitleLowerCase() {
        return title.getValue().toLowerCase();
    }

    public Content getContent() {
        return content;
    }

    public Status getStatus() {
        return status;
    }

    public Datetime getCreateDateTime() {
        return createDateTime;
    }

    public Datetime getDeadline() {
        return deadline;
    }

    public void setPeoples(List<Person> peoples) {
        this.peoples = peoples;
    }

    public List<Person> getPeoples() {
        return peoples;
    }



    /**
     * Returns true if both tasks have the same title.
     * This defines a weaker notion of equality between two tasks.
     */
    @Override
    public boolean isSame(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
            && otherTask.getTitle().equals(getTitle());
    }

    @Override
    public boolean hasSameId(Task otherTask) {
        return otherTask.getId().equals(getId());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getTitle().equals(getTitle())
            && otherTask.getContent().equals(getContent())
            && otherTask.getStatus().equals(getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, status);
    }

    @Override
    public String toString() {
        return getTitle()
            + "; Status: "
            + getStatus()
            + "; Content: "
            + getContent()
            + "; Create Date: "
            + getCreateDateTime().toString()
            + "; Deadline: "
            + getDeadline().toString();
    }

}
