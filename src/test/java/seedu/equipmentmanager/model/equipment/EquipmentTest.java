package seedu.equipmentmanager.model.equipment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.equipmentmanager.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.equipmentmanager.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.equipmentmanager.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.equipmentmanager.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
//import static seedu.equipmentmanager.logic.commands.CommandTestUtil.VALID_SERIAL_NUMBER_AMY;
import static seedu.equipmentmanager.logic.commands.CommandTestUtil.VALID_SERIAL_NUMBER_BOB;
import static seedu.equipmentmanager.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.equipmentmanager.testutil.TypicalEquipments.ANCHORVALECC;
import static seedu.equipmentmanager.testutil.TypicalEquipments.BOB;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.equipmentmanager.testutil.EquipmentBuilder;
import seedu.equipmentmanager.model.equipment.Equipment;

public class EquipmentTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Equipment equipment = new EquipmentBuilder().build();
        thrown.expect(UnsupportedOperationException.class);
        equipment.getTags().remove(0);
    }

    @Test
    public void isSameEquipment() {
        // same object -> returns true
        assertTrue(ANCHORVALECC.isSameEquipment(ANCHORVALECC));

        // null -> returns false
        assertFalse(ANCHORVALECC.isSameEquipment(null));

        // same name and other attributes except serial number -> return False
        Equipment editedAlice = new EquipmentBuilder(ANCHORVALECC).withSerialNumber(VALID_SERIAL_NUMBER_BOB).build();
        assertFalse(ANCHORVALECC.isSameEquipment(editedAlice));

        // same serial number, same name, different attributes -> return true
        editedAlice = new EquipmentBuilder(ANCHORVALECC).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ANCHORVALECC.isSameEquipment(editedAlice));

        // same serial number, same email, different attributes -> return true
        editedAlice = new EquipmentBuilder(ANCHORVALECC).withName(VALID_NAME_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ANCHORVALECC.isSameEquipment(editedAlice));

        // same serial number, same equipmentmanager, different attributes -> return true
        editedAlice = new EquipmentBuilder(ANCHORVALECC).withName(VALID_NAME_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ANCHORVALECC.isSameEquipment(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Equipment aliceCopy = new EquipmentBuilder(ANCHORVALECC).build();
        assertTrue(ANCHORVALECC.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ANCHORVALECC.equals(ANCHORVALECC));

        // null -> returns false
        assertFalse(ANCHORVALECC.equals(null));

        // different type -> returns false
        assertFalse(ANCHORVALECC.equals(5));

        // different equipment -> returns false
        assertFalse(ANCHORVALECC.equals(BOB));

        // different name -> returns false
        Equipment editedAlice = new EquipmentBuilder(ANCHORVALECC).withName(VALID_NAME_BOB).build();
        assertFalse(ANCHORVALECC.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new EquipmentBuilder(ANCHORVALECC).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ANCHORVALECC.equals(editedAlice));

        // different email -> returns false
        editedAlice = new EquipmentBuilder(ANCHORVALECC).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ANCHORVALECC.equals(editedAlice));

        // different equipmentmanager -> returns false
        editedAlice = new EquipmentBuilder(ANCHORVALECC).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ANCHORVALECC.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new EquipmentBuilder(ANCHORVALECC).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ANCHORVALECC.equals(editedAlice));
    }
}
