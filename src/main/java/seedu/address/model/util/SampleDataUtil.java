package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
<<<<<<< HEAD
import seedu.address.model.person.*;
=======
import seedu.address.model.ReadOnlyRepository;
import seedu.address.model.Repository;
import seedu.address.model.mapping.PersonTask;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.shared.Id;
>>>>>>> c74a83152ed2ee5c806c6fc35171635eeef79a12
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Content;
import seedu.address.model.task.Status;
import seedu.address.model.task.Subject;
import seedu.address.model.task.Task;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
<<<<<<< HEAD
                new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK,
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_REMARK,
                getTagSet("colleagues", "friends")),
=======
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), new Id("f26605e3-613d-4d6b-946c-43e496f07f76")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), new Id("c916334d-0b63-4dbd-9372-e553116b3892")),
>>>>>>> c74a83152ed2ee5c806c6fc35171635eeef79a12
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_REMARK,
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_REMARK,
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
                getTagSet("colleagues"))
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Subject("Task 1"), new Content("Content 1"), new Status(true),
                new Id("c916334d-1111-4dbd-9372-e553116b3892")),
            new Task(new Subject("Task 2"), new Content("Content 2"), new Status(false),
                new Id("a716334d-0000-4dbd-9372-e553116b3893"))
        };
    }

    public static PersonTask[] getSamplePersonTask() {
        return new PersonTask[] {
            new PersonTask(new Id("f26605e3-613d-4d6b-946c-43e496f07f76"),
                new Id("c916334d-1111-4dbd-9372-e553116b3892")),
            new PersonTask(new Id("c916334d-0b63-4dbd-9372-e553116b3892"),
                new Id("a716334d-0000-4dbd-9372-e553116b3893")),
        };
    }

    public static ReadOnlyRepository<Task> getSampleTasksRepo() {
        Repository<Task> sampleTasksRepo = new Repository<>();
        for (Task sampleTask : getSampleTasks()) {
            sampleTasksRepo.addItem(sampleTask);
        }
        return sampleTasksRepo;
    }

    public static ReadOnlyRepository<PersonTask> getSamplePersonTasksRepo() {
        Repository<PersonTask> samplePersonTasksRepo = new Repository<>();
        for (PersonTask personTask : getSamplePersonTask()) {
            samplePersonTasksRepo.addItem(personTask);
        }
        return samplePersonTasksRepo;
    }


    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
