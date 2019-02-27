package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.equipment.Equipment;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddEquipmentCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddEquipmentCommand(null);
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Equipment validEquipment = new PersonBuilder().build();

        CommandResult commandResult = new AddEquipmentCommand(validEquipment).execute(modelStub, commandHistory);

        assertEquals(String.format(AddEquipmentCommand.MESSAGE_SUCCESS, validEquipment),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEquipment), modelStub.personsAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() throws Exception {
        Equipment validEquipment = new PersonBuilder().build();
        AddEquipmentCommand addEquipmentCommand = new AddEquipmentCommand(validEquipment);
        ModelStub modelStub = new ModelStubWithPerson(validEquipment);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddEquipmentCommand.MESSAGE_DUPLICATE_PERSON);
        addEquipmentCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Equipment alice = new PersonBuilder().withName("Alice").build();
        Equipment bob = new PersonBuilder().withName("Bob").build();
        AddEquipmentCommand addAliceCommand = new AddEquipmentCommand(alice);
        AddEquipmentCommand addBobCommand = new AddEquipmentCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddEquipmentCommand addAliceCommandCopy = new AddEquipmentCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different equipment -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Equipment equipment) {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Equipment equipment) {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Equipment target) {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Equipment target, Equipment editedEquipment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePerson(Equipment target, Equipment editedEquipment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Equipment> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Equipment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoAddressBook() {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAddressBook() {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Equipment> selectedPersonProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Equipment getSelectedPerson() {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedPerson(Equipment equipment) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single equipment.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Equipment equipment;

        ModelStubWithPerson(Equipment equipment) {
            requireNonNull(equipment);
            this.equipment = equipment;
        }

        @Override
        public boolean hasPerson(Equipment equipment) {
            requireNonNull(equipment);
            return this.equipment.isSamePerson(equipment);
        }
    }

    /**
     * A Model stub that always accept the equipment being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Equipment> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Equipment equipment) {
            requireNonNull(equipment);
            return personsAdded.stream().anyMatch(equipment::isSamePerson);
        }

        @Override
        public void addPerson(Equipment equipment) {
            requireNonNull(equipment);
            personsAdded.add(equipment);
        }

        @Override
        public void commitAddressBook() {
            // called by {@code AddEquipmentCommand#execute()}
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
