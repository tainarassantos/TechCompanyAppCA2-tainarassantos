package techcompanyapp;

/**
 *
 * @author Tainara de Souza Santos 
 * @student 2024561
 * Validates all user inputs throughout the application.
 * I ensure data integrity by checking menu choices and employee details.
 */

import java.util.Scanner;

public class InputValidator {
    /**
     * Validates main menu selection (user choise)
     */
    public static int validateMenuChoice(Scanner scanner) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= MenuENUM.values().length) {
                    return choice;
                }
                System.out.print("Invalid choice. Please enter a number between 1-" 
                    + MenuENUM.values().length + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    
    /**
     * validateSubMenuChoice is the function to checks submenu selection range 
     */
    public static int validateSubMenuChoice(Scanner scanner, MenuENUM mainMenuOption) {
        int maxOption = 0;
        switch (mainMenuOption) {
            case SORT: maxOption = 3; break;
            case SEARCH: 
            case ADD_RECORDS: maxOption = 4; break;
            default: throw new IllegalArgumentException("Invalid main menu option");
        }
        
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= maxOption) {
                    return choice;
                }
                System.out.print("Invalid choice. Please enter a number between 1-" + maxOption + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    
    /**
     * validateName Ensures names input aren't empty 
     */
    public static String validateName(Scanner scanner, String fieldName) {
        while (true) {
            System.out.print("Enter " + fieldName + ": ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println(fieldName + " cannot be empty. Please try again.");
        }
    }
    
    /**
     * validateSalary validates if the salary input is numeric 
     */
    public static double validateSalary(Scanner scanner) {
        while (true) {
            System.out.print("Enter salary: ");
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary. Please enter a valid number.");
            }
        }
    }
    
    /**
     * validateDepartment confirms department exists/is valid
     */
    public static Department validateDepartment(Scanner scanner) {
        System.out.println("\nAvailable departments:");
        for (Department dept : Department.values()) {
            System.out.println("- " + dept.name());
        }
        
        while (true) {
            System.out.print("Enter department: ");
            String input = scanner.nextLine().trim().toUpperCase().replace(" ", "_");
            try {
                return Department.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid department. Please choose from the list above.");
            }
        }
    }
    
    /**
     * validatePosition validate the employee position level according with the options
     */
    public static String validatePosition(Scanner scanner) {
        System.out.println("\nCommon position levels (you can enter any value):");
        System.out.println("- senior\n- middle\n- junior\n- intern\n- contract");
        System.out.print("Enter position level (optional, press Enter to skip): ");
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? null : input;
    }
    
    /**
     * validateManagerType verifies job title/manager selection
     */
    public static ManagerType validateManagerType(Scanner scanner) {
        System.out.println("\nAvailable positions:");
        for (ManagerType type : ManagerType.values()) {
            System.out.printf("- %s (%s)%n", type.name(), type.getDisplayName());
        }

        while (true) {
            System.out.print("Enter position name or display name: ");
            String input = scanner.nextLine().trim();

            try {
                //Try to find both by enum name and by displayName
                for (ManagerType type : ManagerType.values()) {
                    if (type.name().equalsIgnoreCase(input.replace(" ", "_").replace("-", "_")) || 
                        type.getDisplayName().equalsIgnoreCase(input)) {
                        return type;
                    }
                }
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid position. Please choose from the list above.");
            }
        }
    }
}
