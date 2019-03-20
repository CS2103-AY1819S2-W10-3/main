package seedu.equipmentmanager.testutil;

import static seedu.equipmentmanager.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.equipmentmanager.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.equipmentmanager.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.equipmentmanager.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.equipmentmanager.logic.parser.CliSyntax.PREFIX_SERIALNUMBER;
import static seedu.equipmentmanager.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.equipmentmanager.logic.commands.AddCommand;
import seedu.equipmentmanager.logic.commands.EditCommand.EditEquipmentDescriptor;
import seedu.equipmentmanager.model.equipment.Equipment;
import seedu.equipmentmanager.model.tag.Tag;

/**
 * A utility class for Equipment.
 */
public class EquipmentUtil {

    /**
     * Returns an add command string for adding the {@code equipment}.
     */
    public static String getAddCommand(Equipment equipment) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(equipment);
    }

    /**
     * Returns the part of command string for the given {@code equipment}'s details.
     */
    public static String getPersonDetails(Equipment equipment) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + equipment.getName().name + " ");
        sb.append(PREFIX_PHONE + equipment.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + equipment.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + equipment.getAddress().value + " ");
        sb.append(PREFIX_SERIALNUMBER + equipment.getSerialNumber().serialNumber + " ");
        equipment.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEquipmentDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditEquipmentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.name).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getSerialNumber().ifPresent(serialNumber -> sb.append(PREFIX_SERIALNUMBER)
                .append(serialNumber.serialNumber).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
