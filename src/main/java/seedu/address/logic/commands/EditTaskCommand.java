package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddTaskCommand.MESSAGE_DUPLICATE_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_CREATEDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.shared.Datetime;
import seedu.address.model.shared.Id;
import seedu.address.model.task.Content;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;


/**
 * Edits the details of an existing task in OfficeConnect.
 */
public class EditTaskCommand extends Command {
    public static final String COMMAND_WORD = "editt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of a task by the index number."
        + "Parameters: "
        + PREFIX_TITLE + "TITLE "
        + PREFIX_CONTENT + "CONTENT "
        + PREFIX_STATUS + "STATUS "
        + PREFIX_TASK_CREATEDATE + "CREATEDATE "
        + PREFIX_TASK_DEADLINE + "DEADLINE \n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_TITLE + "Complete Project X "
        + PREFIX_CONTENT + "Do the UML diagram "
        + PREFIX_STATUS + "false "
        + PREFIX_TASK_CREATEDATE + "CREATEDATE "
        + PREFIX_TASK_DEADLINE + "DEADLINE ";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "New task added: %1$s";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";


    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * Creates an EditTaskCommand to add the specified {@code Task}
     */
    public EditTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);
        this.index = index;
        this.editTaskDescriptor = editTaskDescriptor;
    }

    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) throws CommandException {
        requireNonNull(model);
        requireNonNull(officeConnectModel);

        List<Task> lastShownList = officeConnectModel.getTaskModelManager().getFilteredItemList();
        RepositoryModelManager<Task> taskModelManager = officeConnectModel.getTaskModelManager();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);
        if (!taskToEdit.isSame(editedTask) && taskModelManager.hasItem(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        officeConnectModel.setTaskModelManagerItem(taskToEdit, editedTask, model);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskCommand.EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Title updatedTitle = editTaskDescriptor.getTitle().orElse(taskToEdit.getTitle());
        Content updatedContent = editTaskDescriptor.getContent().orElse(taskToEdit.getContent());
        Status updatedStatus = editTaskDescriptor.getStatus().orElse(taskToEdit.getStatus());
        Id updateId = editTaskDescriptor.getId().orElse(taskToEdit.getId());
        Datetime updateCreateDate = editTaskDescriptor.getCreateDate().orElse(taskToEdit.getCreateDateTime());
        Datetime updateDeadline = editTaskDescriptor.getDeadline().orElse(taskToEdit.getDeadline());

        return new Task(updatedTitle, updatedContent, updatedStatus, updateCreateDate, updateDeadline, updateId);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskCommand)) {
            return false;
        }

        // state check
        EditTaskCommand e = (EditTaskCommand) other;
        return index.equals(e.index)
                && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private Title title;
        private Content content;
        private Status status;
        private Id id;

        private Datetime createDate;
        private Datetime deadline;

        public EditTaskDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditTaskDescriptor(EditTaskCommand.EditTaskDescriptor toCopy) {
            setTitle(toCopy.title);
            setContent(toCopy.content);
            setStatus(toCopy.status);
            setId(toCopy.id);
            setCreateDate(toCopy.createDate);
            setDeadline(toCopy.deadline);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, content, status, deadline, createDate);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public void setTitle(String title) {
            this.title = new Title(title);
        }

        public Optional<Datetime> getCreateDate() {
            return Optional.ofNullable(createDate);
        }

        public void setCreateDate(Datetime createDate) {
            this.createDate = createDate;
        }

        public Optional<Datetime> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setDeadline(Datetime deadline) {
            this.deadline = deadline;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setContent(Content content) {
            this.content = content;
        }

        public void setContent(String content) {
            this.content = new Content(content);
        }
        public Optional<Content> getContent() {
            return Optional.ofNullable(content);
        }

        public void setStatus(Status status) {
            this.status = status;
        }
        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        public void setId(Id id) {
            this.id = id;
        }
        public Optional<Id> getId() {
            return Optional.ofNullable(id);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskCommand.EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskCommand.EditTaskDescriptor e = (EditTaskCommand.EditTaskDescriptor) other;
            return getTitle().equals(e.getTitle())
                    && getContent().equals(e.getContent())
                    && getStatus().equals(e.getStatus())
                    && getId().equals(e.getId());
        }
    }
}
