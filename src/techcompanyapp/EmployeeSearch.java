package techcompanyapp;

/**
 *
 * @author Tainara de Souza Santos 
 * @student 2024561
 * The class EmployeeSearch handles employee search operations using recursive binary search.
 * Integrates with the main menu system and displays formatted results.
 * I implemented sorting and searching algorithms to find employees by name or department.
 * I use the searchType parameter to determine which field to compare.
 * The binarySearchRecursive function is the only one that works for all types of searches (first name, full name, department)
 */

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class EmployeeSearch {
    /**
     * searchMenu is used to controls the search submenu and runs to specific search functions.
     */
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


    /** 
     * binarySearchRecursive function is the only one that works for all 
     * types of searches (first name, full name, department)
     * I adapted with the CA2-RESOURCES shared in Class to check the object EMPLOYEE.
    */ 
    public static int binarySearchRecursive(List<Employee> employees, String key, int left, int right, int searchType) {
        if (left > right) return -1;

        int mid = left + (right - left) / 2;
        Employee midEmp = employees.get(mid);
        String midValue = getCompareValue(midEmp, searchType);
        int comparison = key.compareToIgnoreCase(midValue);

        if (comparison == 0) {
            return mid;
        } else if (comparison < 0) {
            return binarySearchRecursive(employees, key, left, mid - 1, searchType);
        } else {
            return binarySearchRecursive(employees, key, mid + 1, right, searchType);
        }
    }

    /**
     * getCompareValue is helping to extract comparison values ​​(first name, full name or department) 
     * from Employee objects based on data from the Applicants_Form.txt file.
     */
    private static String getCompareValue(Employee emp, int searchType) {
        return switch (searchType) {
            case 1 -> emp.getFirstName(); //search first name
            case 2 -> emp.getFirstName() + " " + emp.getLastName(); //search first+last (full) name
            case 3 -> emp.getDepartment().getDisplayName(); //search department
            default -> "";
        };
    }

    /**
     * searchByFirstName will search for employees by name using binary search (requires sorted list).
     */
    public static void searchByFirstName(List<Employee> employees, Scanner scanner) {
        System.out.print("\nEnter first name to search: ");
        String firstName = scanner.nextLine().trim();
        
        //Sort before searching
        List<Employee> sorted = new ArrayList<>(employees);
        Collections.sort(sorted, Comparator.comparing(Employee::getFirstName));
        
        //Recursive binary search
        int pos = binarySearchRecursive(sorted, firstName, 0, sorted.size() - 1, 1);
        
        if (pos == -1) {
            System.out.println("Employee with first name '" + firstName + "' NOT found!");
        } else {
            System.out.println("Employee found at position " + pos + ":");
            System.out.println(sorted.get(pos));
        }
    }

    /**
     * searchByFullName will search for employees by full name 
     * in FIRST+LAST name order using recursive binary search.
     */
    public static void searchByFullName(List<Employee> employees, Scanner scanner) {
        System.out.print("\nEnter full name (First Last): ");
        String fullName = scanner.nextLine().trim();
        
        //Sort before searching
        List<Employee> sorted = new ArrayList<>(employees);
        Collections.sort(sorted, Comparator.comparing(Employee::getFirstName)
                                        .thenComparing(Employee::getLastName));
        
        //Recursive binary search
        int pos = binarySearchRecursive(sorted, fullName, 0, sorted.size() - 1, 2);
        
        if (pos == -1) {
            System.out.println("Employee '" + fullName + "' NOT found!");
        } else {
            System.out.println("Employee found at position " + pos + ":");
            System.out.println(sorted.get(pos));
        }
    }
    
    /**
     * searchByDepartment will search and list all employees in a specific department using 
     * binary search (department name must be one of the list, otherwise it will not search).
     * Here is a future improvement to compute any name in the department field.
     */
    public static void searchByDepartment(List<Employee> employees, Scanner scanner) {
        Department department = InputValidator.validateDepartment(scanner);
        
        //Sort before searching
        List<Employee> sorted = new ArrayList<>(employees);
        Collections.sort(sorted, Comparator.comparing(e -> e.getDepartment().getDisplayName()));
        
        //Recursive binary search
        int pos = binarySearchRecursive(sorted, department.getDisplayName(), 0, sorted.size() - 1, 3);
        
        if (pos == -1) {
            System.out.println("No employees found in " + department.getDisplayName() + " department.");
        } else {
            System.out.println("\nEmployees in " + department.getDisplayName() + " department:");
            //Find all employees in the same department (there may be several)
            int start = pos;
            while (start > 0 && sorted.get(start-1).getDepartment() == department) {
                start--;
            }            
            int end = pos;
            while (end < sorted.size()-1 && sorted.get(end+1).getDepartment() == department) {
                end++;
            }            
            for (int i = start; i <= end; i++) {
                System.out.println(sorted.get(i));
            }
        }
    }

    /**
     * displaySearchResults is the function that helps formating 
     * the data correctly and display the search results consistently.
     */
    private static void displaySearchResults(List<Employee> employees, String title) {
        System.out.println("\n=== " + title + " ===");
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        
        employees.forEach(emp -> System.out.printf("%-15s %-15s | %-20s | %s%n",
            emp.getFirstName(),
            emp.getLastName(),
            emp.getJobTitle().getDisplayName(),
            emp.getDepartment().getDisplayName()));
    }    
}
    
    
    
    /**
     * 
     * OLD CODE
     
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
    
   