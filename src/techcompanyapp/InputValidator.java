package techcompanyapp;

/**
 *
 * @author Tainara
 */

import java.util.Scanner;

public class InputValidator {
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
    
    
    public static ManagerType validateManagerType(Scanner scanner) {
        System.out.println("\nAvailable positions:");
        for (ManagerType type : ManagerType.values()) {
            System.out.println("- " + type.name());
        }
        
        while (true) {
            System.out.print("Enter position: ");
            String input = scanner.nextLine().trim().toUpperCase().replace(" ", "_").replace("-", "_");
            try {
                return ManagerType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid position. Please choose from the list above.");
            }
        }
    }
    
  
}
