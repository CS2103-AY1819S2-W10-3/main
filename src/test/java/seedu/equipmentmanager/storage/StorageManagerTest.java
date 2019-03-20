package seedu.equipmentmanager.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static seedu.equipmentmanager.testutil.TypicalEquipments.getTypicalAddressBook;

import java.nio.file.Path;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import seedu.equipmentmanager.commons.core.GuiSettings;
import seedu.equipmentmanager.model.EquipmentManager;
import seedu.equipmentmanager.model.ReadOnlyEquipmentManager;
import seedu.equipmentmanager.model.UserPrefs;
import seedu.equipmentmanager.storage.JsonEquipmentManagerStorage;
import seedu.equipmentmanager.storage.JsonUserPrefsStorage;
import seedu.equipmentmanager.storage.StorageManager;

public class StorageManagerTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    private StorageManager storageManager;

    @Before
    public void setUp() {
        JsonEquipmentManagerStorage addressBookStorage = new JsonEquipmentManagerStorage(
                getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.getRoot().toPath().resolve(fileName);
    }


    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonEquipmentManagerStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonEquipmentManagerStorageTest} class.
         */
        EquipmentManager original = getTypicalAddressBook();
        storageManager.saveAddressBook(original);
        ReadOnlyEquipmentManager retrieved = storageManager.readAddressBook().get();
        assertEquals(original, new EquipmentManager(retrieved));
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getAddressBookFilePath());
    }

}
