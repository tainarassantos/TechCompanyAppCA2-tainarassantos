package CA_2;

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
     * searchByFirstName  search for employees by name using binary search (requires sorted list).
     * @param employees List of employees (should be sorted by first name)
     * @param firstName First name to search for
     * @return List of matching employees (may be empty if none found)
     * UPDATE IN 6/5/25: I change the method searchByFirstName and I include searchByFirstNameUI
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
    public static void searchByFirstName(List<Employee> employees, Scanner scanner) {
        System.out.print("\nEnter first name to search: ");
        String firstName = scanner.nextLine().trim();

        // 1. Ordena a lista pelo primeiro nome
        List<Employee> sorted = new ArrayList<>(employees);
        Collections.sort(sorted, Comparator.comparing(Employee::getFirstName));

        // 2. Executa a busca binária
        int pos = binarySearchRecursive(sorted, firstName, 0, sorted.size() - 1, 1);

        // 3. Processa resultados
        if (pos == -1) {
            System.out.println("No employees found with first name: " + firstName);
        } else {
            // Encontra todos os empregados com o mesmo nome (pode haver múltiplos)
            List<Employee> results = new ArrayList<>();
            results.add(sorted.get(pos));

            // Verifica elementos à esquerda (nomes iguais)
            int left = pos - 1;
            while (left >= 0 && sorted.get(left).getFirstName().equalsIgnoreCase(firstName)) {
                results.add(sorted.get(left));
                left--;
            }

            // Verifica elementos à direita (nomes iguais)
            int right = pos + 1;
            while (right < sorted.size() && sorted.get(right).getFirstName().equalsIgnoreCase(firstName)) {
                results.add(sorted.get(right));
                right++;
            }

            displaySearchResults(results, "Employees with first name '" + firstName + "'");
        }
    }
    /**
    public static List<Employee> searchByFirstName(List<Employee> employees, String firstName) {
        if (firstName == null || firstName.trim().isEmpty() || employees.isEmpty()) {
            return Collections.emptyList();
        }
        //Make a copy to avoid modifying the original list
        List<Employee> sorted = new ArrayList<>(employees);

        //Ensure the list is sorted by first name
        Collections.sort(sorted, Comparator.comparing(Employee::getFirstName));

        //Binary search to find first occurrence
        int pos = binarySearchRecursive(sorted, firstName.trim(), 0, sorted.size() - 1, 1);

        if (pos == -1) {
            return Collections.emptyList();
        }

        //Find all matching employees (since there might be multiple with same first name)
        List<Employee> result = new ArrayList<>();
        result.add(sorted.get(pos));

        //Check previous elements
        int left = pos - 1;
        while (left >= 0 && sorted.get(left).getFirstName().equalsIgnoreCase(firstName)) {
            result.add(sorted.get(left));
            left--;
        }

        //Check next elements
        int right = pos + 1;
        while (right < sorted.size() && sorted.get(right).getFirstName().equalsIgnoreCase(firstName)) {
            result.add(sorted.get(right));
            right++;
        }

        return result;
    }

    */

    
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
    
    
    

    
   