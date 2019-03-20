package seedu.equipmentmanager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.equipmentmanager.commons.core.Config;
import seedu.equipmentmanager.commons.core.LogsCenter;
import seedu.equipmentmanager.commons.core.Version;
import seedu.equipmentmanager.commons.exceptions.DataConversionException;
import seedu.equipmentmanager.commons.util.ConfigUtil;
import seedu.equipmentmanager.commons.util.StringUtil;
import seedu.equipmentmanager.logic.Logic;
import seedu.equipmentmanager.logic.LogicManager;
import seedu.equipmentmanager.model.EquipmentManager;
import seedu.equipmentmanager.model.Model;
import seedu.equipmentmanager.model.ModelManager;
import seedu.equipmentmanager.model.ReadOnlyEquipmentManager;
import seedu.equipmentmanager.model.ReadOnlyUserPrefs;
import seedu.equipmentmanager.model.UserPrefs;
import seedu.equipmentmanager.model.util.SampleDataUtil;
import seedu.equipmentmanager.storage.EquipmentManagerStorage;
import seedu.equipmentmanager.storage.JsonEquipmentManagerStorage;
import seedu.equipmentmanager.storage.JsonUserPrefsStorage;
import seedu.equipmentmanager.storage.Storage;
import seedu.equipmentmanager.storage.StorageManager;
import seedu.equipmentmanager.storage.UserPrefsStorage;
import seedu.equipmentmanager.ui.Ui;
import seedu.equipmentmanager.ui.UiManager;

/**
 * The main entry point to the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 6, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing EquipmentManager ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        EquipmentManagerStorage equipmentManagerStorage = new JsonEquipmentManagerStorage(
                userPrefs.getAddressBookFilePath());
        storage = new StorageManager(equipmentManagerStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s equipmentmanager book and {@code userPrefs}.
     * The data from the sample equipmentmanager book will be used instead if {@code storage}'s equipmentmanager book
     * is not found, or an empty equipmentmanager book will be used instead if errors occur
     * when reading {@code storage}'s equipmentmanager book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyEquipmentManager> addressBookOptional;
        ReadOnlyEquipmentManager initialData;
        try {
            addressBookOptional = storage.readAddressBook();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample EquipmentManager");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. "
                    + "Will be starting with an empty EquipmentManager");
            initialData = new EquipmentManager();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. "
                    + "Will be starting with an empty EquipmentManager");
            initialData = new EquipmentManager();
        }

        return new ModelManager(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. "
                    + "Will be starting with an empty EquipmentManager");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting EquipmentManager " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
