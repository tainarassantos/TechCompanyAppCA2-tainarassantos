package techcompanyapp;

/**
 *
 * @author Tainara
 */

public enum ManagerType {
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

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static ManagerType fromDisplayName(String displayName) {
        for (ManagerType type : values()) {
            if (type.displayName.equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant for display name: " + displayName);
    }
    
    //included static method to list formatted options
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
