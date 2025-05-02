package techcompanyapp;

/**
 *
 * @author Tainara
 */
public enum Department {
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

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static Department fromDisplayName(String displayName) {
        for (Department dept : values()) {
            if (dept.displayName.equalsIgnoreCase(displayName)) {
                return dept;
            }
        }
        throw new IllegalArgumentException("No enum constant for display name: " + displayName);
    }
    
}
