package seedu.equipmentmanager.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.equipmentmanager.testutil.TypicalEquipments.AMY;
import static seedu.equipmentmanager.testutil.TypicalEquipments.BOB;

import org.junit.Test;

import seedu.equipmentmanager.model.EquipmentManager;
import seedu.equipmentmanager.testutil.EquipmentManagerBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListEquipmentCommand.
 */
public class SortCommandTest {

    @Test
    public void execute_sortList() {
        EquipmentManager equipmentManager = new EquipmentManagerBuilder().withPerson(BOB).withPerson(AMY).build();
        EquipmentManager expectedEquipmentManager = new EquipmentManagerBuilder().withPerson(AMY)
                                                                                 .withPerson(BOB).build();
        equipmentManager.sortByName();
        assertEquals(equipmentManager, expectedEquipmentManager);
    }
}
