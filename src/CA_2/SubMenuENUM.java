package CA_2;

/**
 *
 * @author Tainara de Souza Santos 
 * @student 2024561
 * Organises SUBmenu options for each main feature.
 * I structure the secondary navigation levels for sort/search/add 
 * operations and I included more functions I wanted for my application.
 * My design ensures consistent menu handling across all the features.
 */

public enum SubMenuENUM {
    /**
     * the submenu ENUM configuration
     */  
    SORT_FIRST_20(1, "Sort first 20 employee names"),
    SORT_BY_DEPARTMENT(2, "Sort ALL employees by department"),
    SORT_RETURN(3, "Return to main menu"),
    
    //Search submenu options
    SEARCH_FIRST_NAME(1, "Search employees by first name"),
    SEARCH_FULL_NAME(2, "Search employees by full name"),
    SEARCH_BY_DEPARTMENT(3, "Search employees by department"),
    SEARCH_RETURN(4, "Return to main menu"),
    
    //Add records submenu options
    ADD_EMPLOYEE(1, "Add new employee"),
    EDIT_EMPLOYEE(2, "Edit employee information"),
    GENERATE_ADD_RANDOM(3, "Generate and add random employee"),
    ADD_RETURN(4, "Return to main menu");
    
    private final int optionNumber;
    private final String description;
    
    SubMenuENUM(int optionNumber, String description) {
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
     * displaySubMenu: Shows context-specific submenu options
     */
    public static void displaySubMenu(MenuENUM mainMenuOption) {
        System.out.println("\n=== " + mainMenuOption.getDescription() + " ===");
        
        switch (mainMenuOption) {
            case SORT:
                printOptions(SORT_FIRST_20, SORT_BY_DEPARTMENT, SORT_RETURN);
                System.out.print("Select sort option (1-3): ");
                break;
            case SEARCH:
                printOptions(SEARCH_FIRST_NAME, SEARCH_FULL_NAME, SEARCH_BY_DEPARTMENT, SEARCH_RETURN);
                System.out.print("Select search option (1-4): ");
                break;
            case ADD_RECORDS:
                printOptions(ADD_EMPLOYEE, EDIT_EMPLOYEE, GENERATE_ADD_RANDOM, ADD_RETURN);
                System.out.print("Select add/edit option (1-4): ");
                break;
            default:
                throw new IllegalArgumentException("Invalid main menu option for submenu");
        }
    }
    
    /**
     * printOptions: Helper to print available submenu options
     */
    private static void printOptions(SubMenuENUM... options) {
        for (SubMenuENUM option : options) {
            System.out.printf("%d. %s%n", option.getOptionNumber(), option.getDescription());
        }
    }
    
    /**
     * SubMenuENUM fromOptionNumber: Maps user input to submenu choices
     */
    public static SubMenuENUM fromOptionNumber(int optionNumber, MenuENUM mainMenuOption) {
        switch (mainMenuOption) {
            case SORT:
                return getSubMenuOption(optionNumber, SORT_FIRST_20, SORT_BY_DEPARTMENT, SORT_RETURN);
            case SEARCH:
                return getSubMenuOption(optionNumber, SEARCH_FIRST_NAME, SEARCH_FULL_NAME, 
                                                      SEARCH_BY_DEPARTMENT, SEARCH_RETURN);
            case ADD_RECORDS:
                return getSubMenuOption(optionNumber, ADD_EMPLOYEE, EDIT_EMPLOYEE, 
                                                      GENERATE_ADD_RANDOM, ADD_RETURN);
            default:
                throw new IllegalArgumentException("Invalid main menu option");
        }
    }
    
    /**
     * SubMenuENUM getSubMenuOption: Validates and returns selected submenu item
     */
    private static SubMenuENUM getSubMenuOption(int optionNumber, SubMenuENUM... options) {
        for (SubMenuENUM option : options) {
            if (option.getOptionNumber() == optionNumber) {
                return option;
            }
        }
        throw new IllegalArgumentException("Invalid submenu option: " + optionNumber);
    }
}