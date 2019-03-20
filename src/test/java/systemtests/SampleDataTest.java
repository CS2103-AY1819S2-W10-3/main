package systemtests;

<<<<<<< HEAD
import static seedu.equipmentmanager.ui.testutil.GuiTestAssert.assertListMatching;
=======
import static seedu.equipment.ui.testutil.GuiTestAssert.assertListMatching;
>>>>>>> 166fafa10330a5bb1b0f3f2671f0c6e4d51aee61

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

<<<<<<< HEAD
import seedu.equipmentmanager.model.EquipmentManager;
import seedu.equipmentmanager.model.equipment.Equipment;
import seedu.equipmentmanager.model.util.SampleDataUtil;
import seedu.equipmentmanager.testutil.TestUtil;
=======
import seedu.equipment.model.EquipmentManager;
import seedu.equipment.model.equipment.Equipment;
import seedu.equipment.model.util.SampleDataUtil;
import seedu.equipment.testutil.TestUtil;
>>>>>>> 166fafa10330a5bb1b0f3f2671f0c6e4d51aee61

public class SampleDataTest extends EquipmentManagerSystemTest {
    /**
     * Returns null to force test app to load data of the file in {@code getDataFileLocation()}.
     */
    @Override
    protected EquipmentManager getInitialData() {
        return null;
    }

    /**
     * Returns a non-existent file location to force test app to load sample data.
     */
    @Override
    protected Path getDataFileLocation() {
        Path filePath = TestUtil.getFilePathInSandboxFolder("SomeFileThatDoesNotExist1234567890.xml");
        deleteFileIfExists(filePath);
        return filePath;
    }

    /**
     * Deletes the file at {@code filePath} if it exists.
     */
    private void deleteFileIfExists(Path filePath) {
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException ioe) {
            throw new AssertionError(ioe);
        }
    }

    @Test
    public void addressBook_dataFileDoesNotExist_loadSampleData() {
        Equipment[] expectedList = SampleDataUtil.getSamplePersons();
        assertListMatching(getPersonListPanel(), expectedList);
    }
}
