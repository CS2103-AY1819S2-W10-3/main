package seedu.equipmentmanager.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.equipmentmanager.testutil.TypicalEquipments.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.equipmentmanager.commons.core.Messages;
import seedu.equipmentmanager.logic.CommandHistory;
import seedu.equipmentmanager.logic.commands.CommandResult;
import seedu.equipmentmanager.logic.commands.DisplayCommand;
import seedu.equipmentmanager.model.Model;
import seedu.equipmentmanager.model.ModelManager;
import seedu.equipmentmanager.model.UserPrefs;

public class DisplayCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_displayCommandExecuteSuccessful() {
        CommandHistory expectedCommandHistory = new CommandHistory(commandHistory);
        CommandResult result = new DisplayCommand().execute(model, commandHistory);
        CommandResult expectedCommandResult = new CommandResult(
                Messages.MESSAGE_EQUIPMENT_DISPLAYED_OVERVIEW, false, false, true);
        assertEquals(expectedCommandResult.toString(), result.toString());
        assertEquals(expectedModel, model);
        assertEquals(expectedCommandHistory, commandHistory);
    }
}
