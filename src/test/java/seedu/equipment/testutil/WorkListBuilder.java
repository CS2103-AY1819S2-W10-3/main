package seedu.equipment.testutil;

//import java.util.HashSet;
//import java.util.Set;

import seedu.equipment.model.WorkList;
import seedu.equipment.model.equipment.Date;
//import seedu.equipment.model.equipment.Equipment;

/**
 * A utility class to help with building WorkList objects.
 */
public class WorkListBuilder {

    public static final String DEFAULT_DATE = "12 February 2019";
    public static final String DEFAULT_ASSIGNEE = "Mei Yen";

    private Date date;
    private String assignee;
    //private Set<Equipment> equipmentSet;

    public WorkListBuilder() {
        date = new Date(DEFAULT_DATE);
        assignee = DEFAULT_ASSIGNEE;
        //equipmentSet = new HashSet<>();
    }

    /**
     * Initializes the WorkListBuilder with the data of {@code workListToCopy}.
     */
    public WorkListBuilder(WorkList workListToCopy) {
        date = workListToCopy.getDate();
        assignee = workListToCopy.getAssignee();
        //equipmentSet = workListToCopy.getEquipments();
    }

    /**
     * Sets the {@code Date} of the {@code WorkList} that we are building.
     */
    public WorkListBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Parses the {@code assignee} of the {@code WorkList} that we are building.
     */
    public WorkListBuilder withAssignee(String assignee) {
        this.assignee = assignee;
        return this;
    }

    public WorkList build() {
        return new WorkList(date, assignee);
    }

}
