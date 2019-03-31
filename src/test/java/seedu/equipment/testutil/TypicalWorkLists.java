package seedu.equipment.testutil;

import static seedu.equipment.logic.commands.CommandTestUtil.ASSIGNEE_DESC_LISTA;
import static seedu.equipment.logic.commands.CommandTestUtil.DATE_DESC_LISTA;
//import static seedu.equipment.testutil.TypicalEquipments.ANCHORVALECC;
//import static seedu.equipment.testutil.TypicalEquipments.BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.equipment.model.EquipmentManager;
import seedu.equipment.model.WorkList;

/**
 * A utility class containing a list of {@code WorkList} objects to be used in tests.
 */
public class TypicalWorkLists {

    public static final WorkList LISTA = new WorkListBuilder().withDate("01 January 2019")
            .withAssignee("Mei Yen").build();
    public static final WorkList LISTB = new WorkListBuilder().withDate("01 January 2018")
            .withAssignee("Bob").build();
    public static final WorkList LISTC = new WorkListBuilder().withDate(DATE_DESC_LISTA)
            .withAssignee(ASSIGNEE_DESC_LISTA).build();
    //{
    //    LISTA.addEquipment(ANCHORVALECC);
    //   LISTB.addEquipment(BOB);
    //}

    private TypicalWorkLists() {} //prevents instantiation

    /**
     * Returns an {@code EquipmentManager} with all the typical persons.
     */
    public static EquipmentManager getTypicalEquipmentManager() {
        EquipmentManager em = new EquipmentManager();
        for (WorkList worklist : getTypicalWorkLists()) {
            em.addWorkList(worklist);
        }
        return em;
    }

    public static List<WorkList> getTypicalWorkLists() {
        return new ArrayList<>(Arrays.asList(LISTA, LISTB));
    }
}
