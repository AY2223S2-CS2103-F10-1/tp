package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.mapping.AssignTask;

/**
 * A {@code ListAssignment} class that lists all assigned or unassigned persons and tasks in OfficeConnect.
 */
public class ListAssignment extends Command {

    public static final String MESSAGE_SUCCESS = "Listed all %s %s";
    public static final String TYPE_PERSON = "person";
    public static final String TYPE_TASK = "task";
    public static final String COMMAND_WORD_UNASSIGNED_PERSON = "viewunassignedp";
    public static final String COMMAND_WORD_UNASSIGNED_TASK = "viewunassignedt";
    public static final String COMMAND_WORD_ASSIGNED_TASK = "viewassignedt";
    public static final String COMMAND_WORD_ASSIGNED_PERSON = "viewassignedp";
    public static final String COMMAND_WORD_ASSIGNED_ALL = "viewassignedall";
    public static final String COMMAND_WORD_UNASSIGNED_ALL = "viewunassignedall";

    private final String type;
    private final Boolean assign;

    /**
     * Constructs a ListAssignment command with the specified type and assign.
     *
     * @param type   A string representing the type, either "person" or "task".
     * @param assign A boolean value, true for assigned and false for unassigned.
     */
    public ListAssignment(String type, boolean assign) {
        this.type = type;
        this.assign = assign;
    }

    /**
     * Returns a string "assign" if the provided boolean is true, or "unassign" if false.
     *
     * @param assign A boolean value, true for assigned and false for unassigned.
     * @return A string "assign" if the provided boolean is true, or "unassign" if false.
     */
    private String getAssignString(boolean assign) {
        return assign ? "assigned" : "unassigned";
    }

    /**
     * Executes the ListAssignment command, updating the filtered person and task lists
     * based on the specified type and assignment status.
     *
     * @param model              The model to update the filtered person list.
     * @param officeConnectModel The OfficeConnectModel to update the filtered task list.
     * @return A CommandResult containing the success message.
     */
    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) {
        requireAllNonNull(model, officeConnectModel);
        ObservableList<AssignTask> assignTaskObservable = officeConnectModel.getAssignTaskModelManager()
            .getReadOnlyRepository().getData();
        if (type.equals(TYPE_PERSON)) {
            model.updateFilteredPersonList(p -> assign == assignTaskObservable.stream()
                .anyMatch(a -> a.getPersonId().equals(p.getId())));

        } else if (type.equals(TYPE_TASK)) {
            officeConnectModel.updateTaskModelManagerFilteredItemList(t -> assign == assignTaskObservable.stream()
                .anyMatch(a -> a.getTaskId().equals(t.getId())));
        } else {
            officeConnectModel.updateTaskModelManagerFilteredItemList(t -> assign == assignTaskObservable.stream()
                .anyMatch(a -> a.getTaskId().equals(t.getId())));
            model.updateFilteredPersonList(p -> assign == assignTaskObservable
                .stream().anyMatch(a -> a.getPersonId().equals(p.getId())));
        }
        String typeMsg = type.equals("") ? "person(s) and task(s)" : type + "(s)";
        return new CommandResult(String.format(MESSAGE_SUCCESS, getAssignString(assign), typeMsg));
    }


}
