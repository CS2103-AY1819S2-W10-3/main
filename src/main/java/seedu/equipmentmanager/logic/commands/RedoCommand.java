package seedu.equipmentmanager.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.equipmentmanager.logic.CommandHistory;
import seedu.equipmentmanager.logic.commands.exceptions.CommandException;
import seedu.equipmentmanager.model.Model;

/**
 * Reverts the {@code model}'s equipment manager to its previously undone state.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo success!";
    public static final String MESSAGE_FAILURE = "No more commands to redo!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoEquipmentManager()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.redoEquipmentManager();
        model.updateFilteredEquipmentList(Model.PREDICATE_SHOW_ALL_EQUIPMENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
