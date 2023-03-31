package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_NO_PERSONS;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;


/**
 * Finds all persons with the assigned tag.
 * Keyword matching is case-insensitive.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filterp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": displays all persons with the assigned tag. Only "
            + "one tag should be specified. "
            + "Parameters: "
            + PREFIX_TAG + "TAG "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "Logistics ";

    public static final String MESSAGE_SUCCESS = "Filtered all persons with the tag: %1$s";
    public static final String MESSAGE_NO_PERSON_WITH_TAG = "There are no persons with the tag: %1$s";

    private final Tag tag;

    /**
     * Creates a FilterCommand object that filters persons according to the specified {@code Tag }.
     */
    public FilterCommand(Tag tag) {
        requireNonNull(tag);
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) {
        requireAllNonNull(model, officeConnectModel);
        List<Person> uniquePersonsList = model.getAddressBook().getPersonList();
        Set<Person> personsWithTag = uniquePersonsList.stream().filter(x ->
                x.getTags().contains(tag)).collect(Collectors.toSet());
        if (personsWithTag.isEmpty()) {
            model.updateFilteredPersonList(PREDICATE_SHOW_NO_PERSONS);
            return new CommandResult(String.format(MESSAGE_NO_PERSON_WITH_TAG, tag));
        }
        Predicate<Person> predicate = x -> personsWithTag.contains(x);
        model.updateFilteredPersonList(predicate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && this.tag.equals(((FilterCommand) other).tag)); // state check
    }

    @Override
    public String toString() {
        return tag.toString();
    }

}
