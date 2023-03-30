---
layout: page
title: User Guide
---
OfficeConnect is a task management tool designed specifically for managerial role personnel at companies.

The target users are individuals who are responsible for assigning tasks and overseeing the work of a team.

The product addresses several challenges faced by managers in the current office environment, such as work overload among subordinates, difficulties in coordinating tasks with a large number of employees, and time-consuming manual tasks like typing and sending emails.

OfficeConnect offers a solution to these problems by providing better visibility into subordinates’ workloads, allowing managers to efficiently delegate tasks in an organised manner. The app also automates the process of planning and communicating with subordinates, making it easier for managers to get things done. Additionally, the app ensures that emails are sent during working hours, so that subordinates will not be disturbed outside of work.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `officeconnect.jar` from [OfficeConnect Release Page](https://github.com/AY2223S2-CS2103T-W10-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your OfficeConnect.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar officeconnect.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/improvedUI.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to OfficeConnect.

   * `add s/Complete slides c/Finish up slides for meeting st/false` : Adds a task with title `Complete slides` to OfficeConnect.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…` can be used as (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Displays a comprehensive window detailing the outline of executable commands by user.

A hierarchical view on the left window lists the different available commands, with the description of the command on the right.

![image](https://user-images.githubusercontent.com/99934242/225213208-f94de7e4-2085-41e7-9325-9ecef5fe246f.png)

Format: `help`


### Adding a person: `add`

Adds a person to OfficeConnect.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…`

<div class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in OfficeConnect.

Format: `list`

### Editing a person : `edit`

Edits an existing person in OfficeConnect.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from OfficeConnect.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from OfficeConnect.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

OfficeConnect data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

OfficeConnect data are saved as a JSON file `[JAR file location]/data/officeconnect.json`. Advanced users are welcome to update data directly by editing that data file.

<div class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, OfficeConnect will discard all data and start with an empty data file at the next run.
</div>

# Command Summary For OfficeConnect

## Adding a Task: `addtask`

Adds a task to OfficeConnect.

Format: `addtask title/TITLE c/CONTENT st/STATUS`

Examples:
- `addtask title/Complete slides c/Finish slides for meeting st/false`

## Deleting a Task: `deletetask`

Deletes the specified task from OfficeConnect

Format: `deletetask INDEX`

* Deletes the task at the specified INDEX.
* The INDEX refers to the INDEX shown in the displayed task list.
* The INDEX must be a positive integer 1, 2, 3...

Examples:
- `listtasks` followed by `delete 2` deletes the 2nd task in the task list.
- `findtasks book` followed by `delete 1` deletes the 1st task in the results of the `findtask` command.

## Locating a Specific Task: `findtask`

Finds the task based on given keyword.

Format: `findtask KEYWORD [MORE_KEYWORDS]`

Examples:
- `findtask complete`

## Listing the Tasks: `listtask`

Shows a list of all tasks in OfficeConnect.

Format: `listtask`

## Assigning a Task to a Person: `assign`

Assigns an existing task to an existing person in OfficeConnect.

Format: `assign ti/INDEX pi/INDEX`

* Assigns the task at specified index to the person at specified index.
* The index refers to the index number shown in the displayed person/task list.
* The index must be a positive integer 1, 2, 3...

Examples:
- `assign ti/ 2 pi/ 3` assigns task 2 to person 3.

## Remove a Task assignment from a Person: `unassign`

Remove assignment of an existing task from an existing person in OfficeConnect.

Format: `unassign ti/INDEX pi/INDEX`

* Remove assignment of the task at specified index from the person at specified index.
* The index refers to the index number shown in the displayed person/task list.
* The index must be a positive integer 1, 2, 3...

Examples:
- `unassign ti/ 2 pi/ 3` assigns task 2 to person 3.

## Marking a Task as completed: `mark`

Marks an existing task in OfficeConnect.

Format: `mark INDEX`

* Changes the status of the task at the specified index to completed.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3...

Examples:
- `mark 2` marks task 2 as completed

## Unmarking a Task: `unmark`

Unmarks a task in OfficeConnect

Format: `unmark INDEX`

* Changes the status of the task at the specified index to uncompleted.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3...

## Find tasks assigned to a Person: `find`

Shows a list of tasks assigned to an existing person in OfficeConnect.

Format: `find NAME`

* Finds the list of tasks that are assigned to the person with the specified name.

Examples:
- `find John Cena` displays all tasks that are assigned to him.

## Find tasks assigned to a Person: `findtask`


Shows a list of persons assigned to an existing task in OfficeConnect.

Format: `findtask TITLE`

* Finds the list of persons who are assigned to the task with the specified title.

Examples:
- `findtask CS2103 TP` displays everyone who are assigned to this task.


## View Assigned People: `ap`

Displays a list of all persons who have been assigned to a task.

Format: `ap`

Examples:
- `ap` displays a list of all persons who have been assigned to a task.

---

## View Assigned Tasks: `at`

Displays a list of all tasks that have been assigned to a person.

Format: `at`

Examples:
- `at` displays a list of all tasks that have been assigned to a person.

---

## View Unassigned People: `up`

Displays a list of all persons who have not been assigned to any task.

Format: `up`

Examples:
- `up` displays a list of all persons who have not been assigned to any task.

---

## View Unassigned Tasks: `ut`

Displays a list of all tasks that have not been assigned to any person.

Format: `ut`

Examples:
- `ut` displays a list of all tasks that have not been assigned to any person.

---

## View Assigned: `la`

Displays a list of all persons who have been assigned to a task and all tasks that have been assigned to a person.

Format: `la`

Examples:
- `la` displays a list of all persons who have been assigned to a task and all tasks that have been assigned to a person.

---

## View Unassigned: `lu`

Displays a list of all persons who have not been assigned to any task and all tasks that have not been assigned to any person.

Format: `lu`

Examples:
- `lu` displays a list of all persons who have not been assigned to any task and all tasks that have not been assigned to any person.

---


### Archiving data files `[coming in v1.5]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous OfficeConnect home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                       | Format, Examples                                                                                                                                                              |
|------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**                      | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`          |
| **Add Task**                 | `addtask title/TITLE c/CONTENT st/STATUS` <br> e.g., `addtask title/Draft proposal c/Complete proposal by 1st March st/false`                                                 |
| **Clear**                    | `clear`                                                                                                                                                                       |
| **Delete**                   | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                           |
| **Delete Task**              | `deletetask INDEX`<br/> e.g. `deletetask 2`                                                                                                                                   |
| **Edit**                     | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                |
| **Find**                     | `find NAME`<br> e.g., `find James Jake`                                                                                                                                       |
| **Find Task**                | `findtask TITLE`<br> e.g., `findtask CS2103 TP`                                                                                                                               |
| **Mark Task**                | `mark INDEX`<br/> e.g. `mark 3`                                                                                                                                               |
| **Unmark Task**              | `unmark INDEX` <br/> e.g. `unmark 2`                                                                                                                                          |
| **Help**                     | `help`                                                                                                                                                                        |
| **List**                     | `list`                                                                                                                                                                        |
| **List All**                 | `listall`                                                                                                                                                                     |
| **List Task**                | `listtask`                                                                                                                                                                    |
| **Assign Task from Person**  | `assigntask ti/INDEX p/INDEX`<br> e.g., `assigntask ti/1 p/2`                                                                                                                 |
| **Unassign Task from Person** | `ut ti/INDEX pi/INDEX`<br/> e.g. `ut ti/1 pi/2`                                                                                                                      |
| **View Assigned People**     | `ap`                                                                                                                                                                    |
| **View Assigned Tasks**      | `at`                                                                                                                                                                    |
| **View Unassigned People**   | `up`                                                                                                                                                                    |
| **View Unassigned Tasks**    | `ut`                                                                                                                                                                    |
| **View Assigned**            | `la`                                                                                                                                                                         |
| **View Unassigned**          | `lu`                                                                                                                                                                         |
