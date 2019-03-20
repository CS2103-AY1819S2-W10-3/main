package seedu.equipmentmanager.logic;

import java.nio.file.Path;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.equipmentmanager.commons.core.GuiSettings;
import seedu.equipmentmanager.logic.commands.CommandResult;
import seedu.equipmentmanager.logic.commands.exceptions.CommandException;
import seedu.equipmentmanager.logic.parser.exceptions.ParseException;
import seedu.equipmentmanager.model.Model;
import seedu.equipmentmanager.model.ReadOnlyEquipmentManager;
import seedu.equipmentmanager.model.equipment.Equipment;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the EquipmentManager.
     *
     * @see Model#getAddressBook()
     */
    ReadOnlyEquipmentManager getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Equipment> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the list of commands entered by the user.
     * The list is ordered from the least recent command to the most recent command.
     */
    ObservableList<String> getHistory();

    /**
     * Returns the user prefs' equipmentmanager book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Selected equipment in the filtered equipment list.
     * null if no equipment is selected.
     *
     * @see Model#selectedPersonProperty()
     */
    ReadOnlyProperty<Equipment> selectedPersonProperty();

    /**
     * Sets the selected equipment in the filtered equipment list.
     *
     * @see Model#setSelectedPerson(Equipment)
     */
    void setSelectedPerson(Equipment equipment);
}
