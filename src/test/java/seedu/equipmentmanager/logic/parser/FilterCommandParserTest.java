package seedu.equipmentmanager.logic.parser;

import static seedu.equipmentmanager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.equipmentmanager.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.equipmentmanager.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.equipmentmanager.logic.commands.FilterCommand;

import seedu.equipmentmanager.logic.parser.FilterCommandParser;
import seedu.equipmentmanager.model.equipment.EquipmentContainsKeywordsPredicate;

public class FilterCommandParserTest {

    private seedu.equipmentmanager.logic.parser.FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new EquipmentContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFilterCommand);
    }
}
