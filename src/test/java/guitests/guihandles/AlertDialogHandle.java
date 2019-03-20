package guitests.guihandles;

import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
<<<<<<< HEAD
import seedu.equipmentmanager.ui.UiManager;
=======
import seedu.equipment.ui.UiManager;
>>>>>>> 166fafa10330a5bb1b0f3f2671f0c6e4d51aee61

/**
 * A handle for the {@code AlertDialog} of the UI.
 */
public class AlertDialogHandle extends StageHandle {
    private final DialogPane dialogPane;

    public AlertDialogHandle(Stage stage) {
        super(stage);

        dialogPane = getChildNode("#" + UiManager.ALERT_DIALOG_PANE_FIELD_ID);
    }

    /**
     * Returns the text of the header in the {@code AlertDialog}.
     */
    public String getHeaderText() {
        return dialogPane.getHeaderText();
    }

    /**
     * Returns the text of the content in the {@code AlertDialog}.
     */
    public String getContentText() {
        return dialogPane.getContentText();
    }
}
