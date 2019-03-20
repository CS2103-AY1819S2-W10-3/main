package seedu.equipmentmanager.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.equipmentmanager.logic.CommandHistory;
import seedu.equipmentmanager.logic.commands.exceptions.CommandException;
import seedu.equipmentmanager.model.Model;

/**
 * Reverts the {@code model}'s equipment manager to its previous state.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo success!";
    public static final String MESSAGE_FAILURE = "No more commands to undo!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoEquipmentManager()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.undoEquipmentManager();
        model.updateFilteredEquipmentList(Model.PREDICATE_SHOW_ALL_EQUIPMENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
