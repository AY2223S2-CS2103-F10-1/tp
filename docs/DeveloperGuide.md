---
layout: page
title: Developer Guide
---

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## 1. Introduction

### 1.1 Product Overview

OfficeConnect is a task management tool designed specifically for managerial role personnel at companies.

The product addresses several challenges faced by managers in the current office environment, such as work overload
among subordinates, difficulties in coordinating tasks with a large number of employees, and time-consuming manual
tasks like typing and sending emails.

OfficeConnect offers a solution to these problems by providing better visibility into subordinates’ workloads, allowing
managers to efficiently delegate tasks in an organised manner. The app also automates the process of planning and
communicating with subordinates, making it easier for managers to get things done. Additionally, the app ensures that
emails are sent during working hours, so that subordinates will not be disturbed outside of work.

--------------------------------------------------------------------------------------------------------------------

## 1.2 Setting up, getting started

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

### 1.3 Acknowledgements

OfficeConnect is a brownfield Java project based on
the [AB3 project template](https://github.com/se-edu/addressbook-level3)
by [se-education.org](https://se-education.org).

* Libraries used include: [JavaFx](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson),
* [JUnit5](https://junit.org/junit5/)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S2-CS2103-F10-1/tp/tree/master/docs/diagrams) folder.
Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html)
to learn how to create and edit diagrams.
</div>

### 2.1 Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

#### 2.1.1 Main components of the architecture

**`Main`** has two classes called [`Main`](https://github.com/AY2223S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/AY2223S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/MainApp.java).
It is responsible for, At app launch: Initializes the components in the correct sequence, and connects them up with each other. At shut down: Shuts down the components and invokes cleanup methods where necessary.
[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

#### 2.1.2 How the architecture components interact with each other

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above), defines its *API* in an `interface` with the same name as the Component. implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality
using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given
component through its interface rather than the concrete class (reason: to prevent outside component's being coupled
to the implementation of a component), as illustrated in the (partial) class diagram below.


<img src="images/OfficeComponentManagers.png" width="300" />

### 2.2 UI component

The **API** of this component is specified in
[`Ui.java`](https://github.com/AY2223S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)
![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`,
`StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures
the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml`
files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,
executes user commands using the `Logic` component. listens for changes to `Model` data so that the UI can be updated with the modified data. keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands. depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### 2.3 Logic component

**API** : [`Logic.java`](https://github.com/AY2223S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is
3. executed by the `LogicManager`.
4. The command can communicate with the `Model` or `OfficeConnectModel` when it is executed (e.g. to add a person or
5. add a task or add a assignment).
6. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")`
API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser`
should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser`(`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object. All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### 2.4 Model component

**API** : [`Model.java`](https://github.com/AY2223S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="550" />

The `Model` component, stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object). stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change. stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a`ReadOnlyUserPref` objects. does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP)
model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook`
to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

### 2.5 OfficeConnectModel component

**API** : [`OfficeConnectModel.java`](https://github.com/AY2223S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/model/OfficeConnectModel.java)
<img src="images/OfficeConnectModelClassDiagram.png" width="450" />

Stores the task list data and taskAssignment data i.e., all `task` and `assignTask` objects (which are contained in a `UniqueItemList` object). Stores the currently 'selected' `Task` objects and 'AssignTask' (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Task>` and `ObservableList<AssignTask>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change. Does not depend on any of the other three components (as the `OfficeConnectModel` represents data entities of the domain, they should make sense on their own without depending on other components)

### 2.6 Storage component

**API** : [`Storage.java`](https://github.com/AY2223S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)
Original AddressBook Storage Component

<img src="images/StorageClassDiagram.png" width="550" />

OfficeConnect Storage Component
<img src="images/OfficeStorageClassDiagram.png" width="550" />
The initial storage component was specifically designed to accommodate the address book model. However, OfficeConnect
necessitates the inclusion of two additional storage types, namely task storage and assignment storage.

To address this requirement, a new generic class called RepositoryStorage has been introduced to the storage component.
This addition allows for increased extensibility and flexibility in the storage component's functionality.

The enhanced design is extendable and also capable of supporting the integration of additional databases into the
application in the future if required.
The `Storage` component, can save address book data, task list data, task assignment data and user preference data in json format, and read them back into corresponding objects. Inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed). Depends on some classes in the `Model` and `OfficeConnectModel` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model` and `OfficeConnectModel`)

### 2.7 Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## 3. Implementation

This section describes some noteworthy details on how certain features are implemented.

### 3.1 Adding a task

Syntax: `addtask t/TITLE [c/CONTENT] [st/STATUS]` </br>
Purpose: Allows users to add tasks into the OfficeConnectModel

#### 3.1.1 Implementation
The implementation of this feature is supported by `AddTaskCommand` and `AddTaskCommandParser`.
Below is a sequence diagram that illustrates how a user adds new tasks into the OfficeConnectModel. </br>

![AddTaskSequenceDiagram](images/AddTaskSequenceDiagram.png)

#### 3.1.2 Design Considerations
**Aspect: Format of inputs in Add Task Command**

* **Alternative 1 (current choice):** Only title is required when creating a task. The other fields are optional.
    * Pros: As users may not have a content or status in mind when creating new tasks, this alternative allows flexibility
  in user input, which makes the app more user-friendly. Some tasks may also be self-explanatory and thus do not
  require content descriptions.
    * Cons: More difficult to implement, more likely to cause bugs.

* **Alternative 2:** Require all fields to be compulsory
    * Pros: Easier to implement, fewer bugs may be generated.
    * Cons: Less intuitive and less user-friendly, as users who might not have a content description in mind when 
  creating tasks may be forced to key in random content to add tasks.

#### 3.1.3 Constraints:
**Title must be unique:** </br>
We felt that the title should be unique as it improves organisation and visual clarity for the user. By mandating unique 
titles, we encourage users to be specific in the title(purpose) of the task (e.g they will set title as
"Complete slides for Mr X" rather than "Complete Slides"), which will benefit them greatly, as they will be
able to clearly distinguish the purpose of each task just by looking at the title. </br>
Suppose that the title was not unique. Users might have many tasks with the same title, which would impair their ability
to distinguish between the tasks unless they read each of the task content individually. It would also impair visual
clarity when searching for tasks, as tasks with similar titles might clutter up the GUI.
Hence, our approach in mandating unique titles are geared towards improving organisation and visual clarity for users in 
both the short and long term.

### 3.2 Deleting a task
Syntax: `deletetask INDEX` </br>
Purpose: Allows users to delete the task at the specified index in the OfficeConnectModel.

#### 3.2.1 Implementation
The implementation of this feature is supported by `ListTaskCommand`, `DeleteTaskCommand` and `DeleteTaskCommandParser`.
Below are the steps required to delete a task in the OfficeConnectModel. </br>

Step 1: User keys in `listtask`, which will display the index of all tasks.
The user can thus obtain the index of the task that they want to delete.

Step 2: User keys in `deletetask INDEX` to delete the task at the specified index.
If the index is invalid, an error will be thrown.

Below is an activity diagram showcasing the 2 steps: </br>
![DeleteTaskActivityDiagram](images/DeleteTaskActivityDiagram.png)

#### 3.2.2 Design Considerations
**Aspect: Implementation of Delete Task Command**

* **Alternative 1 (current choice):** Users have to call `listtask` to find the index of the task they wish to delete.
    * Pros: Increase convenience for users, as they do not have to remember the index of each task while being easier to implement.
    * Cons: Increases coupling within OfficeConnectModel, as any bug with `listtask` could render users incapable of 
  obtaining the index needed for `deletetask`.

* **Alternative 2:** Allow users to key in the index of each task when creating tasks, after which they can
use this index when deleting tasks
    * Pros: If the user remembers the index of each task, they will not need to call `listtask`. Hence, it will be less
  troublesome for them to delete tasks as the number of steps required is reduced by one. 
  Also reduces coupling, as `deletetask` will not have to depend on `listtask` to function properly.
    * Cons: The cons of this alternative lies in the difficulty of managing indexes when adding and deleting tasks. </br>
  If the user does not keep track of the indexes they have used for previous tasks, they may have to still
  call `listtask` to find the index of the task they wish to delete or to find unused indexes to add tasks, which will not 
  give it an advantage over the first alternative. </br>
  It would also be harder to keep track of invalid indexes. When tasks are deleted, their index should be invalid. Using
  this alternative, we would have to constantly update a list of invalid indexes when adding or deleting tasks, which
  would be troublesome and could lead to bugs. In alternative 1, all indexes are flushed to the front (i.e. first task
  has index 1, second task has index 2 etc.) and thus the invalid indexes can be easily obtained.

### 3.3 Find a Person's Assigned Task
Syntax: `find [NAME]` </br>
Purpose: Allow users to search and review the list of tasks assigned to the specified person.

#### 3.3.1 Implementation
The implementation of this feature is supported by `FindCommand` and `FindCommandParser`.

Below is an activity diagram that illustrates the control flow for the Find feature. </br>

![FindTaskActivitySequenceDiagram](images/FindActivityDiagram.png)

### 3.4 Find a Task's Assignees
Syntax: `findtask [TASKNAME]` </br>
Purpose: Allow users to search and review the group of individuals assigned to the specified task.

#### 3.4.1 Implementation
The implementation of this feature is supported by `FindTaskCommand` and `FindTaskCommandParser`.

Below is an activity diagram that illustrates how a user finds who are assigned to a task. </br>

![FindTaskActivitySequenceDiagram](images/FindTaskActivitySequenceDiagram.png)

#### 3.4.2 Design Considerations

**Aspect: Form of query**

* **Alternative 1 (current choice):** Query using TASK_TITLE
    * Pros: More intuitive as users do not have to keep track of the list index of the tasks.
      Able to query for tasks that are already logged in OfficeConnect using the title of the task.
    * Cons: Length of commands are dependent on length of title. Users have to remember the name of the tasks.

* **Alternative 2:** Query using INDEX
    * Pros: Shorter command to type out.
    * Cons: Less intuitive and less user-friendly. Users would be forced to list all the tasks before being able
      to execute the findtask command if the current display is empty.

### 3.5 Edit a Task
Syntax: `edittask INDEX title/TITLE c/CONTENT st/STATUS` </br>
Purpose: Allow users to edit tasks that are currently listed in OfficeConnect.

#### 3.5.1 Implementation
The implementation of this feature is supported by `EditTaskCommand` and `EditTaskCommandParser`.

### 3.6 Help Window

Syntax: ```help```
<br>
Purpose: Provides users with a bird's eye view of the various methods usable in OfficeConnect.

#### 3.6.1 Implementation

A Help Window will be opened either by entering the `help` command, or by clicking on "Help" button in the toolbar.
The Activity Diagram below details the workflow of a user who wishes to access the HelpWindow. If the Help Window does not provide enough
information, the user can choose to view the comprehensive User Guide instead.

<img src="images/HelpActivityDiagram.png" width="330" />

#### 3.6.2 Design Considerations

**Aspect: Contents of help window**

* **Alternative 1 (Current choice):** Display as a separate window, with concise details included in the sheet.
    * Pros: Serves as a quick reference for the user, without having to go online to view the full comprehensive User Guide.
    * Cons: Major updates in command implementations will have to be updated in the help sheet too, in addition to the online User Guide.

* **Alternative 2:** Provide the user with the link to access the online User Guide.
    * Pros: More comprehensive, able to give user the complete detailing of each command.
    * Cons: Troublesome, requires user to go online to view the User Guide. User may also be overwhelmed by length of guide.

<br>

**Aspect: Viewing of various command instructions**

* **Alternative 1 (Current choice):** Display all executable commands in a hierarchical tree, with description of method selected in a separate area.
    * Pros: Providing a hierarchical structure to the list of commands available. This provides users with more ease of visualising the various methods as different groups of commands.
    * Cons: More complex structures to be used when implementing the Ui of the help window. Restructuring of tree also may be necessary when big changes are made to structure of commands.

* **Alternative 2:** Display all executable commands in a list.
    * Pros: Adding/Restructuring of commands only involves deleting/modifying the line the command is on, no resturcturing of the list needed
    * Cons: Design not too intuitive, user may need to eyeball through all the commands in order to find what he/she is looking for.

<br>

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo
history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the
following operations:

* `VersionedAddressBook#commit()`— Saves the current address book state in its history.
* `VersionedAddressBook#undo()`— Restores the previous address book state from its history.
* `VersionedAddressBook#redo()`— Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and
`Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the
initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls
`Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to
be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book
state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls
`Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution,
it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the
`addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the
`undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer`
once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at
index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore.
The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to
the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should
end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer`
once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index
`addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook
states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so,
it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as
`list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`.
Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not
pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer`
will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior
that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_
### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_

### Adding a task

Syntax: `addtask t/TITLE [c/CONTENT] [st/STATUS]` </br>
Purpose: Allows users to add tasks into the OfficeConnectModel

#### Implementation
The implementation of this feature is supported by `AddTaskCommand` and `AddTaskCommandParser`.
Below is a sequence diagram that illustrates how a user adds new tasks into the OfficeConnectModel. </br>

![AddTaskSequenceDiagram](images/AddTaskSequenceDiagram.png)

#### Design Considerations
**Aspect: Format of inputs in Add Task Command**

* **Alternative 1 (current choice):** Only title is required when creating a task. The other fields are optional.
    * Pros: As users may not have a content or status in mind when creating new tasks, this alternative allows flexibility
  in user input, which makes the app more user-friendly. Some tasks may also be self-explanatory and thus do not 
  require content descriptions.
    * Cons: More difficult to implement, more likely to cause bugs.

* **Alternative 2:** Require all fields to be compulsory
    * Pros: Easier to implement, fewer bugs may be generated.
    * Cons: Less intuitive and less user-friendly, as users who might not have a content description in mind when 
  creating tasks may be forced to key in random content to add tasks.

#### Constraints:
**Title must be unique:** </br>
We felt that the title should be unique as it improves organisation and visual clarity for the user. By mandating unique 
titles, we encourage users to be specific in the title(purpose) of the task (e.g they will set title as 
"Complete slides for Mr X" rather than "Complete Slides"), which will benefit them greatly, as they will be 
able to clearly distinguish the purpose of each task just by looking at the title. </br>
Suppose that the title was not unique. Users might have many tasks with the same title, which would impair their ability
to distinguish between the tasks unless they read each of the task content individually. It would also impair visual
clarity when searching for tasks, as tasks with similar titles might clutter up the GUI.
Hence, our approach in mandating unique titles are geared towards improving organisation and visual clarity for users in 
both the short and long term.

### Deleting a task
Syntax: `deletetask INDEX` </br>
Purpose: Allows users to delete the task at the specified index in the OfficeConnectModel.

#### Implementation
The implementation of this feature is supported by `ListTaskCommand`, `DeleteTaskCommand` and `DeleteTaskCommandParser`.
Below are the steps required to delete a task in the OfficeConnectModel. </br>

Step 1: User keys in `listtask`, which will display the index of all tasks. 
The user can thus obtain the index of the task that they want to delete.

Step 2: User keys in `deletetask INDEX` to delete the task at the specified index. 
If the index is invalid, an error will be thrown. 

Below is an activity diagram showcasing the 2 steps: </br> 
![DeleteTaskActivityDiagram](images/DeleteTaskActivityDiagram.png)

#### Design Considerations
**Aspect: Implementation of Delete Task Command**

* **Alternative 1 (current choice):** Users have to call `listtask` to find the index of the task they wish to delete.
    * Pros: Increase convenience for users, as they do not have to remember the index of each task. Also easier to implement.
    * Cons: Increases coupling within OfficeConnectModel, as any bug with `listtask` could render users incapable of 
  obtaining the index needed for `deletetask`.

* **Alternative 2:** Allow users to key in the index of each task when creating tasks, after which they can 
use this index when deleting tasks
    * Pros: If the user remembers the index of each task, they will not need to call `listtask`. Hence, it will be less
  troublesome for them to delete tasks as the number of steps required is reduced by one. 
  Also reduces coupling, as `deletetask` will not have to depend on `listtask` to function properly.
    * Cons: The cons of this alternative lies in the difficulty of managing indexes when adding and deleting tasks. </br>
  If the user does not keep track of the indexes they have used for previous tasks, they may have to still 
  call `listtask` to find the index of the task they wish to delete or to find unused indexes to add tasks, which will not 
  give it an advantage over the first alternative. </br>
  It would also be harder to keep track of invalid indexes. When tasks are deleted, their index should be invalid. Using
  this alternative, we would have to constantly update a list of invalid indexes when adding or deleting tasks, which 
  would be troublesome and could lead to bugs. In alternative 1, all indexes are flushed to the front (i.e first task
  has index 1, second task has index 2 etc) and thus the invalid indexes can be easily obtained.

## Unassigning a Task from a Person

**Syntax:** `unassign pi/PERSON_INDEX ti/TASK_INDEX`  
**Purpose:** Allows users to unassign a task from a person.

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/UnassignActivity.png" width="550" />

Below is a sequence diagram that illustrates how a user unassign a tasks from a person in the OfficeConnectModel. 

![AddTaskSequenceDiagram](images/UnassignSequenceDiagram.png)
### Implementation

The implementation of this feature is supported by `UnassignTaskCommand` and `UnassignTaskCommandParser`.

### Design Considerations

**Aspect: Unassigning a task from a person**

- **Alternative 1 (current choice):** Unassign tasks using the index of the person and the index of the task.
    - Pros: Easier for users to locate the task and person they want to unassign, especially if the list of tasks or persons is long.
    - Cons: Requires users to first obtain the index of the task and person by listing them, which might increase the steps required for the user.

- **Alternative 2:** Unassign tasks using the task title and person name.
    - Pros: No need to obtain the index of the task and person, which could reduce the steps required for the user.
    - Cons: Task titles and person names might be long, making it more difficult for users to input the command. There could also be issues with names that are not unique.


--------------------------------------------------------------------------------------------------------------------

## 4. Documentation, logging, testing, configuration, dev-ops

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)
--------------------------------------------------------------------------------------------------------------------

## 5. Appendix: Requirements

### 5.1 Product scope

**Target user profile**:
* Holds a managerial role
* Has a need to manage a significant number of subordinates
* Has a need to assign large number of tasks to subordinates
* Prefer desktop apps over other types
* Can type fast
* Prefers typing to mouse interactions
* Is reasonably comfortable using CLI apps

**Value proposition**:

* Manage tasks and contacts faster than a typical mouse/GUI driven app
* Able to view all upcoming tasks to be completed at one glance
* Allows efficient delegation of tasks to subordinates in an organised and centralised manner

### 5.2 User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                                | So that I can…​                                                        |
|----------|--------------------------------------------|---------------------------------------------|------------------------------------------------------------------------|
| `* * *`  | new user                                   | see usage instructions                      | refer to instructions when I forget how to use the App                 |
| `* * *`  | manager                                    | add tasks                                   |                                                                        |
| `* * *`  | manager                                    | edit tasks                                  | keep the task updated with the most updated information                |
| `* * *`  | manager                                    | delete tasks                                | remove tasks that I no longer need                                     |
| `* * *`  | manager                                    | find tasks assigned to specific subordinate | better manage my subordinates workload                                 |
| `* * *`  | manager                                    | check all ongoing tasks available           | better delegate my tasks                                               |
| `* * *`  | manager                                    | assign tasks to a subordinate               | keep track of which subordinate is in charge of which task             |
| `* * *`  | manager                                    | unassign tasks from a subordinate           | assign this task to other subordinate                                  |
| `* * *`  | manager                                    | add a subordinate                           |                                                                        |
| `* * *`  | manager                                    | delete a subordinate                        | remove subordinates that I no longer need                              |
| `* * *`  | manager                                    | find a subordinate by name                  | locate details of persons without having to go through the entire list |
| `* *`    | user                                       | hide private contact details                | minimize chance of someone else seeing them by accident                |
| `*`      | user with many persons in the address book | sort persons by name                        | locate a person easily                                                 |

### 5.3 Acceptance Criteria

- When I use the "add" command followed by a task name, a new task with the given name should be added to my task list.
- The task name should be displayed in the task list.
- The task should be saved to persistent storage so that it is not lost when I exit the app.

#### 5.3.1 Example Usage

> addtask s/Draft proposal c/Complete proposal by 1st March st/false

- New task added: Draft proposal; Status: Undone; Content: Complete proposal by 1st March

> listtask

1. Finish report; Status: Undone; Content: Complete report for Mr Chan by 2nd March
2. Send email to team; Status: Done; Content: Send an email regarding office hours

> findtask report

1. Finish report

> deletetask 1

- Deleted task: Draft proposal; Status: Undone; Content: Complete proposal by 1st March

> bye

- Goodbye!

In the example above, the user story is clearly defined at the top, followed by the acceptance criteria for the feature.
Below that, we have a series of example usages, shown in a code editor block to provide clarity and context for how the
feature would work in practice.

*{More to be added}*

### 5.4 Use cases

<div markdown="span" class="alert alert-info">

:information_source: **Note:** For all use cases below, the **System** is the `OfficeConnect` and the
**Actor** is the `user`, unless specified otherwise.

</div>

---
#### 5.4.1 Use case 1: Add a person

**Main Success Scenario (MSS):**

1. User requests to add a person.

2. OfficeConnect adds the person to the contact list.

3. OfficeConnect informs user has been successfully added.

   Use case ends.

**Extensions**

* 1a. User enters incomplete or invalid data.

    * 1a1. OfficeConnect shows an error message.

      Use case ends.

---
#### 5.4.2 Use case 2: Delete a person

**Main Success Scenario (MSS):**

1. User requests to list persons.

2. OfficeConnect shows a list of persons.

3. User requests to delete a specific person in the list.

4. OfficeConnect deletes the person.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. OfficeConnect shows an error message.

      Use case resumes at step 2.

---
#### 5.4.3 Use case 3: Delete a task

**Main Success Scenario (MSS):**

1. User requests to list tasks.

2. OfficeConnect shows a list of tasks.

3. User requests to delete a specific task in the list.

4. OfficeConnect deletes the task.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. OfficeConnect shows an error message.

      Use case resumes at step 2.

---
#### 5.4.4 Use case 4: Assign a task to a person

**Main Success Scenario (MSS):**

1. User requests to list persons.

2. OfficeConnect shows a list of persons.

3. User requests to list tasks.

4. OfficeConnect shows a list of tasks.

5. User requests to assign a specific task to a specific person.

6. OfficeConnect assigns the task to the person.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 4a. The list is empty.

  Use case ends.

* 5a. The given index is invalid.

    * 5a1. OfficeConnect shows an error message.

      Use case resumes at step 2. 

---
#### 5.4.5 Use case 5: Remove assignment of task from a person

**Main Success Scenario (MSS):**

1. User requests to list persons.

2. OfficeConnect shows a list of persons.

3. User requests to list tasks.

4. OfficeConnect shows a list of tasks.

5. User requests to unassign a specific task from a specific person.

6. OfficeConnect unassign the task from the person.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 4a. The list is empty.

  Use case ends.

* 5a. The given index is invalid.

    * 5a1. OfficeConnect shows an error message.

      Use case resumes at step 2.

**Below is an activity diagram showcasing removing assignment of task from a person:** </br>
<img src="images/UnassignActivity.png" width="250" />

---

#### 5.4.6 Use case 6: Consulting Help Window

**Main Success Scenario (MSS):**

1. User requests for help.

2. OfficeConnect opens help interface with dedicated help instructions.

3. User specifies command he wishes to get help on.

4. OfficeConnect displays how command works, along with format of queried command to user.

5. User reads closes window after getting required info.

   Use case ends.

**Extensions**

* 2a. User requests for more info on command.

    * 2a1. Help interface provides link to user guide.

    * 2a2. User retrieves user guide providing more detail on command.

      Use case resumes at step 5.

---
#### 5.4.7 Use case 7: Add a task

**Main Success Scenario (MSS):**

1. User requests to add a task.

2. OfficeConnect adds the task to the task list.

3. OfficeConnect informs user has been successfully added.

   Use case ends.

**Extensions**

* 1a. User enters incomplete or invalid data.

    * 1a1. OfficeConnect shows an error message.

      Use case ends.

---
#### 5.4.8 Use case 8: List all tasks

**Main Success Scenario (MSS):**

1. User requests for a list of all tasks.

2. OfficeConnect displays all tasks stored.

   Use case ends.

---
#### 5.4.9 Use case 9: Mark a tasks

**Main Success Scenario (MSS):**

1. User requests for a list of all tasks.

2. OfficeConnect displays all tasks stored.

3. User requests to mark a specific task.

4. OfficeConnect marks the task as completed.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. OfficeConnect shows an error message.

      Use case resumes at step 2.

* 3b. The task at the given index is already marked as completed.

    * 3b1. OfficeConnect shows an error message.

      Use case ends.

---
#### 5.4.10 Use case 10: Unmark a tasks

**Main Success Scenario (MSS):**

1. User requests for a list of all tasks.

2. OfficeConnect displays all tasks stored.

3. User requests to unmark a specific task.

4. OfficeConnect unmarks the task as uncompleted.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. OfficeConnect shows an error message.

      Use case resumes at step 2.

* 3b. The task at the given index is already marked as uncompleted.

    * 3b1. OfficeConnect shows an error message.

      Use case ends.

---
#### 5.4.11 Use case 11: Find tasks assigned to an individual

**Main Success Scenario (MSS):**

1. User requests for the list of tasks assigned to a specific person.

2. OfficeConnect displays all the tasks assigned to the person.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

---
#### 5.4.12 Use case 12: Find the group of individuals assigned to a task

**Main Success Scenario (MSS):**

1. User requests for the list of persons assigned to a specific task.

2. OfficeConnect displays all the individuals assigned to the task.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

### Non-Functional Requirements

1. Performance: The system shall respond to user input within 2 seconds, even under peak load conditions.
2. Maintainability: The system shall be designed to allow for easy maintenance and updates, with clear documentation
   and modular architecture.
3. Compatibility: The system shall be compatible to operating systems with java 11 runtime (e.g. Windows, MacOS, Linux).
4. Interoperability: The system shall be able to exchange data with other systems using standard formats and protocols.
5. Usability: The system shall have a user interface that is intuitive and easy to use, with a learning curve of no more
   than 2 hours for a new user.

### Glossary

1. Unassign: remove assignment of task from the person.

#### *M*

* **Mainstream OS**: Windows, Linux, Unix, OS-X

#### *P*

* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting
point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### 6.1 Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder 
   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be
      optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window. 
   2. Re-launch the app by double-clicking the jar file.<br>
      Expected: The most recent window size and location is retained.

3_{ more test cases …​ }_

### 6.2 Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list. 
   2. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.
      Timestamp in the status bar is updated. 
   3. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same. 
   4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

2. _{ more test cases …​ }_

### 6.3 Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

2. _{ more test cases …​ }_
