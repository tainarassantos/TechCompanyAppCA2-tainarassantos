package techcompanyapp;

/**
 *
 * @author Tainara de Souza Santos 
 * @student 2024561
 * Defines the main menu structure using ENUMs.
 * I provide the navigation options for the primary application features.
 * Each ENUM constant represents a selectable menu action (I used the class examples to create the ENUM menu).
 */

public enum MenuENUM {
    /**
     * The main menu ENUM configuration
     */    
    SORT(1, "SORT employees"),
    SEARCH(2, "SEARCH employees"),
    ADD_RECORDS(3, "ADD records"),
    //GENERATE_RANDOM(4, "Generate random employee"),
    EXIT(4, "EXIT program"); //before: EXIT(5, "EXIT program");
    
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
    
    /**
     * fromOptionNumber
     * convert the number input to corresponding menu option
     */
    public static MenuENUM fromOptionNumber(int optionNumber) {
        for (MenuENUM option : values()) {
            if (option.getOptionNumber() == optionNumber) {
                return option;
            }
        }
        throw new IllegalArgumentException("Invalid menu option, use a numeric input: " + optionNumber);
    }
    
    /**
     * displayMainMenu
     * print the menu structure for the user choose the option
     */
    public static void displayMainMenu() {
        System.out.println("\n=== Tech Company App ===");
        System.out.println("Main Menu:");
        for (MenuENUM option : values()) {
            System.out.printf("%d. %s%n", option.getOptionNumber(), option.getDescription());
        }
        System.out.print("Please select an option (1-5): ");
    }
}
