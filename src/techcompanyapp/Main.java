package techcompanyapp;

/**
 *
 * @author Tainara de Souza Santos
 * 
 */


import java.util.List;
import java.util.Scanner;

public class Main {
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
                    case GENERATE_RANDOM:
                        handleGenerateRandom();
                        break;
                    case EXIT:
                        running = false;
                        System.out.println("Thank you. Exiting the Tech Company system!");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
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
    
    private static void handleSortMenu() {
        System.out.println("\nSort Menu Selected");
        EmployeeService.sortMenu(employees, scanner);
    }
    
    private static void handleSearchMenu() {
        System.out.println("\nSearch Menu Selected");
        EmployeeSearch.searchMenu(employees, scanner);
    }
    
    private static void handleAddRecordsMenu() {
        System.out.println("\nAdd/Edit Records Menu Selected");
        EmployeeService.addRecordsMenu(employees, scanner);
        
        // Save changes to file after modifications
        FileHandler.saveToFile(FILE_NAME, employees);
    }
    
    private static void handleGenerateRandom() {
        System.out.println("\nGenerate Random Employee Selected");
        Employee newEmployee = EmployeeService.generateRandomEmployee();
        employees.add(newEmployee);
        System.out.println("Generated new employee: " + newEmployee);
        
        // Save changes to file
        FileHandler.saveToFile(FILE_NAME, employees);
    }
}