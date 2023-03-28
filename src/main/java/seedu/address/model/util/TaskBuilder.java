package seedu.address.model.util;

import java.time.LocalDateTime;

import seedu.address.model.Repository;
import seedu.address.model.shared.Datetime;
import seedu.address.model.shared.Id;
import seedu.address.model.task.Content;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String RANDOM_TASK_TITLE = "Random Title";
    public static final String RANDOM_TASK_CONTENT = "Random Content ABC";
    public static final boolean RANDOM_TASK_STATUS = true;
    public static final String DEFAULT_TITLE = "Recreation day";
    public static final String DEFAULT_CONTENT = "Play football";
    public static final boolean DEFAULT_STATUS = false;

    private Title title;
    private Content content;
    private Status status;
    private Datetime deadline;
    private Datetime createTime;

    private Id id;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        title = new Title(DEFAULT_TITLE);
        content = new Content(DEFAULT_CONTENT);
        status = new Status(DEFAULT_STATUS);
        createTime = new Datetime(LocalDateTime.now().toString());
        deadline = new Datetime();
        id = new Id();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        title = taskToCopy.getTitle();
        content = taskToCopy.getContent();
        status = taskToCopy.getStatus();
        id = taskToCopy.getId();
        deadline = taskToCopy.getDeadline();
        createTime = taskToCopy.getCreateDateTime();
    }

    private Task buildRandomTask() {
        return new Task(new Title(RANDOM_TASK_TITLE), new Content(RANDOM_TASK_CONTENT),
            new Status(RANDOM_TASK_STATUS));
    }

    private Task buildRandomTaskWithIndex(int index) {
        return new Task(new Title(RANDOM_TASK_TITLE + " " + index),
            new Content(RANDOM_TASK_CONTENT + " " + index),
            new Status(RANDOM_TASK_STATUS));
    }

    /**
     * Generates a random task
     */
    public static Task ofRandomTask() {
        return new TaskBuilder().buildRandomTask();

    }
    /**
     * Generates a random list of tasks
     */
    public static Repository<Task> ofRandomTaskBook(int size) {
        Repository<Task> taskRepository = new Repository<>();
        for (int i = 0; i < size; i++) {
            taskRepository.addItem(new TaskBuilder().buildRandomTaskWithIndex(i));
        }
        return taskRepository;

    }

    /**
     * Sets the {@code Title} of the {@code Task} that we are building.
     */
    public TaskBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Content} of the {@code Task} that we are building.
     */
    public TaskBuilder withContent(String content) {
        this.content = new Content(content);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Task} that we are building.
     */
    public TaskBuilder withStatus(boolean status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Task} that we are building.
     */
    public TaskBuilder withStatus(String status) {
        this.status = new Status(Boolean.parseBoolean(status));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = new Datetime(deadline);
        return this;
    }

    /**
     * Sets the {@code CreateTime} of the {@code Task} that we are building.
     */
    public TaskBuilder withDCreateTime(String createTime) {
        this.createTime = new Datetime(createTime);
        return this;
    }


    /**
     * Sets the {@code Id} of the {@code Task} that we are building.
     */
    public TaskBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code Task} that we are building.
     */
    public TaskBuilder withId(Id id) {
        this.id = id;
        return this;
    }

    /**
     * Sets Task to be created
     *
     * @return Task containing specified {@code Title, @code Content, @code Status}
     */
    public Task build() {
        return new Task(title, content, status, createTime, deadline, id);
    }


}
