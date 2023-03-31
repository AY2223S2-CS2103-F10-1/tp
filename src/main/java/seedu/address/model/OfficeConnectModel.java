package seedu.address.model;

import static seedu.address.logic.commands.UnassignCommand.MESSAGE_NON_EXIST_ASSIGNMENT;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;


/**
 * The API of the OfficeConnectModel component.
 */
public class OfficeConnectModel {
    public static final Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;
    private final RepositoryModelManager<Task> taskModelManager;
    private final RepositoryModelManager<AssignTask> assignTaskModelManager;

    /**
     * Initializes a OfficeConnectModel empty data.
     */
    public OfficeConnectModel() {
        taskModelManager = new RepositoryModelManager<>(new Repository<Task>());
        assignTaskModelManager = new RepositoryModelManager<>(new Repository<AssignTask>());
    }
    /**
     * Initializes a OfficeConnectModel given data.
     */
    public OfficeConnectModel(RepositoryModelManager<Task> taskModelManager,
                              RepositoryModelManager<AssignTask> assignTaskModelManager) {
        this.taskModelManager = taskModelManager;
        this.assignTaskModelManager = assignTaskModelManager;
    }

    public RepositoryModelManager<Task> getTaskModelManager() {
        return taskModelManager;
    }

    public RepositoryModelManager<AssignTask> getAssignTaskModelManager() {
        return assignTaskModelManager;
    }

    public void setTaskPeoples(ObservableList<Person> peoples) {
        for (Task t : taskModelManager.getReadOnlyRepository().getData()) {
            List<AssignTask> assignTasks = assignTaskModelManager.filter(a -> a.getTaskId().equals(t.getId()));
            t.setPeoples(peoples.filtered(p -> assignTasks.stream().anyMatch(a ->a.getPersonId().equals(p.getId()))));
        }
    }

    /**
     * Checks if task model manager contains task.
     *
     * @param task task to be checked
     * @return true if task model manager contains the item
     */
    public boolean hasTaskModelManagerItem(Task task) {
        return taskModelManager.hasItem(task);
    }

    /**
     * Adds task to task model manager.
     *
     * @param task task to be added
     */
    public void addTaskModelManagerItem(Task task) {
        taskModelManager.addItem(task);
    }

    /**
     * Checks if assigntask model manager contains assignment.
     *
     * @param assignTask assignment to be checked
     * @return true if assigntask model manager contains the item
     */
    public boolean hasAssignTaskModelManagerItem(AssignTask assignTask) {
        return assignTaskModelManager.hasItem(assignTask);
    }

    /**
     * Adds assignment to assigntask model manager.
     *
     * @param assignTask assignment to be added
     */
    public void addAssignTaskModelManagerItem(AssignTask assignTask) {
        assignTaskModelManager.addItem(assignTask);
    }

    /**
     * Updates filtered item list in task model manager.
     *
     * @param predicate predicate that determines if item should stay in the filtered item list
     */
    public void updateTaskModelManagerFilteredItemList(Predicate<Task> predicate) {
        taskModelManager.updateFilteredItemList(predicate);
    }

    public ObservableList<Task> getTaskModelManagerFilteredItemList() {
        return taskModelManager.getFilteredItemList();
    }

    /**
     * Edits the given task in task model manager.
     *
     * @param target     target task to be edited
     * @param editedTask edited task
     * @param model      person list
     */
    public void setTaskModelManagerItem(Task target, Task editedTask, Model model) {
        taskModelManager.setItem(target, editedTask);
        focusTask(target, model);
    }

    /**
     * Deletes task from task model manager.
     *
     * @param task task to be deleted
     */
    public void deleteTaskModelManagerItem(Task task) {
        taskModelManager.deleteItem(task);
    }

    public ReadOnlyRepository<Task> getTaskModelManagerReadOnlyRepository() {
        return taskModelManager.getReadOnlyRepository();
    }

    /**
     * Focus onto a specific task.
     */
    public void focusTask(Task taskToFocus, Model model) {
        updateTaskModelManagerFilteredItemList(task -> task.getId().equals(taskToFocus.getId()));
        List<AssignTask> assignTasks = getAssignTaskModelManager()
            .filter(assign -> assign.getTaskId().equals(taskToFocus.getId()));
        model.updateFilteredPersonList(person -> assignTasks.stream()
            .anyMatch(assign -> assign.getPersonId().equals(person.getId())));
    }

    /**
     * Deletes assignment from assign task model manager.
     *
     * @param assignTask assignment to be deleted
     */
    public void deleteAssignTaskModelManagerItem(AssignTask assignTask) {
        assignTaskModelManager.deleteItem(assignTask);
    }


    /**
     * Checks the given index is within the filter task list range
     *
     * @param index to check
     * @return true if within the range else false
     */
    public boolean isValidFilterTaskListIndexRange(int index) {
        return index >= 0 && index < taskModelManager.getFilteredItemList().size();
    }

    /**
     * Delete the task assignment from a person
     *
     * @param person       assigned to the task
     * @param taskFilterId base on the current task filter list
     * @return task that has been deleted
     */
    public Task deleteAssignment(Person person, int taskFilterId) throws CommandException {
        Task task = taskModelManager.getFilterItem(taskFilterId);
        AssignTask toDelete = new AssignTask(person, task);

        if (!assignTaskModelManager.hasItem(toDelete)) {
            throw new CommandException(MESSAGE_NON_EXIST_ASSIGNMENT);
        }
        assignTaskModelManager.deleteItem(toDelete);

        List<AssignTask> assignTasks = assignTaskModelManager.filter(a -> a.getPersonId().equals(person.getId()));

        taskModelManager.updateFilteredItemList(t -> assignTasks.stream()
            .anyMatch(a -> a.getTaskId().equals(t.getId())));
        return task;
    }
}
