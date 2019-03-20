package seedu.equipmentmanager.logic.commands;

import seedu.equipmentmanager.commons.core.Messages;
import seedu.equipmentmanager.logic.CommandHistory;
import seedu.equipmentmanager.model.Model;


/**
 * Finds and lists all persons in equipmentmanager book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class DisplayCommand extends Command {

    public static final String COMMAND_WORD = "display";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display the locations of equipments shown on left."
            + "Example: " + COMMAND_WORD;

    public DisplayCommand() {
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        return new CommandResult(
                Messages.MESSAGE_EQUIPMENT_DISPLAYED_OVERVIEW, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this; // short circuit if same object
    }
}
