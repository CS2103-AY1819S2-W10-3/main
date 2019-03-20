package seedu.equipmentmanager.ui;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.equipmentmanager.commons.core.LogsCenter;
import seedu.equipmentmanager.model.equipment.Equipment;

/**
 * Panel containing the list of persons.
 */
public class EquipmentListPanel extends UiPart<Region> {
    private static final String FXML = "EquipmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EquipmentListPanel.class);

    @FXML
    private ListView<Equipment> equipmentListView;

    public EquipmentListPanel(ObservableList<Equipment> equipmentList, ObservableValue<Equipment> selectedPerson,
                              Consumer<Equipment> onSelectedPersonChange) {
        super(FXML);
        equipmentListView.setItems(equipmentList);
        equipmentListView.setCellFactory(listView -> new EquipmentListViewCell());
        equipmentListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            logger.fine("Selection in equipment list panel changed to : '" + newValue + "'");
            onSelectedPersonChange.accept(newValue);
        });
        selectedPerson.addListener((observable, oldValue, newValue) -> {
            logger.fine("Selected equipment changed to: " + newValue);

            // Don't modify selection if we are already selecting the selected equipment,
            // otherwise we would have an infinite loop.
            if (Objects.equals(equipmentListView.getSelectionModel().getSelectedItem(), newValue)) {
                return;
            }

            if (newValue == null) {
                equipmentListView.getSelectionModel().clearSelection();
            } else {
                int index = equipmentListView.getItems().indexOf(newValue);
                equipmentListView.scrollTo(index);
                equipmentListView.getSelectionModel().clearAndSelect(index);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Equipment} using a {@code EquipmentCard}.
     */
    class EquipmentListViewCell extends ListCell<Equipment> {
        @Override
        protected void updateItem(Equipment equipment, boolean empty) {
            super.updateItem(equipment, empty);

            if (empty || equipment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EquipmentCard(equipment, getIndex() + 1).getRoot());
            }
        }
    }

}
