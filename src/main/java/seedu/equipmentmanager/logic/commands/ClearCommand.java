package seedu.equipmentmanager.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.equipmentmanager.logic.CommandHistory;
import seedu.equipmentmanager.model.EquipmentManager;
import seedu.equipmentmanager.model.Model;

/**
 * Clears the equipmentmanager book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Equipment Manager has been cleared!";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.setAddressBook(new EquipmentManager());
        model.commitAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
