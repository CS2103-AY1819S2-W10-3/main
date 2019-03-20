package seedu.equipmentmanager.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.equipmentmanager.commons.core.Messages;
import seedu.equipmentmanager.logic.commands.AddCommand;
import seedu.equipmentmanager.logic.commands.ClearCommand;
import seedu.equipmentmanager.logic.commands.Command;
import seedu.equipmentmanager.logic.commands.DeleteCommand;
import seedu.equipmentmanager.logic.commands.DisplayCommand;
import seedu.equipmentmanager.logic.commands.EditCommand;
import seedu.equipmentmanager.logic.commands.ExitCommand;
import seedu.equipmentmanager.logic.commands.FilterCommand;
import seedu.equipmentmanager.logic.commands.FindCommand;
import seedu.equipmentmanager.logic.commands.HelpCommand;
import seedu.equipmentmanager.logic.commands.HistoryCommand;
import seedu.equipmentmanager.logic.commands.ListEquipmentCommand;
import seedu.equipmentmanager.logic.commands.ListWorkListCommand;
import seedu.equipmentmanager.logic.commands.RedoCommand;
import seedu.equipmentmanager.logic.commands.SelectCommand;
import seedu.equipmentmanager.logic.commands.SortCommand;
import seedu.equipmentmanager.logic.commands.UndoCommand;
import seedu.equipmentmanager.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class EquipmentManagerParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListEquipmentCommand.COMMAND_WORD:
            return new ListEquipmentCommand();

        case ListWorkListCommand.COMMAND_WORD:
            return new ListWorkListCommand();

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommand();

        case DisplayCommand.COMMAND_WORD:
            return new DisplayCommand();

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
