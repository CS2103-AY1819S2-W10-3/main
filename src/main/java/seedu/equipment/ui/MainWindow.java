package seedu.equipment.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.equipment.commons.core.GuiSettings;
import seedu.equipment.commons.core.LogsCenter;
import seedu.equipment.logic.Logic;
import seedu.equipment.logic.commands.CommandResult;
import seedu.equipment.logic.commands.exceptions.CommandException;
import seedu.equipment.logic.parser.exceptions.ParseException;
import seedu.equipment.model.equipment.Equipment;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private String command;

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private BrowserPanel browserPanel;
    private EquipmentListPanel equipmentListPanel;
    private EquipmentListPanel equipmentListPanel2;
    private WorkListListPanel workListListPanel;
    private ClientListPanel clientListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane browserPlaceholder;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane clientListPanelPlaceholder;

    @FXML
    private StackPane workListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }
    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerPartsWithClient() {
        browserPanel = new BrowserPanel(logic.selectedEquipmentProperty());
        browserPlaceholder.getChildren().add(browserPanel.getRoot());

        equipmentListPanel = new EquipmentListPanel(logic.getFilteredClient());
        personListPanelPlaceholder.getChildren().add(equipmentListPanel.getRoot());

        workListListPanel = new WorkListListPanel(logic.getFilteredWorkListList(), logic.selectedWorkListProperty(),
                logic::setSelectedWorkList);
        workListPanelPlaceholder.getChildren().add(workListListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getEquipmentManagerFilePath(),
                logic.getEquipmentManager());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand, logic.getHistory());
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerPartsWithEquipment() {
        browserPanel = new BrowserPanel(logic.selectedEquipmentProperty());
        browserPlaceholder.getChildren().add(browserPanel.getRoot());

        equipmentListPanel = new EquipmentListPanel(logic.getFilteredEquipment(), logic.selectedEquipmentProperty(),
                logic::setSelectedPerson);
        personListPanelPlaceholder.getChildren().add(equipmentListPanel.getRoot());

        workListListPanel = new WorkListListPanel(logic.getFilteredWorkListList(), logic.selectedWorkListProperty(),
                logic::setSelectedWorkList);
        workListPanelPlaceholder.getChildren().add(workListListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getEquipmentManagerFilePath(),
                logic.getEquipmentManager());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand, logic.getHistory());
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Opens the map window or focuses on it if it's already opened.
     */
    @FXML
    public void handleDisplayMap() {
        List<Equipment> equipmentList = logic.getFilteredEquipment();
        String coordiantesString = "[";
        for (Equipment equipment:equipmentList) {
            double[] coordinates = equipment.getCoordiantes();
            coordiantesString += "[" + coordinates[0] + "," + coordinates[1] + "],";
        }
        coordiantesString = coordiantesString.replaceAll(",$", "");
        coordiantesString += "]";
        String url = BrowserPanel.MAP_PAGE_BASE_URL + "?coordinates=" + coordiantesString;
        System.out.println("Loading page: " + url);
        browserPanel.loadPage(url);
    }

    public EquipmentListPanel getEquipmentListPanel() {
        return equipmentListPanel;
    }

    public WorkListListPanel getWorkListListPanel() {
        return workListListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            setCommand(commandText);
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isDisplayMap()) {
                handleDisplayMap();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    private void setCommand(String commandText) {
        this.command = commandText;
    }

    public String getCommand() {
        return command;
    }
}
