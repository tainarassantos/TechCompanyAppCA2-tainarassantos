package techcompanyapp;

/**
 *
 * @author Tainara de Souza Santos 
 * @student 2024561
 * Main application class controlling program flow.
 * I manage the core application loop, handle menu navigation, and coordinate data loading/saving.
 * All user interactions start from this central controller. 
 */

import java.util.List;
import java.util.Scanner;

public class Main {
    //set the default data file name
    private static final String FILE_NAME = "Applicants_Form.txt";
    
    private static List<Employee> employees;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Welcome to Tech Company App ===");
        
        // Step 1: Load data from file
        loadEmployeeData();
        
        // Step 2: Main menu loop
        boolean running = true;
        while (running) {
            MenuENUM.displayMainMenu();
            
            try {
                int choice = InputValidator.validateMenuChoice(scanner);
                MenuENUM selectedOption = MenuENUM.fromOptionNumber(choice);
                
                switch (selectedOption) {
                    case SORT:
                        handleSortMenu();
                        break;
                    case SEARCH:
                        handleSearchMenu();
                        break;
                    case ADD_RECORDS:
                        handleAddRecordsMenu();
                        break;
                    case EXIT:
                        running = false;
                        System.out.println("\nThank you. Exiting the Tech Company system!");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    /**
     * loadEmployeeData: Loads employee data from file at program start
     */
    private static void loadEmployeeData() {
        System.out.println("\nLoading employee data from file: " + FILE_NAME);
        employees = FileHandler.loadFromFile(FILE_NAME);
        
        if (employees == null || employees.isEmpty()) {
            System.out.println("Warning: No employee data loaded or file is empty.");
            System.out.println("The system will start with an empty employee list.");
            System.out.println("You can add new employees through the menu.");
        } else {
            System.out.println("Successfully loaded " + employees.size() + " employee records.");
        }
    }
    
    /**
     * handleSortMenu: Handles the sorting menu selection and execution
     */
    private static void handleSortMenu() {
        System.out.println("\nSort Menu Selected");
        EmployeeService.sortMenu(employees, scanner);
    }
    
    /**
     * handleSearchMenu: Manage the search menu selection and processing
     */
    private static void handleSearchMenu() {
        System.out.println("\nSearch Menu Selected");
        EmployeeSearch.searchMenu(employees, scanner);
    }
    
    /**
     * handleAddRecordsMenu: Controls add/edit records functionality
     */
    private static void handleAddRecordsMenu() {
        System.out.println("\nAdd/Edit Records Menu Selected");
        EmployeeService.addRecordsMenu(employees, scanner);
        
        //Save changes to file after modifications - I CHANGED TO SAVE ON addRecordsMenu
        //FileHandler.saveToFile(FILE_NAME, employees);
        /**PLAN B:
        boolean saved = FileHandler.saveToFile(FILE_NAME, employees);
            if (!saved) {
                System.out.println("Warning: Could not save changes to file!");
            }
        */
    }
    
    
}