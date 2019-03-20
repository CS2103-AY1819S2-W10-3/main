package systemtests;

import static org.junit.Assert.assertTrue;
<<<<<<< HEAD
import static seedu.equipmentmanager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.equipmentmanager.commons.core.Messages.MESSAGE_INVALID_EQUIPMENT_DISPLAYED_INDEX;
import static seedu.equipmentmanager.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.equipmentmanager.logic.commands.SelectCommand.MESSAGE_SELECT_EQUIPMENT_SUCCESS;
import static seedu.equipmentmanager.testutil.TestUtil.getLastIndex;
import static seedu.equipmentmanager.testutil.TestUtil.getMidIndex;
//import static seedu.equipmentmanager.testutil.TypicalEquipments.KEYWORD_MATCHING_CC;
import static seedu.equipmentmanager.testutil.TypicalEquipments.KEYWORD_MATCHING_HWI;
import static seedu.equipmentmanager.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.Test;

import seedu.equipmentmanager.commons.core.index.Index;
import seedu.equipmentmanager.logic.commands.RedoCommand;
import seedu.equipmentmanager.logic.commands.SelectCommand;
import seedu.equipmentmanager.logic.commands.UndoCommand;
import seedu.equipmentmanager.model.Model;
=======
import static seedu.equipment.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.equipment.commons.core.Messages.MESSAGE_INVALID_EQUIPMENT_DISPLAYED_INDEX;
import static seedu.equipment.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.equipment.logic.commands.SelectCommand.MESSAGE_SELECT_EQUIPMENT_SUCCESS;
import static seedu.equipment.testutil.TestUtil.getLastIndex;
import static seedu.equipment.testutil.TestUtil.getMidIndex;
//import static seedu.equipment.testutil.TypicalEquipments.KEYWORD_MATCHING_CC;
import static seedu.equipment.testutil.TypicalEquipments.KEYWORD_MATCHING_HWI;
import static seedu.equipment.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.Test;

import seedu.equipment.commons.core.index.Index;
import seedu.equipment.logic.commands.RedoCommand;
import seedu.equipment.logic.commands.SelectCommand;
import seedu.equipment.logic.commands.UndoCommand;
import seedu.equipment.model.Model;
>>>>>>> 166fafa10330a5bb1b0f3f2671f0c6e4d51aee61

public class SelectCommandSystemTest extends EquipmentManagerSystemTest {
    @Test
    public void select() {
        /* ------------------------ Perform select operations on the shown unfiltered list -------------------------- */

        /* Case: select the first card in the equipment list, command with leading spaces and trailing spaces
         * -> selected
         */
        String command = "   " + SelectCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + "   ";
        assertCommandSuccess(command, INDEX_FIRST_PERSON);

        /* Case: select the last card in the equipment list -> selected */
        Index personCount = getLastIndex(getModel());
        command = SelectCommand.COMMAND_WORD + " " + personCount.getOneBased();
        assertCommandSuccess(command, personCount);

        /* Case: undo previous selection -> rejected */
        command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_FAILURE;
        assertCommandFailure(command, expectedResultMessage);

        /* Case: redo selecting last card in the list -> rejected */
        command = RedoCommand.COMMAND_WORD;
        expectedResultMessage = RedoCommand.MESSAGE_FAILURE;
        assertCommandFailure(command, expectedResultMessage);

        /* Case: select the middle card in the equipment list -> selected */
        Index middleIndex = getMidIndex(getModel());
        command = SelectCommand.COMMAND_WORD + " " + middleIndex.getOneBased();
        assertCommandSuccess(command, middleIndex);

        /* Case: select the current selected card -> selected */
        assertCommandSuccess(command, middleIndex);

        /* ------------------------ Perform select operations on the shown filtered list ---------------------------- */

<<<<<<< HEAD
        /* Case: filtered equipment list, select index within bounds of equipmentmanager book but out of bounds of equipment list
=======
        /* Case: filtered equipment list, select index within bounds of equipment book but out of bounds of equipment list
>>>>>>> 166fafa10330a5bb1b0f3f2671f0c6e4d51aee61
         * -> rejected
         */
        showPersonsWithName(KEYWORD_MATCHING_HWI);
        int invalidIndex = getModel().getEquipmentManager().getPersonList().size();
        assertCommandFailure(SelectCommand.COMMAND_WORD + " " + invalidIndex,
                MESSAGE_INVALID_EQUIPMENT_DISPLAYED_INDEX);

<<<<<<< HEAD
        /* Case: filtered equipment list, select index within bounds of equipmentmanager book and equipment list -> selected */
=======
        /* Case: filtered equipment list, select index within bounds of equipment book and equipment list -> selected */
>>>>>>> 166fafa10330a5bb1b0f3f2671f0c6e4d51aee61
        Index validIndex = Index.fromOneBased(1);
        assertTrue(validIndex.getZeroBased() < getModel().getFilteredEquipmentList().size());
        command = SelectCommand.COMMAND_WORD + " " + validIndex.getOneBased();
        assertCommandSuccess(command, validIndex);

        /* ----------------------------------- Perform invalid select operations ------------------------------------ */

        /* Case: invalid index (0) -> rejected */
        assertCommandFailure(SelectCommand.COMMAND_WORD + " " + 0,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));

