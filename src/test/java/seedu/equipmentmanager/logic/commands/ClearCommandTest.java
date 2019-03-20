package seedu.equipmentmanager.logic.commands;

import static seedu.equipmentmanager.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.equipmentmanager.testutil.TypicalEquipments.getTypicalAddressBook;

import org.junit.Test;

import seedu.equipmentmanager.logic.CommandHistory;
import seedu.equipmentmanager.model.EquipmentManager;
import seedu.equipmentmanager.model.Model;
import seedu.equipmentmanager.model.ModelManager;
import seedu.equipmentmanager.model.UserPrefs;

public class ClearCommandTest {

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.commitEquipmentManager();

        assertCommandSuccess(new ClearCommand(), model, commandHistory, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setEquipmentManager(new EquipmentManager());
        expectedModel.commitEquipmentManager();

        assertCommandSuccess(new ClearCommand(), model, commandHistory, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
