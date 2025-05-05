package techcompanyapp;

/**
 *
 * @author Tainara
 */
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


public class EmployeeSearch {
    public static void searchMenu(List<Employee> employees, Scanner scanner) {
        boolean inSearchMenu = true;
        while (inSearchMenu) {
            SubMenuENUM.displaySubMenu(MenuENUM.SEARCH);
            int choice = InputValidator.validateSubMenuChoice(scanner, MenuENUM.SEARCH);
            SubMenuENUM searchOption = SubMenuENUM.fromOptionNumber(choice, MenuENUM.SEARCH);

            switch (searchOption) {
                case SEARCH_FIRST_NAME:
                    searchByFirstName(employees, scanner);
                    break;
                case SEARCH_FULL_NAME:
                    searchByFullName(employees, scanner);
                    break;
                case SEARCH_BY_DEPARTMENT:
                    searchByDepartment(employees, scanner);
                    break;
                case SEARCH_RETURN:
                    inSearchMenu = false;
                    break;
            }
        }
    }

    public static void searchByFullName(List<Employee> employees, Scanner scanner) {
        System.out.print("\nEnter full name (First Last): ");
        String fullName = scanner.nextLine().trim();
        
        // Ordena a lista antes da busca
        List<Employee> sorted = new ArrayList<>(employees);
        EmployeeService.sortWithInsertionSort(sorted,
            Comparator.comparing(Employee::getLastName)
                     .thenComparing(Employee::getFirstName));
        
        // Busca binária
        Employee found = binarySearchRecursive(sorted, fullName, 0, sorted.size()-1);
        
        if (found != null) {
            System.out.println("\n=== Employee Found ===");
            System.out.println("Name: " + found.getFirstName() + " " + found.getLastName());
            System.out.println("Title: " + found.getJobTitle().getDisplayName());
            System.out.println("Department: " + found.getDepartment().getDisplayName());
            System.out.println("Email: " + found.getEmail());
        } else {
            System.out.println("\nEmployee not found.");
        }
    }
   
    // BINARY SEARCH recursivo
    public static Employee binarySearchRecursive(List<Employee> sortedEmployees, 
                                              String fullName, int left, int right) {
        if (left > right) return null;
        
        int mid = left + (right - left) / 2;
        Employee midEmp = sortedEmployees.get(mid);
        String midFullName = midEmp.getFirstName() + " " + midEmp.getLastName();
        
        int comparison = fullName.compareToIgnoreCase(midFullName);
        if (comparison == 0) {
            return midEmp;
        } else if (comparison < 0) {
            return binarySearchRecursive(sortedEmployees, fullName, left, mid - 1);
        } else {
            return binarySearchRecursive(sortedEmployees, fullName, mid + 1, right);
        }
    }

    
    
    
     /** public static void searchByFullName(List<Employee> employees, Scanner scanner) {
        System.out.print("\nEnter last name to search: ");
        String lastName = scanner.nextLine().trim().toLowerCase();
        
        List<Employee> found = searchByLastName(employees, lastName);
        if (found.size() == 1) {
            System.out.println("\nEmployee found:");
            System.out.println(found.get(0));
            return;
        }
        
        displaySearchResults(found, "Employees with last name containing '" + lastName + "'");
    }

    public static void searchByFirstName(List<Employee> employees, Scanner scanner) {
        System.out.print("\nEnter first name to search: ");
        String firstName = scanner.nextLine().trim().toLowerCase();
        
        List<Employee> found = employees.stream()
            .filter(e -> e.getFirstName().toLowerCase().contains(firstName))
            .toList();
        
        displaySearchResults(found, "Employees with first name containing '" + firstName + "'");
    }

    

    public static List<Employee> searchByLastName(List<Employee> employees, String lastName) {
        return employees.stream()
            .filter(e -> e.getLastName().toLowerCase().contains(lastName.toLowerCase()))
            .toList();
    }

    public static void searchByDepartment(List<Employee> employees, Scanner scanner) {
        Department department = InputValidator.validateDepartment(scanner);
        
        List<Employee> found = employees.stream()
            .filter(e -> e.getDepartment() == department)
            .toList();
        
        displaySearchResults(found, "Employees in " + department.getDisplayName() + " department");
    }

    private static void displaySearchResults(List<Employee> employees, String title) {
        System.out.println("\n=== " + title + " ===");
        System.out.println("Found " + employees.size() + " employees:");
        
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        
        // Group by department for better organization
        employees.stream()
            .sorted(Comparator.comparing(Employee::getDepartment)
                             .thenComparing(Employee::getLastName))
            .forEach(emp -> System.out.printf("%-15s %-15s | %-20s | %s%n",
                emp.getFirstName(),
                emp.getLastName(),
                emp.getJobTitle().getDisplayName(),
                emp.getDepartment().getDisplayName()));
    }

    public static Employee binarySearchByFullName(List<Employee> sortedEmployees, String fullName) {
        int left = 0;
        int right = sortedEmployees.size() - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Employee midEmp = sortedEmployees.get(mid);
            String midFullName = midEmp.getFirstName() + " " + midEmp.getLastName();
            
            int comparison = fullName.compareToIgnoreCase(midFullName);
            if (comparison == 0) {
                return midEmp;
            } else if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return null;
    } */
    
    
}