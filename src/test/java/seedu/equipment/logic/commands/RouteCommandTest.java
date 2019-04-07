package seedu.equipment.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static seedu.equipment.testutil.TypicalEquipments.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.equipment.logic.CommandHistory;
import seedu.equipment.logic.commands.exceptions.CommandException;
import seedu.equipment.model.Model;
import seedu.equipment.model.ModelManager;
import seedu.equipment.model.UserPrefs;
import seedu.equipment.model.equipment.Address;
import seedu.equipment.model.equipment.Equipment;
import seedu.equipment.testutil.EquipmentBuilder;

public class RouteCommandTest {

    private Model model;
    private Model expectedModel;
    private Model emptyModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        emptyModel = new ModelManager();
        expectedModel = new ModelManager(model.getEquipmentManager(), new UserPrefs());
    }

    @Test
    public void execute_routeCommandExecuteSuccessful() {
        try {
            CommandHistory expectedCommandHistory = new CommandHistory(commandHistory);
            Address testAddress = new Address("School of computing, NUS, Singapore");
            CommandResult result = new RouteCommand(testAddress).execute(model, commandHistory);
            CommandResult expectedCommandResult = new CommandResult(
                    RouteCommand.MESSAGE_ROUTE_EQUIPMENT_SUCCESS, false, false, false, true, testAddress);
            assertEquals(expectedCommandResult.toString(), result.toString());
            assertEquals(expectedModel, model);
            assertEquals(expectedCommandHistory, commandHistory);
        } catch (CommandException ce) {
            fail("NO invalid command exception should be thrown.");
        }
    }

    @Test
    public void execute_routeCommandExecuteNoEquipmentExceptions() {
        try {
            Address testAddress = new Address("School of computing, NUS, Singapore");
            CommandResult result = new RouteCommand(testAddress).execute(emptyModel, commandHistory);
            fail("Routing with empty equipment list should raise exception.");
        } catch (CommandException ce) {
            if (ce.getMessage() != RouteCommand.MESSAGE_NO_EQUIPMENTS_TO_ROUTE) {
                fail("Should display no equipment message to user.");
            }
        }
    }

    @Test
    public void execute_routeCommandExecuteTooManyEquipmentExceptions() {
        try {
            Address testAddress = new Address("School of computing, NUS, Singapore");
            for (int i = 0; i < 16; i++) {
                Equipment newEquipment = new EquipmentBuilder().withName("PGPR" + i).withPhone("8482131")
                        .withDate("10-05-2019").withAddress("PGPR, NUS, SINGAPORE")
                        .withSerialNumber(String.valueOf(i)).build();
                model.addEquipment(newEquipment);
            }
            CommandResult result = new RouteCommand(testAddress).execute(model, commandHistory);
            fail("Routing with 16 equipment list should raise exception.");
        } catch (CommandException ce) {
            if (ce.getMessage() != RouteCommand.MESSAGE_TOO_MANY_EQUIPMENTS_TO_ROUTE) {
                fail("Should display too many equipments message to user.");
            }
        }
    }
}
