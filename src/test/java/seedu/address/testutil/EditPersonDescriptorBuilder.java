package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditEquipmentDescriptor;
import seedu.address.model.equipment.Address;
import seedu.address.model.equipment.Email;
import seedu.address.model.equipment.Equipment;
import seedu.address.model.equipment.Name;
import seedu.address.model.equipment.Phone;
import seedu.address.model.equipment.SerialNumber;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditEquipmentDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditEquipmentDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditEquipmentDescriptor();
    }

    public EditPersonDescriptorBuilder(EditEquipmentDescriptor descriptor) {
        this.descriptor = new EditEquipmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEquipmentDescriptor} with fields containing {@code equipment}'s details
     */
    public EditPersonDescriptorBuilder(Equipment equipment) {
        descriptor = new EditEquipmentDescriptor();
        descriptor.setName(equipment.getName());
        descriptor.setPhone(equipment.getPhone());
        descriptor.setEmail(equipment.getEmail());
        descriptor.setAddress(equipment.getAddress());
        descriptor.setSerialNumber(equipment.getSerialNumber());
        descriptor.setTags(equipment.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditEquipmentDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditEquipmentDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditEquipmentDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditEquipmentDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code SerialNumber} of the {@code EditEquipmentDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSerialNumber(String serialNumber) {
        descriptor.setSerialNumber(new SerialNumber(serialNumber));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditEquipmentDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditEquipmentDescriptor build() {
        return descriptor;
    }
}
