package seedu.equipmentmanager.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.equipmentmanager.commons.core.GuiSettings;
import seedu.equipmentmanager.commons.core.LogsCenter;
import seedu.equipmentmanager.logic.commands.Command;
import seedu.equipmentmanager.logic.commands.CommandResult;
import seedu.equipmentmanager.logic.commands.exceptions.CommandException;
import seedu.equipmentmanager.logic.parser.EquipmentManagerParser;
import seedu.equipmentmanager.logic.parser.exceptions.ParseException;
import seedu.equipmentmanager.model.Model;
import seedu.equipmentmanager.model.ReadOnlyEquipmentManager;
import seedu.equipmentmanager.model.equipment.Equipment;
import seedu.equipmentmanager.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CommandHistory history;
    private final EquipmentManagerParser equipmentManagerParser;
    private boolean addressBookModified;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        history = new CommandHistory();
        equipmentManagerParser = new EquipmentManagerParser();

        // Set addressBookModified to true whenever the models' equipmentmanager book is modified.
        model.getEquipmentManager().addListener(observable -> addressBookModified = true);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        addressBookModified = false;

        CommandResult commandResult;
        try {
            Command command = equipmentManagerParser.parseCommand(commandText);
            commandResult = command.execute(model, history);
        } finally {
            history.add(commandText);
        }

        if (addressBookModified) {
            logger.info("Address book modified, saving to file.");
            try {
                storage.saveAddressBook(model.getEquipmentManager());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }

        return commandResult;
    }

    @Override
    public ReadOnlyEquipmentManager getAddressBook() {
        return model.getEquipmentManager();
    }

    @Override
    public ObservableList<Equipment> getFilteredPersonList() {
        return model.getFilteredEquipmentList();
    }

    @Override
    public ObservableList<String> getHistory() {
        return history.getHistory();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getEquipmentManagerFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public ReadOnlyProperty<Equipment> selectedPersonProperty() {
        return model.selectedEquipmentProperty();
    }

    @Override
    public void setSelectedPerson(Equipment equipment) {
        model.setSelectedEquipment(equipment);
    }
}
