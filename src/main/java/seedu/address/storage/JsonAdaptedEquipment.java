package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.equipment.Address;
import seedu.address.model.equipment.Email;
import seedu.address.model.equipment.Equipment;
import seedu.address.model.equipment.Name;
import seedu.address.model.equipment.Phone;
import seedu.address.model.equipment.SerialNumber;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Equipment}.
 */
class JsonAdaptedEquipment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Equipment's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String serialNumber;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEquipment} with the given equipment details.
     */
    @JsonCreator
    public JsonAdaptedEquipment(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                                @JsonProperty("email") String email, @JsonProperty("address") String address,
                                @JsonProperty("serial number") String serialNumber,
                                @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.serialNumber = serialNumber;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Equipment} into this class for Jackson use.
     */
    public JsonAdaptedEquipment(Equipment source) {
        name = source.getName().name;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        serialNumber = source.getSerialNumber().serialNumber;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted equipment object into the model's {@code Equipment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted equipment.
     */
    public Equipment toModelType() throws IllegalValueException {
        final List<Tag> equipmentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            equipmentTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (serialNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    SerialNumber.class.getSimpleName()));
        }
        if (!SerialNumber.isValidSerialNumber(serialNumber)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final SerialNumber modelSerialNumber = new SerialNumber(serialNumber);

        final Set<Tag> modelTags = new HashSet<>(equipmentTags);
        return new Equipment(modelName, modelPhone, modelEmail, modelAddress, modelSerialNumber, modelTags);
    }

}
