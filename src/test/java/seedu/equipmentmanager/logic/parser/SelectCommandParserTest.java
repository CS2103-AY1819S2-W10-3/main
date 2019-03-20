package seedu.equipmentmanager.logic.parser;

import static seedu.equipmentmanager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.equipmentmanager.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.equipmentmanager.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.equipmentmanager.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.Test;

import seedu.equipmentmanager.logic.commands.SelectCommand;
import seedu.equipmentmanager.logic.parser.SelectCommandParser;

/**
 * Test scope: similar to {@code DeleteCommandParserTest}.
 * @see DeleteCommandParserTest
 */
public class SelectCommandParserTest {

    private SelectCommandParser parser = new SelectCommandParser();

    @Test
    public void parse_validArgs_returnsSelectCommand() {
        assertParseSuccess(parser, "1", new SelectCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));
    }
}
