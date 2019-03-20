package seedu.equipmentmanager.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.equipmentmanager.logic.CommandHistory;
import seedu.equipmentmanager.model.Model;

/**
 * Lists all equipments in the equipment manager to the user.
 */
public class ListEquipmentCommand extends Command {

    public static final String COMMAND_WORD = "list-e";

    public static final String MESSAGE_SUCCESS = "Listed all equipment";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