        /* Case: invalid index (-1) -> rejected */
        assertCommandFailure(SelectCommand.COMMAND_WORD + " " + -1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));

        /* Case: invalid index (size + 1) -> rejected */
        invalidIndex = getModel().getFilteredEquipmentList().size() + 1;
        assertCommandFailure(SelectCommand.COMMAND_WORD + " " + invalidIndex,
                MESSAGE_INVALID_EQUIPMENT_DISPLAYED_INDEX);

        /* Case: invalid arguments (alphabets) -> rejected */
        assertCommandFailure(SelectCommand.COMMAND_WORD + " abc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));

        /* Case: invalid arguments (extra argument) -> rejected */
        assertCommandFailure(SelectCommand.COMMAND_WORD + " 1 abc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));

        /* Case: mixed case command word -> rejected */
        assertCommandFailure("SeLeCt 1", MESSAGE_UNKNOWN_COMMAND);

<<<<<<< HEAD
        /* Case: select from empty equipmentmanager book -> rejected */
=======
        /* Case: select from empty equipment book -> rejected */
>>>>>>> 166fafa10330a5bb1b0f3f2671f0c6e4d51aee61
        deleteAllPersons();
        assertCommandFailure(SelectCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(),
                MESSAGE_INVALID_EQUIPMENT_DISPLAYED_INDEX);
    }

    /**
     * Executes {@code command} and asserts that the,<br>
     * 1. Command box displays an empty string.<br>
     * 2. Command box has the default style class.<br>
     * 3. Result display box displays the success message of executing select command with the
     * {@code expectedSelectedCardIndex} of the selected equipment.<br>
     * 4. {@code Storage} and {@code EquipmentListPanel} remain unchanged.<br>
     * 5. Selected card is at {@code expectedSelectedCardIndex} and the browser url is updated accordingly.<br>
     * 6. Status bar remains unchanged.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code EquipmentManagerSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see EquipmentManagerSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     * @see EquipmentManagerSystemTest#assertSelectedCardChanged(Index)
     */
    private void assertCommandSuccess(String command, Index expectedSelectedCardIndex) {
        Model expectedModel = getModel();
        String expectedResultMessage = String.format(
                MESSAGE_SELECT_EQUIPMENT_SUCCESS, expectedSelectedCardIndex.getOneBased());
        int preExecutionSelectedCardIndex = getPersonListPanel().getSelectedCardIndex();

        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);

        if (preExecutionSelectedCardIndex == expectedSelectedCardIndex.getZeroBased()) {
            assertSelectedCardUnchanged();
        } else {
            assertSelectedCardChanged(expectedSelectedCardIndex);
        }

        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchanged();
    }

    /**
     * Executes {@code command} and asserts that the,<br>
     * 1. Command box displays {@code command}.<br>
     * 2. Command box has the error style class.<br>
     * 3. Result display box displays {@code expectedResultMessage}.<br>
     * 4. {@code Storage} and {@code EquipmentListPanel} remain unchanged.<br>
     * 5. Browser url, selected card and status bar remain unchanged.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code EquipmentManagerSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see EquipmentManagerSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
