package seedu.equipmentmanager.logic.commands;

import static seedu.equipmentmanager.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.equipmentmanager.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.equipmentmanager.testutil.TypicalEquipments.getTypicalAddressBook;
//import static seedu.equipmentmanager.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.Before;
import org.junit.Test;

import seedu.equipmentmanager.logic.CommandHistory;
import seedu.equipmentmanager.logic.commands.ListWorkListCommand;
import seedu.equipmentmanager.model.Model;
import seedu.equipmentmanager.model.ModelManager;
import seedu.equipmentmanager.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListWorkListCommand.
 */
public class ListWorkListCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListWorkListCommand(), model, commandHistory,
                ListWorkListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
