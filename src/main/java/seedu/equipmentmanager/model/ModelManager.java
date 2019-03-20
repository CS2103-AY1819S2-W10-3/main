package seedu.equipmentmanager.model;

import static java.util.Objects.requireNonNull;
import static seedu.equipmentmanager.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.equipmentmanager.commons.core.GuiSettings;
import seedu.equipmentmanager.commons.core.LogsCenter;
import seedu.equipmentmanager.model.equipment.Equipment;
import seedu.equipmentmanager.model.equipment.exceptions.EquipmentNotFoundException;
import seedu.equipmentmanager.model.tag.Tag;

/**
 * Represents the in-memory model of the equipmentmanager book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedEquipmentManager versionedEquipmentManager;
    private final UserPrefs userPrefs;
    private final FilteredList<Equipment> filteredEquipments;
    private final FilteredList<WorkList> filteredWorkList;
    private final SimpleObjectProperty<Equipment> selectedPerson = new SimpleObjectProperty<>();

    /**
     * Initializes a ModelManager with the given equipmentManager and userPrefs.
     */
    public ModelManager(ReadOnlyEquipmentManager equipmentManager, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(equipmentManager, userPrefs);

        logger.fine("Initializing with equipment manager: " + equipmentManager + " and user prefs " + userPrefs);

        versionedEquipmentManager = new VersionedEquipmentManager(equipmentManager);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredEquipments = new FilteredList<>(versionedEquipmentManager.getPersonList());
        filteredEquipments.addListener(this::ensureSelectedPersonIsValid);
        filteredWorkList = new FilteredList<>(versionedEquipmentManager.getWorkListList());
        //filteredWorkList.addListener(this::ensureSelectedworkListIsValid);
    }

    public ModelManager() {
        this(new EquipmentManager(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getEquipmentManagerFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setEquipmentManagerFilePath(Path equipmentManagerFilePath) {
        requireNonNull(equipmentManagerFilePath);
        userPrefs.setAddressBookFilePath(equipmentManagerFilePath);
    }

    //=========== EquipmentManager ================================================================================

    @Override
    public void setEquipmentManager(ReadOnlyEquipmentManager addressBook) {
        versionedEquipmentManager.resetData(addressBook);
    }

    @Override
    public ReadOnlyEquipmentManager getEquipmentManager() {
        return versionedEquipmentManager;
    }

    @Override
    public boolean hasEquipment(Equipment equipment) {
        requireNonNull(equipment);
        return versionedEquipmentManager.hasPerson(equipment);
    }

    @Override
    public void deleteEquipment(Equipment target) {
        versionedEquipmentManager.removePerson(target);
    }

    @Override
    public void addEquipment(Equipment equipment) {
        versionedEquipmentManager.addPerson(equipment);
        updateFilteredEquipmentList(PREDICATE_SHOW_ALL_EQUIPMENTS);
    }

    @Override
    public void setEquipment(Equipment target, Equipment editedEquipment) {
        requireAllNonNull(target, editedEquipment);

        versionedEquipmentManager.setPerson(target, editedEquipment);
    }

    @Override
    public void updateEquipment(Equipment target, Equipment editedEquipment) {
        requireAllNonNull(target, editedEquipment);

        versionedEquipmentManager.updatePerson(target, editedEquipment);
    }

    //=========== Filtered WorkList List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code WorkList} backed by the internal list of
     * {@code versionedEquipmentManager}
     */
    public ObservableList<WorkList> getFilteredWorkListList() {
        return filteredWorkList;
    }

    @Override
    public void updateFilteredWorkListList(Predicate<WorkList> predicate) {
        requireNonNull(predicate);
        filteredWorkList.setPredicate(predicate);
    }

    //=========== Filtered Equipment List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Equipment} backed by the internal list of
     * {@code versionedEquipmentManager}
     */
    @Override
    public ObservableList<Equipment> getFilteredEquipmentList() {
        return filteredEquipments;
    }

    @Override
    public void updateFilteredEquipmentList(Predicate<Equipment> predicate) {
        requireNonNull(predicate);
        filteredEquipments.setPredicate(predicate);
    }
    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoEquipmentManager() {
        return versionedEquipmentManager.canUndo();
    }

    @Override
    public boolean canRedoEquipmentManager() {
        return versionedEquipmentManager.canRedo();
    }

    @Override
    public void undoEquipmentManager() {
        versionedEquipmentManager.undo();
    }

    @Override
    public void redoEquipmentManager() {
        versionedEquipmentManager.redo();
    }

    @Override
    public void commitEquipmentManager() {
        versionedEquipmentManager.commit();
    }

    //=========== Selected equipment ===========================================================================

    @Override
    public ReadOnlyProperty<Equipment> selectedEquipmentProperty() {
        return selectedPerson;
    }

    @Override
    public Equipment getSelectedPerson() {
        return selectedPerson.getValue();
    }

    @Override
    public void setSelectedEquipment(Equipment equipment) {
        if (equipment != null && !filteredEquipments.contains(equipment)) {
            throw new EquipmentNotFoundException();
        }
        selectedPerson.setValue(equipment);
    }

    @Override
    public void deleteTag(Tag tag) {
        versionedEquipmentManager.removeTag(tag);
    }

    /**
     * Ensures {@code selectedPerson} is a valid equipment in {@code filteredEquipments}.
     */
    private void ensureSelectedPersonIsValid(ListChangeListener.Change<? extends Equipment> change) {
        while (change.next()) {
            if (selectedPerson.getValue() == null) {
                // null is always a valid selected equipment, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedPersonReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedPerson.getValue());
            if (wasSelectedPersonReplaced) {
                // Update selectedPerson to its new value.
                int index = change.getRemoved().indexOf(selectedPerson.getValue());
                selectedPerson.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedPersonRemoved = change.getRemoved().stream()
                    .anyMatch(removedPerson -> selectedPerson.getValue().isSameEquipment(removedPerson));
            if (wasSelectedPersonRemoved) {
                // Select the equipment that came before it in the list,
                // or clear the selection if there is no such equipment.
                selectedPerson.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    @Override
    public void sortByName() {
        versionedEquipmentManager.sortByName();
        updateFilteredEquipmentList(PREDICATE_SHOW_ALL_EQUIPMENTS);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedEquipmentManager.equals(other.versionedEquipmentManager)
                && userPrefs.equals(other.userPrefs)
                && filteredEquipments.equals(other.filteredEquipments)
                && Objects.equals(selectedPerson.get(), other.selectedPerson.get());
    }
}
