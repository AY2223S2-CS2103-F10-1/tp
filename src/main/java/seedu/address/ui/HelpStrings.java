package seedu.address.ui;

/**
 * Provides HelpWindow class with long strings to be displayed on screen.
 */
public class HelpStrings {
    public static final String HELP_MESSAGE = "For more help, Refer to the user guide: " + HelpStrings.USERGUIDE_URL;
    public static final String CREDITS_MESSAGE =
            "This project was based on the SE-EDU Initiative, using resources from ICON8 and freepik. Enjoy!";
    public static final String USERGUIDE_URL = "\nhttps://ay2223s2-cs2103-f10-1.github.io/tp/UserGuide.html";
    public static final String ADDMETHODS_HELP = "Add commands allow you to "
            + "add tasks, or people.";
    public static final String ADDPERSON_HELP = "What it does: Adds a person to OfficeConnect. \n\n"
            + "Format: addp n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…\n"
            + "Example: addp n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01";
    public static final String ADDTASK_HELP = "What it does: Adds a task to OfficeConnect. \n\n"
            + "Datetime format for DEADLINE for tasks should be of the format YYYY-MM-DD HH:MM:SS. "
            + "e.g dl/2023-01-01 23:11:01. Time(HH:MM:SS) is optional! "
            + "There must be trailing zeros for digits less than 10.\n\n"
            + "Format: addt t/TITLE c/CONTENT st/STATUS [dl/DEADLINE]\n"
            + "Example: addt t/Complete slides c/Finish slides for meeting st/false dl/2024-01-01 20:02:01";
    public static final String DELETEMETHODS_HELP = "Delete commands allow you to"
            + " delete tasks, or people.";
    public static final String DELETEPERSON_HELP = "What it does: Deletes the person at the specified ID.\n\n"
            + "The index refers to the INDEX shown in the displayed person list."
            + "The INDEX must be a positive integer (eg. 1, 2, 3...) \n\n"
            + "Format: deletep INDEX";
    public static final String DELETETASK_HELP = "What it does: Deletes the task at the specified INDEX.\n\n"
            + "The index refers to the INDEX shown in the displayed task list"
            + "The INDEX must be a positive integer (eg. 1, 2, 3...) \n\n"
            + "Format: deletet INDEX";
    public static final String EDITMETHODS_HELP = "Edit commands allow you to modify an existing task or person.";
    public static final String EDITPERSON_HELP = "Edits an existing person in OfficeConnect.\n\n"
            + "Format: editp INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [tag/TAG]…";
    public static final String EDITTASK_HELP = "Edits an existing task in OfficeConnect.\n\n"
            + "Format: editt INDEX [title/TITLE] [c/CONTENT] [st/TRUE] [dl/DEADLINE] [createdate/CREATEDATE]";
    public static final String LISTMETHODS_HELP = "List commands shows results of persons, or tasks.";
    public static final String LISTPERSON_HELP = "What it does: Lists all persons in OfficeConnect. \n\n"
            + "Format: listp";
    public static final String LISTTASK_HELP = "What it does: Lists all the tasks in OfficeConnect. \n\n"
            + "Format: listt";
    public static final String LISTALL_HELP = "What it does: Lists all the tasks and persons in OfficeConnect. \n\n"
            + "Format: listall";
    public static final String FINDMETHODS_HELP = "Find commands allow you to find persons, or tasks.";
    public static final String FINDTASK_HELP = "What it does: Finds the task based on given keyword. \n\n"
            + "Format: findt TITLE\n"
            + "Example: findt CS2103 TP";
    public static final String FINDPERSON_HELP = "Shows a list of tasks assigned "
            + "to an existing person in OfficeConnect.\n\n"
            + "Format: findp NAME\n"
            + "Example: findp John Cena";
    public static final String ASSIGNMETHODS_HELP = "Assign or Unassigns a task to/from a person.";
    public static final String ASSIGN_HELP = "What it does: Assigns the task at"
            + " specified index to person at specified"
            + "index. The index refers to the index number shown in the displayed persons/task list. \n\n"
            + "Format: assign ti/INDEX pi/INDEX\n"
            + "Example: assign ti/ 2 pi/ 3 , which assigns task 2 to person 3.";
    public static final String UNASSIGN_HELP = "What it does: Remove the existing task from"
            + " specified index to person at specified"
            + "index. The index refers to the index number shown in the displayed persons/task list. \n\n"
            + "Format: assign ti/INDEX pi/INDEX\n"
            + "Example: unassign ti/ 2 pi/ 3 , which removes task 2 from person 3.";
    public static final String MARKMETHODS_HELP = "Marking commands mark or unmark an"
            + " existing task as done.";
    public static final String MARK_HELP = "Marks an existing task in OfficeConnect. "
            + "Changes the status of the task at the specified index to completed.\n\n"
            + "Format: mark INDEX\n"
            + "Example: mark 2 , marks task 2 as completed";
    public static final String UNMARK_HELP = "Unmarks an existing task in OfficeConnect. "
            + "Changes the status of the task at the specified index to uncompleted.\n\n"
            + "Format: unmark INDEX\n"
            + "Example: unmark 2 , marks task 2 as incomplete";
    public static final String FILTERMETHODS_HELP = "Filter commands filter people according to tag.";
    public static final String FILTERPERSON_HELP = "Displays a list of all persons with the assigned "
            + "tag. Only ONE tag can be keyed as input.\n\n"
            + "Format: filterp tag/TAG\n"
            + "Example: filterp tag/Logistics , shows all persons with the Logistics tag.";
    public static final String VIEWMETHODS_HELP = "View commands provide many different ways to change"
            + " the persons and views within OfficeConnect.";
    public static final String VIEWPERSON_HELP = "Shows a list of tasks assigned to a person"
            + " according to the displayed index.\n\n"
            + "Format: pi INDEX\n"
            + "Example: pi 2 , displays person 2 and all tasks assigned to that person";
    public static final String VIEWTASK_HELP = "Shows a list of persons assigned to a task"
            + " according to the displayed index.\n\n"
            + "Format: ti INDEX\n"
            + "Example: ti 1 , displays task 1 and all persons assigned to that task";
    public static final String VIEWASSIGNEDALL_HELP = "Displays a list of all persons who have been assigned to"
            + " a task and all tasks that have been assigned to a person.\n\n"
            + "Format: viewassignedall";
    public static final String VIEWASSIGNEDPERSON_HELP = "Displays a list of all persons who have "
            + "been assigned to"
            + " a task.\n\n"
            + "Format: viewassignedp";
    public static final String VIEWASSIGNEDTASK_HELP = "Displays a list of all tasks that have"
            + " been assigned to any person\n\n"
            + "Format: viewassignedt";
    public static final String VIEWUNASSIGNEDALL_HELP = "Displays a list of all persons who have not been assigned to"
            + " any task and all tasks that have not been assigned to a person.\n\n"
            + "Format: viewunassignedall";
    public static final String VIEWUNASSIGNEDPERSON_HELP = "Displays a list of all persons who have not "
            + "been assigned to"
            + " a task.\n\n"
            + "Format: viewunassignedp";
    public static final String VIEWUNASSIGNEDTASK_HELP = "Displays a list of all tasks that have not"
            + " been assigned to any person\n\n"
            + "Format: viewunassignedt";
    public static final String GUIDEMETHODS_HELP = "These are quickstart and help commands, to aid the user. ";
    public static final String HELP_HELP = "Opens the help window.\n\n"
            + "Format: help";
    public static final String QUICKSTART_HELP = "Opens the quickstart window.\n\n"
            + "Format: quickstart";



}
