package seedu.equipmentmanager.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.equipmentmanager.commons.exceptions.DataConversionException;
import seedu.equipmentmanager.model.ReadOnlyEquipmentManager;
import seedu.equipmentmanager.model.ReadOnlyUserPrefs;
import seedu.equipmentmanager.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends EquipmentManagerStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyEquipmentManager> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyEquipmentManager addressBook) throws IOException;

}
