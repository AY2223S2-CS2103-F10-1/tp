package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.shared.Datetime;
import seedu.address.model.shared.Id;
import seedu.address.model.util.TaskBuilder;

public class TaskTest {

    @Test
    public void constructor_validInput_success() {
        // Valid input parameters
        Title title = new Title("Math Homework");
        Content content = new Content("Do exercises 1 to 10 in Chapter 3");
        Status status = new Status(false);

        // Call constructor
        Task task = new Task(title, content, status);

        // Assert that fields are correctly set
        assertEquals(title, task.getTitle());
        assertEquals(content, task.getContent());
        assertEquals(status, task.getStatus());
        assertNotNull(task.getId());
        assertNotNull(task.getCreateDateTime().getTimestamp());
        assertNotNull(task.getDeadline().getTimestamp());
    }

    @Test
    public void constructor_validInputWithDeadline_success() {
        // Valid input parameters with deadline
        Title title = new Title("Math Homework");
        Content content = new Content("Do exercises 1 to 10 in Chapter 3");
        Status status = new Status(false);
        Datetime deadline = new Datetime(LocalDateTime.now().plusDays(1).toString());
        Id id = new Id();

        // Call constructor
        Task task = new Task(title, content, status, deadline, id);

        // Assert that fields are correctly set
        assertEquals(title, task.getTitle());
        assertEquals(content, task.getContent());
        assertEquals(status, task.getStatus());
        assertEquals(id, task.getId());
        assertNotNull(task.getCreateDateTime().getTimestamp());
        assertEquals(deadline.getTimestamp().orElse(null), task.getDeadline().getTimestamp().orElse(null));
    }

    @Test
    public void constructor_validInputWithCreateDateTimeAndDeadline_success() {
        // Valid input parameters with createDateTime and deadline
        Title title = new Title("Math Homework");
        Content content = new Content("Do exercises 1 to 10 in Chapter 3");
        Status status = new Status(false);
        Datetime deadline = new Datetime(LocalDateTime.now().plusDays(1).toString());
        Datetime createDateTime = new Datetime(LocalDateTime.now().toString());
        Id id = new Id();

        // Call constructor
        Task task = new Task(title, content, status, createDateTime, deadline, id);

        // Assert that fields are correctly set
        assertEquals(title, task.getTitle());
        assertEquals(content, task.getContent());
        assertEquals(status, task.getStatus());
        assertEquals(id, task.getId());
        assertEquals(createDateTime.getTimestamp().orElse(null), task.getCreateDateTime().getTimestamp().orElse(null));
        assertEquals(deadline.getTimestamp().orElse(null), task.getDeadline().getTimestamp().orElse(null));
    }

    @Test
    public void isSame_sameTask_success() {
        // Create a task
        Title title = new Title("Math Homework");
        Content content = new Content("Do exercises 1 to 10 in Chapter 3");
        Status status = new Status(false);
        Task task = new Task(title, content, status);

        // Call isSame with same task
        boolean result = task.isSame(task);

        // Assert that result is true
        assertTrue(result);
    }

    @Test
    void isSame_differentTaskWithDifferentTitle_returnsFalse() {
        Task task1 = new TaskBuilder().withTitle("Task 1").build();
        Task task2 = new TaskBuilder().withTitle("Task 2").build();
        assertFalse(task1.isSame(task2));
    }

    @Test
    void equals_null_returnsFalse() {
        Task task = new TaskBuilder().build();
        assertNotEquals(null, task);
    }

    @Test
    void equals_sameTask_returnsTrue() {
        Task task = new TaskBuilder().build();
        assertEquals(task, task);
    }

    @Test
    void equals_equalTasks_returnsTrue() {
        Task task1 = new TaskBuilder().build();
        Task task2 = new TaskBuilder().build();
        assertEquals(task1, task2);
    }

    @Test
    void equals_differentTitle_returnsFalse() {
        Task task1 = new TaskBuilder().withTitle("Task 1").build();
        Task task2 = new TaskBuilder().withTitle("Task 2").build();
        assertNotEquals(task1, task2);
    }

    @Test
    void equals_differentContent_returnsFalse() {
        Task task1 = new TaskBuilder().withContent("Content 1").build();
        Task task2 = new TaskBuilder().withContent("Content 2").build();
        assertNotEquals(task1, task2);
    }

    @Test
    void equals_differentStatus_returnsFalse() {
        Task task1 = new TaskBuilder().withStatus(false).build();
        Task task2 = new TaskBuilder().withStatus(true).build();
        assertNotEquals(task1, task2);
    }

    @Test
    void hashCode_equalTasks_returnsTrue() {
        Id id = new Id();
        Task task1 = new Task(new Title("Math homework"), new Content("Do exercises 1 to 10 in Chapter 3"),
            new Status(true), new Datetime(), new Datetime(), id);
        Task task2 = new Task(new Title("Math homework"), new Content("Do exercises 1 to 10 in Chapter 3"),
            new Status(true), new Datetime(), new Datetime(), id);
        assertEquals(task1.hashCode(), task2.hashCode());
    }

}
