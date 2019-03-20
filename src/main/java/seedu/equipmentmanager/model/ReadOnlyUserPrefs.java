package seedu.equipmentmanager.model;

import java.nio.file.Path;

import seedu.equipmentmanager.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();

}
