package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.EquipmentManager;
import seedu.address.model.ReadOnlyEquipmentManager;

/**
 * Represents a storage for {@link EquipmentManager}.
 */
public interface EquipmentManagerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns EquipmentManager data as a {@link ReadOnlyEquipmentManager}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyEquipmentManager> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyEquipmentManager> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyEquipmentManager} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyEquipmentManager addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyEquipmentManager)
     */
    void saveAddressBook(ReadOnlyEquipmentManager addressBook, Path filePath) throws IOException;

    void backupAddressBook(ReadOnlyEquipmentManager addressBook) throws IOException;

}
