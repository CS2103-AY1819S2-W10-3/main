package seedu.equipment.ui;

import static org.junit.Assert.assertEquals;
import static seedu.equipment.testutil.TypicalEquipments.ANCHORVALECC;
import static seedu.equipment.ui.StatusBarFooter.SYNC_STATUS_INITIAL;
import static seedu.equipment.ui.StatusBarFooter.SYNC_STATUS_UPDATED;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import guitests.guihandles.StatusBarFooterHandle;
import seedu.equipment.model.EquipmentManager;
import seedu.equipment.ui.StatusBarFooter;

public class StatusBarFooterTest extends GuiUnitTest {

    private static final Path STUB_SAVE_LOCATION = Paths.get("Stub");
    private static final Path RELATIVE_PATH = Paths.get(".");

    private static final Clock originalClock = StatusBarFooter.getClock();
    private static final Clock injectedClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

    private StatusBarFooterHandle statusBarFooterHandle;
    private final EquipmentManager equipmentManager = new EquipmentManager();

    @BeforeClass
    public static void setUpBeforeClass() {
        // inject fixed clock
        StatusBarFooter.setClock(injectedClock);
    }

    @AfterClass
    public static void tearDownAfterClass() {
        // restore original clock
        StatusBarFooter.setClock(originalClock);
    }

    @Before
    public void setUp() {
        StatusBarFooter statusBarFooter = new StatusBarFooter(STUB_SAVE_LOCATION, equipmentManager);
        uiPartRule.setUiPart(statusBarFooter);

        statusBarFooterHandle = new StatusBarFooterHandle(statusBarFooter.getRoot());
    }

    @Test
    public void display() {
        // initial state
        assertStatusBarContent(RELATIVE_PATH.resolve(STUB_SAVE_LOCATION).toString(), SYNC_STATUS_INITIAL);

        // after equipment book is updated
        guiRobot.interact(() -> equipmentManager.addPerson(ANCHORVALECC));
        assertStatusBarContent(RELATIVE_PATH.resolve(STUB_SAVE_LOCATION).toString(),
                String.format(SYNC_STATUS_UPDATED, new Date(injectedClock.millis()).toString()));
    }

    /**
     * Asserts that the save location matches that of {@code expectedSaveLocation}, and the
     * sync status matches that of {@code expectedSyncStatus}.
     */
    private void assertStatusBarContent(String expectedSaveLocation, String expectedSyncStatus) {
        assertEquals(expectedSaveLocation, statusBarFooterHandle.getSaveLocation());
        assertEquals(expectedSyncStatus, statusBarFooterHandle.getSyncStatus());
        guiRobot.pauseForHuman();
    }

}
