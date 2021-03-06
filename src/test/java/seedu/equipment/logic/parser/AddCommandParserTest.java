package seedu.equipment.logic.parser;

import static seedu.equipment.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.equipment.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.equipment.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.equipment.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.equipment.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static seedu.equipment.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.equipment.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.equipment.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.equipment.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
//import static seedu.equipment.logic.commands.CommandTestUtil.INVALID_SERIAL_NUMBER_DESC;
import static seedu.equipment.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.equipment.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.equipment.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.equipment.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.equipment.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.equipment.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.equipment.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.equipment.logic.commands.CommandTestUtil.SERIAL_NUMBER_DESC_AMY;
import static seedu.equipment.logic.commands.CommandTestUtil.SERIAL_NUMBER_DESC_BOB;
import static seedu.equipment.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.equipment.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.equipment.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.equipment.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.equipment.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.equipment.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
//import static seedu.equipment.logic.commands.CommandTestUtil.VALID_SERIAL_NUMBER_AMY;
import static seedu.equipment.logic.commands.CommandTestUtil.VALID_SERIAL_NUMBER_BOB;
import static seedu.equipment.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.equipment.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.equipment.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.equipment.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.equipment.testutil.TypicalEquipments.AMY;
import static seedu.equipment.testutil.TypicalEquipments.BOB;

import org.junit.Test;

import seedu.equipment.logic.commands.AddCommand;
import seedu.equipment.model.equipment.Address;
import seedu.equipment.model.equipment.Date;
import seedu.equipment.model.equipment.Equipment;
import seedu.equipment.model.equipment.Name;
import seedu.equipment.model.equipment.Phone;
import seedu.equipment.model.tag.Tag;
import seedu.equipment.testutil.EquipmentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Equipment expectedEquipment = new EquipmentBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + DATE_DESC_BOB
                + ADDRESS_DESC_BOB + SERIAL_NUMBER_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedEquipment));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + DATE_DESC_BOB
                + ADDRESS_DESC_BOB + SERIAL_NUMBER_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedEquipment));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + DATE_DESC_BOB
                + ADDRESS_DESC_BOB + SERIAL_NUMBER_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedEquipment));

        // multiple dates - last date accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DATE_DESC_AMY + DATE_DESC_BOB
                + ADDRESS_DESC_BOB + SERIAL_NUMBER_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedEquipment));

        // multiple addresses - last equipment accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DATE_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + SERIAL_NUMBER_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedEquipment));

        // multiple tags - all accepted
        Equipment expectedEquipmentMultipleTags = new EquipmentBuilder(BOB).withTags(VALID_TAG_FRIEND,
                VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DATE_DESC_BOB + ADDRESS_DESC_BOB
                + SERIAL_NUMBER_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddCommand(expectedEquipmentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Equipment expectedEquipment = new EquipmentBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + DATE_DESC_AMY + ADDRESS_DESC_AMY
                + SERIAL_NUMBER_DESC_AMY, new AddCommand(expectedEquipment));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + DATE_DESC_BOB + ADDRESS_DESC_BOB
                + SERIAL_NUMBER_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + DATE_DESC_BOB + ADDRESS_DESC_BOB
                + SERIAL_NUMBER_DESC_BOB, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_DATE_BOB + ADDRESS_DESC_BOB
                + SERIAL_NUMBER_DESC_BOB, expectedMessage);

        // missing equipment prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DATE_DESC_BOB + VALID_ADDRESS_BOB
                + SERIAL_NUMBER_DESC_BOB, expectedMessage);

        // missing serial number prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DATE_DESC_BOB + ADDRESS_DESC_BOB
                + VALID_SERIAL_NUMBER_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_DATE_BOB + VALID_ADDRESS_BOB
                + SERIAL_NUMBER_DESC_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + DATE_DESC_BOB + ADDRESS_DESC_BOB
                + SERIAL_NUMBER_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + DATE_DESC_BOB + ADDRESS_DESC_BOB
                + SERIAL_NUMBER_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_DATE_DESC + ADDRESS_DESC_BOB
                + SERIAL_NUMBER_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Date.MESSAGE_CONSTRAINTS);

        // invalid equipment
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DATE_DESC_BOB + INVALID_ADDRESS_DESC
                + SERIAL_NUMBER_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);



        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DATE_DESC_BOB + ADDRESS_DESC_BOB
                + SERIAL_NUMBER_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + DATE_DESC_BOB + INVALID_ADDRESS_DESC
                + SERIAL_NUMBER_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + DATE_DESC_BOB
                + ADDRESS_DESC_BOB + SERIAL_NUMBER_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
