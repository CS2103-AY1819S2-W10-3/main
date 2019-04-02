package seedu.equipment.model.equipment;

import java.util.List;
import java.util.function.Predicate;

import seedu.equipment.commons.util.StringUtil;

/**
 * Tests that a {@code Equipment}'s {@code Name} matches any of the keywords given.
 */
public class EquipmentContainsKeywordsPredicate implements Predicate<Equipment> {
    private final List<String> keywords;

    public EquipmentContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Equipment equipment) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(equipment.getSerialNumber().serialNumber, keyword)
                                || StringUtil.containsWordIgnoreCase(equipment.getDate().value, keyword)
                                || StringUtil.containsWordIgnoreCase(equipment.getTags().toString(), keyword)
                                || StringUtil.containsWordIgnoreCase(equipment.getPhone().value, keyword)
                                || StringUtil.containsWordIgnoreCase(equipment.getAddress().value, keyword)
                                || StringUtil.containsWordIgnoreCase(equipment.getName().name, keyword)
                                || StringUtil.containsWordIgnoreCase(equipment.getTags().toString(), keyword)
                                || StringUtil.containsPartialWordIgnoreCase(equipment.getSerialNumber().serialNumber,
                                keyword)
                                || StringUtil.containsPartialWordIgnoreCase(equipment.getAddress().value, keyword)
                                || StringUtil.containsPartialWordIgnoreCase(equipment.getName().name, keyword)
                                || StringUtil.containsPartialWordIgnoreCase(equipment.getTags().toString(), keyword)
                                || StringUtil.containsPartialWordIgnoreCase(equipment.getPhone().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EquipmentContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EquipmentContainsKeywordsPredicate) other).keywords)); // state check
    }
}
