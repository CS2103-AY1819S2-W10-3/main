package seedu.address.model.equipment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalEquipments.ACHORVALECC;
import static seedu.address.testutil.TypicalEquipments.BOB;

//import java.util.Arrays;
//import java.util.Collections;

import java.util.LinkedList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.equipment.exceptions.DuplicateEquipmentException;
import seedu.address.model.equipment.exceptions.EquipmentNotFoundException;
//import seedu.address.testutil.EquipmentBuilder;

public class UniqueWorkListListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final WorkList typicalWorkListA = new WorkList("2019-01-01", "Alice");
    private final WorkList typicalWorkListB = new WorkList("2018-01-01", "Bob");

    private final List<WorkList> listOfWorklist = new LinkedList<>();

    private final UniqueWorkListList uniqueWorkListList = new UniqueWorkListList();

    {
        typicalWorkListA.addEquipment(ACHORVALECC);
        typicalWorkListB.addEquipment(BOB);
        listOfWorklist.add(typicalWorkListA);
    }

<<<<<<< HEAD
=======
    private final UniqueWorkListList UniqueWorkListList = new UniqueWorkListList();

>>>>>>> list-w command and create some test files.
    @Test
    public void contains_nullWorkList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        UniqueWorkListList.contains(null);
    }

    @Test
    public void contains_workListNotInList_returnsFalse() {
        assertFalse(UniqueWorkListList.contains(typicalWorkListA));
    }

    @Test
    public void contains_workListInList_returnsTrue() {
        UniqueWorkListList.add(typicalWorkListA);
        assertTrue(UniqueWorkListList.contains(typicalWorkListA));
    }

    @Test
    public void canDoSortById() {
        uniqueWorkListList.sortById();
        assertTrue(true);
    }

    @Test
    public void workListAreUniqueOrNot() {
        assertTrue(uniqueWorkListList.areWorkListUnique(listOfWorklist));
    }

    @Test
    public void add_nullWorkList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        UniqueWorkListList.add(null);
    }

    @Test
    public void add_duplicateWorkList_throwsDuplicateWorkListException() {
        UniqueWorkListList.add(typicalWorkListA);
        thrown.expect(DuplicateEquipmentException.class);
        UniqueWorkListList.add(typicalWorkListA);
    }

    @Test
    public void remove_nullWorkList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        UniqueWorkListList.remove(null);
    }

    @Test
    public void remove_workListDoesNotExist_throwsWorkListNotFoundException() {
        thrown.expect(EquipmentNotFoundException.class);
        UniqueWorkListList.remove(typicalWorkListA);
    }

    @Test
    public void remove_existingWorkList_removesWorkList() {
<<<<<<< HEAD
        uniqueWorkListList.add(typicalWorkListA);
        uniqueWorkListList.remove(typicalWorkListA);
        UniqueWorkListList expectedUniqueWorkListList = new UniqueWorkListList();
        assertEquals(expectedUniqueWorkListList, uniqueWorkListList);
=======
        UniqueWorkListList.add(typicalWorkListA);
        UniqueWorkListList.remove(typicalWorkListA);
        UniqueWorkListList expectedUniqueWorkListList = new UniqueWorkListList();
        assertEquals(expectedUniqueWorkListList, UniqueWorkListList);
>>>>>>> list-w command and create some test files.
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        UniqueWorkListList.asUnmodifiableObservableList().remove(0);
    }
}
