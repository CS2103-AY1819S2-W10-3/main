package seedu.equipmentmanager.logic.parser;

import static seedu.equipmentmanager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.equipmentmanager.logic.commands.FilterCommand;
import seedu.equipmentmanager.logic.parser.Parser;
import seedu.equipmentmanager.logic.parser.exceptions.ParseException;
import seedu.equipmentmanager.model.equipment.EquipmentContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns an FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        String[] tagKeywords = trimmedArgs.split("\\s+");

        return new FilterCommand(new EquipmentContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
    }

}
