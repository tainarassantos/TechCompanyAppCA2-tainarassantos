
package techcompanyapp;

/**
 *
 * @author Tainara
 */
package techcompanyapp;

public enum MenuENUM {
    SORT(1, "SORT employees"),
    SEARCH(2, "SEARCH employees"),
    ADD_RECORDS(3, "ADD records"),
    GENERATE_RANDOM(4, "Generate random employee"),
    EXIT(5, "EXIT program");
    
    private final int optionNumber;
    private final String description;
    
    MenuENUM(int optionNumber, String description) {
        this.optionNumber = optionNumber;
        this.description = description;
    }
    
    public int getOptionNumber() {
        return optionNumber;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static MenuENUM fromOptionNumber(int optionNumber) {
        for (MenuENUM option : values()) {
            if (option.getOptionNumber() == optionNumber) {
                return option;
            }
        }
        throw new IllegalArgumentException("Invalid menu option: " + optionNumber);
    }
    
    public static void displayMainMenu() {
        System.out.println("\n=== Tech Company App ===");
        System.out.println("Main Menu:");
        for (MenuENUM option : values()) {
            System.out.printf("%d. %s%n", option.getOptionNumber(), option.getDescription());
        }
        System.out.print("Please select an option (1-5): ");
    }
}
