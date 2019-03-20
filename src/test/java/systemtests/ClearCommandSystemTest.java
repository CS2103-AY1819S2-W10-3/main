package systemtests;

<<<<<<< HEAD
import static seedu.equipmentmanager.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
//import static seedu.equipmentmanager.testutil.TypicalEquipments.KEYWORD_MATCHING_CC;
import static seedu.equipmentmanager.testutil.TypicalEquipments.KEYWORD_MATCHING_HWI;

import org.junit.Test;

import seedu.equipmentmanager.commons.core.index.Index;
import seedu.equipmentmanager.logic.commands.ClearCommand;
import seedu.equipmentmanager.logic.commands.RedoCommand;
import seedu.equipmentmanager.logic.commands.UndoCommand;
import seedu.equipmentmanager.model.Model;
import seedu.equipmentmanager.model.ModelManager;
=======
import static seedu.equipment.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
//import static seedu.equipment.testutil.TypicalEquipments.KEYWORD_MATCHING_CC;
import static seedu.equipment.testutil.TypicalEquipments.KEYWORD_MATCHING_HWI;

import org.junit.Test;

import seedu.equipment.commons.core.index.Index;
import seedu.equipment.logic.commands.ClearCommand;
import seedu.equipment.logic.commands.RedoCommand;
import seedu.equipment.logic.commands.UndoCommand;
import seedu.equipment.model.Model;
import seedu.equipment.model.ModelManager;
>>>>>>> 166fafa10330a5bb1b0f3f2671f0c6e4d51aee61

public class ClearCommandSystemTest extends EquipmentManagerSystemTest {

    @Test
    public void clear() {
        final Model defaultModel = getModel();

<<<<<<< HEAD
        /* Case: clear non-empty equipmentmanager book, command with leading spaces and trailing alphanumeric characters and
=======
        /* Case: clear non-empty equipment book, command with leading spaces and trailing alphanumeric characters and
>>>>>>> 166fafa10330a5bb1b0f3f2671f0c6e4d51aee61
         * spaces -> cleared
         */
        assertCommandSuccess("   " + ClearCommand.COMMAND_WORD + " ab12   ");
        assertSelectedCardUnchanged();

<<<<<<< HEAD
        /* Case: undo clearing equipmentmanager book -> original equipmentmanager book restored */
=======
        /* Case: undo clearing equipment book -> original equipment book restored */
>>>>>>> 166fafa10330a5bb1b0f3f2671f0c6e4d51aee61
        String command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, expectedResultMessage, defaultModel);
        assertSelectedCardUnchanged();

<<<<<<< HEAD
        /* Case: redo clearing equipmentmanager book -> cleared */
=======
        /* Case: redo clearing equipment book -> cleared */
>>>>>>> 166fafa10330a5bb1b0f3f2671f0c6e4d51aee61
        command = RedoCommand.COMMAND_WORD;
        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, expectedResultMessage, new ModelManager());
        assertSelectedCardUnchanged();

<<<<<<< HEAD
        /* Case: selects first card in equipment list and clears equipmentmanager book -> cleared and no card selected */
        executeCommand(UndoCommand.COMMAND_WORD); // restores the original equipmentmanager book
=======
        /* Case: selects first card in equipment list and clears equipment book -> cleared and no card selected */
        executeCommand(UndoCommand.COMMAND_WORD); // restores the original equipment book
>>>>>>> 166fafa10330a5bb1b0f3f2671f0c6e4d51aee61
        selectPerson(Index.fromOneBased(1));
        assertCommandSuccess(ClearCommand.COMMAND_WORD);
        assertSelectedCardDeselected();

<<<<<<< HEAD
        /* Case: filters the equipment list before clearing -> entire equipmentmanager book cleared */
        executeCommand(UndoCommand.COMMAND_WORD); // restores the original equipmentmanager book
=======
        /* Case: filters the equipment list before clearing -> entire equipment book cleared */
        executeCommand(UndoCommand.COMMAND_WORD); // restores the original equipment book
>>>>>>> 166fafa10330a5bb1b0f3f2671f0c6e4d51aee61
        showPersonsWithName(KEYWORD_MATCHING_HWI);
        assertCommandSuccess(ClearCommand.COMMAND_WORD);
        assertSelectedCardUnchanged();

<<<<<<< HEAD
        /* Case: clear empty equipmentmanager book -> cleared */
=======
        /* Case: clear empty equipment book -> cleared */
>>>>>>> 166fafa10330a5bb1b0f3f2671f0c6e4d51aee61
        assertCommandSuccess(ClearCommand.COMMAND_WORD);
        assertSelectedCardUnchanged();

        /* Case: mixed case command word -> rejected */
        assertCommandFailure("ClEaR", MESSAGE_UNKNOWN_COMMAND);
    }

    /**
     * Executes {@code command} and verifies that the command box displays an empty string, the result display
     * box displays {@code ClearCommand#MESSAGE_SUCCESS} and the model related components equal to an empty model.
     * These verifications are done by
     * {@code EquipmentManagerSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the command box has the default style class and the status bar's sync status changes.
     * @see EquipmentManagerSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(String command) {
        assertCommandSuccess(command, ClearCommand.MESSAGE_SUCCESS, new ModelManager());
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String)} except that the result box displays
     * {@code expectedResultMessage} and the model related components equal to {@code expectedModel}.
     * @see ClearCommandSystemTest#assertCommandSuccess(String)
     */
    private void assertCommandSuccess(String command, String expectedResultMessage, Model expectedModel) {
        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchangedExceptSyncStatus();
    }

    /**
     * Executes {@code command} and verifies that the command box displays {@code command}, the result display
     * box displays {@code expectedResultMessage} and the model related components equal to the current model.
     * These verifications are done by
     * {@code EquipmentManagerSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the browser url, selected card and status bar remain unchanged, and the command box has the
     * error style.
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
