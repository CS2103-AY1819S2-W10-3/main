package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.address.commons.util.InvalidationListenerManager;
import seedu.address.model.equipment.Equipment;
import seedu.address.model.equipment.UniquePersonList;
import seedu.address.model.tag.Tag;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the equipment list with {@code equipment}.
     * {@code equipment} must not contain duplicate equipment.
     */
    public void setPersons(List<Equipment> equipment) {
        this.persons.setPersons(equipment);
        indicateModified();
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// equipment-level operations

    /**
     * Returns true if a equipment with the same identity as {@code equipment} exists in the address book.
     */
    public boolean hasPerson(Equipment equipment) {
        requireNonNull(equipment);
        return persons.contains(equipment);
    }

    /**
     * Adds a equipment to the address book.
     * The equipment must not already exist in the address book.
     */
    public void addPerson(Equipment p) {
        persons.add(p);
        indicateModified();
    }

    /**
     * Replaces the given equipment {@code target} in the list with {@code editedEquipment}.
     * {@code target} must exist in the address book.
     * The equipment identity of {@code editedEquipment} must not be the same as another existing equipment in the address book.
     */
    public void setPerson(Equipment target, Equipment editedEquipment) {
        requireNonNull(editedEquipment);

        persons.setPerson(target, editedEquipment);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Equipment key) {
        persons.remove(key);
        indicateModified();
    }

    /**
     * Replaces the given equipment {@code target} in the list with {@code editedEquipment}.
     * {@code target} must exist in the address book.
     * The equipment identity of {@code editedEquipment} must not be the same as another existing equipment in the address book.
     */
    public void updatePerson(Equipment target, Equipment editedEquipment) {
        requireNonNull(editedEquipment);

        persons.setPerson(target, editedEquipment);
    }

    /**
     * Removes {@code tag} from {@code equipment} in this {@code AddressBook}.
     */
    private void removeTagFromPerson(Tag tag, Equipment equipment) {
        Set<Tag> newTags = new HashSet<>(equipment.getTags());

        if (!newTags.remove(tag)) {
            return;
        }

        Equipment newEquipment =
                new Equipment(equipment.getName(), equipment.getPhone(), equipment.getEmail(), equipment.getAddress(), newTags);

        updatePerson(equipment, newEquipment);
    }

    /**
     * Removes {@code tag} from all persons in this {@code AddressBook}.
     */
    public void removeTag(Tag tag) {
        persons.forEach(person -> removeTagFromPerson(tag, person));
    }

    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListenerManager.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the address book has been modified.
     */
    protected void indicateModified() {
        invalidationListenerManager.callListeners(this);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Equipment> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
