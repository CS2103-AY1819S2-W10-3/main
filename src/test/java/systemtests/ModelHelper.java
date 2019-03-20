package systemtests;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

<<<<<<< HEAD
import seedu.equipmentmanager.model.Model;
import seedu.equipmentmanager.model.equipment.Equipment;
=======
import seedu.equipment.model.Model;
import seedu.equipment.model.equipment.Equipment;
>>>>>>> 166fafa10330a5bb1b0f3f2671f0c6e4d51aee61

/**
 * Contains helper methods to set up {@code Model} for testing.
 */
public class ModelHelper {
    private static final Predicate<Equipment> PREDICATE_MATCHING_NO_PERSONS = unused -> false;

    /**
     * Updates {@code model}'s filtered list to display only {@code toDisplay}.
     */
    public static void setFilteredList(Model model, List<Equipment> toDisplay) {
        Optional<Predicate<Equipment>> predicate =
                toDisplay.stream().map(ModelHelper::getPredicateMatching).reduce(Predicate::or);
        model.updateFilteredEquipmentList(predicate.orElse(PREDICATE_MATCHING_NO_PERSONS));
    }

    /**
     * @see ModelHelper#setFilteredList(Model, List)
     */
    public static void setFilteredList(Model model, Equipment... toDisplay) {
        setFilteredList(model, Arrays.asList(toDisplay));
    }

    /**
     * Returns a predicate that evaluates to true if this {@code Equipment} equals to {@code other}.
     */
    private static Predicate<Equipment> getPredicateMatching(Equipment other) {
        return person -> person.equals(other);
    }
}
