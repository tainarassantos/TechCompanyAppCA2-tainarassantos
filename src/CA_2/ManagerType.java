package CA_2;

/**
 *
 * @author Tainara de Souza Santos 
 * @student 2024561
 * Defines all possible job titles in the company.
 * I provide formatted display options and conversion between display names and enum values.
 */

public enum ManagerType {
    /**
     * Declaration of the manager/job tittles available in the Application Form.txt file
     * (I could not find a solution to search in the file, so I worked with the actual list)
     */
    AI_DEVELOPER("AI Developer"),
    ASSISTANT_MANAGER("Assistant Manager"),
    BACKEND_DEVELOPER("Backend Developer"),
    BOOKKEEPER("Bookkeeper"),
    CLERK("Clerk"),
    CLIENT_RELATIONS_COORDINATOR("Client Relations Coordinator"),
    CLIENT_RELATIONS_SPECIALIST("Client Relations Specialist"),
    DESK_JOCKEY("Desk Jockey"),
    DEVOPS("DevOps"),
    FINANCE_ANALYST("Finance Analyst"),
    FRONTEND_DEVELOPER("Frontend Developer"),
    FULL_STACK("Full-stack"),
    FULL_STACK_DEVELOPER("Full-stack Developer"),
    HEAD_MANAGER("Head Manager"),
    HR_ANALYST("HR Analyst"),
    HR_SPECIALIST("HR Specialist"),
    JUNIOR_BOOKKEEPER("Junior Bookkeeper"),
    JUNIOR_CLERK("Junior Clerk"),
    MARKETING_SPECIALIST("Marketing Specialist"),
    MOBILE_DEVELOPER("Mobile Developer"),
    OFFICE_WORKER("Office Worker"),
    QA("QA"),
    SALES_CLERK("Sales Clerk"),
    SENIOR_MANAGER("Senior Manager"),
    SUPPORT_CLERK("Support Clerk"),
    TEAM_LEAD("Team Lead"),
    WHITE_COLLAR_WORKER("White-collar Worker");

    private final String displayName;

    ManagerType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * getDisplayName: the user-friendly display name
     */
    public String getDisplayName() {
        return displayName;
    }
    @Override
    public String toString() {
        return displayName;
    }
    
    /** 
     * @param displayName
     * @return converts a display name back to enum constant
     */
    public static ManagerType fromDisplayName(String displayName) {
        for (ManagerType type : values()) {
            if (type.displayName.equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant for display name: " + displayName);
    }
    
    /**
     * displayOptions
     * Prints available job titles in columns (included static method to list formatted options)
     */
    public static void displayOptions() {
        System.out.println("\nAvailable Job Titles:");
        int count = 1;
        for (ManagerType type : ManagerType.values()) {
            System.out.printf("%2d. %-25s", count++, type.getDisplayName());
            if (count % 3 == 1) System.out.println();
        }
        System.out.println();
    }
}
