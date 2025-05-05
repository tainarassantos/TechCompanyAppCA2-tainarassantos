package techcompanyapp;

/**
 *
 * @author Tainara de Souza Santos 
 * @student 2024561
 * Represents all company departments as enum values.
 * I handle department selection and display for the employee management system.
 */
public enum Department {
    /**
     * Declaration of the departments available in the Application Form.txt file
     * (I could not find a solution to search in the file, so I worked with the actual list)
     */
    ACCOUNTING("Accounting"),
    CUSTOMER_SERVICE("Customer Service"),
    FINANCE("Finance"),
    HR("HR"),
    IT_DEVELOPMENT("IT Development"),
    MARKETING("Marketing"),
    OPERATIONS("Operations"),
    SALES("Sales"),
    TECHNICAL_SUPPORT("Technical Support");

    private final String displayName;

    Department(String displayName) {
        this.displayName = displayName;
    }

    /**
     * getDisplayName
     * @return Gets the formatted department name
     */
    public String getDisplayName() {
        return displayName;
    }
    @Override
    public String toString() {
        return displayName;
    }

    /**
     * fromDisplayName:Finds department by display name
     */
    public static Department fromDisplayName(String displayName) {
        for (Department dept : values()) {
            if (dept.displayName.equalsIgnoreCase(displayName)) {
                return dept;
            }
        }
        throw new IllegalArgumentException("No enum constant for display name: " + displayName);
    }
    
    /**
     * displayOptions: Shows departments in a formatted list
     * (static method to list formatted options)
     */
    public static void displayOptions() {
        System.out.println("\nAvailable Departments:");
        int count = 1;
        for (Department dept : Department.values()) {
            System.out.printf("%d. %-20s", count++, dept.getDisplayName());
            if (count % 4 == 1) System.out.println();
        }
        System.out.println();
    }
}
