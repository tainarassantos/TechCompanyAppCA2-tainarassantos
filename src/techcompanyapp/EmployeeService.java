package techcompanyapp;

/**
 *
 * @author Tainara de Souza Santos 
 * @student 2024561
 * Handles all employee data operations including sorting(Recursive Insertion Sort), adding(input data and random), and editing (extra in my application).
 * I manage employee records and implement sorting algorithms to organise the company data.
 * My methods work with the main system to keep employee information up-to-date.
 */

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class EmployeeService {
    private static final Random random = new Random();

    /**
     * SORTING OPERATIONS > sortMenu
     * Controls the sorting SUBmenu navigation. 
     * I display sorting options and delegate to specific sort methods
     */
    public static void sortMenu(List<Employee> employees, Scanner scanner) {
        boolean inSortMenu = true;
        while (inSortMenu) {
            SubMenuENUM.displaySubMenu(MenuENUM.SORT);
            int choice = InputValidator.validateSubMenuChoice(scanner, MenuENUM.SORT);
            SubMenuENUM sortOption = SubMenuENUM.fromOptionNumber(choice, MenuENUM.SORT);

            switch (sortOption) {
                case SORT_FIRST_20:
                    sortFirst20Names(employees);
                    break;
                case SORT_BY_DEPARTMENT:
                    sortByDepartment(employees);
                    break;
                case SORT_RETURN:
                    inSortMenu = false;
                    break;
            }
        }
    }

    /**
     * SORTING OPERATIONS > sortFirst20Names
     * Sorts and displays first 20 employee names alphabetically.
     * I used the recursive insertion sort for efficient organization.
     */
    public static void sortFirst20Names(List<Employee> employees) {
        int count = Math.min(employees.size(), 20);
        List<Employee> first20 = new ArrayList<>(employees.subList(0, count));
        
        sortWithInsertionSort(first20, 
            Comparator.comparing(Employee::getFirstName)
                     .thenComparing(Employee::getLastName));

        System.out.println("\n=== First 20 Employees Sorted by Name (All Fields) ===");
        System.out.println("==========================================");
                
        System.out.printf("%-12s %-12s %-8s %-25s %-10s %-20s %-20s %-25s %-15s%n", 
                            "First Name", "Last Name", "Gender", "Email", "Salary", "Department", 
                            "Position", "Department", "Company");
        System.out.println("------------------------------------------------------------------------------------");

        //printing the selected employee data        
        first20.forEach(emp -> System.out.printf("%-12s %-12s %-8s %-25s €%-9.2f %-20s %-20s %-25s %-15s%n",
            emp.getFirstName(),
            emp.getLastName(),
            emp.getGender(),
            emp.getEmail(),
            emp.getSalary(),
            emp.getDepartment().getDisplayName(),
            emp.getPosition() != null ? emp.getPosition() : "N/A",
            emp.getJobTitle().getDisplayName(),
            emp.getCompany()));

        System.out.println("====================================================================================");
    }
        
    /**
     * SORTING OPERATIONS > sortByDepartment
     * Sorts employees by their specific department. I group employees department-wise for better analysis. 
     * (I include these extra function because I wanted to learn how show data per one specific field of the database)
     */
    public static void sortByDepartment(List<Employee> employees) {
        List<Employee> sorted = new ArrayList<>(employees);
        //sortWithInsertionSort(sorted,
        //    Comparator.comparing(e -> e.getDepartment().getDisplayName(),
        //        String.CASE_INSENSITIVE_ORDER));
        //Sort by department name and then by first/last name
        sortWithInsertionSort(sorted,
        Comparator.comparing((Employee e) -> e.getDepartment().getDisplayName(), 
                           String.CASE_INSENSITIVE_ORDER)
                 .thenComparing(Employee::getFirstName)
                 .thenComparing(Employee::getLastName));

        System.out.println("\n=== Employees Sorted by Department ===");
        System.out.println("===============================================================================================================");
        System.out.printf("%-15s %-15s %-10s %-25s %-20s %-20s %-15s%n",
            "First Name", "Last Name", "Position", "Job Title", "Department", "Company", "Email");
        System.out.println("---------------------------------------------------------------------------------------------------------------");

        Department currentDept = null;
        for (Employee emp : sorted) {
            //shows the department name on top
            if (!emp.getDepartment().equals(currentDept)) {
                currentDept = emp.getDepartment();
                System.out.printf("\n=== %s ===%n", currentDept.getDisplayName().toUpperCase());
            }

            //print the employee data
            System.out.printf("%-15s %-15s %-10s %-25s %-20s %-20s %-15s%n",
                emp.getFirstName(),
                emp.getLastName(),
                emp.getPosition() != null ? emp.getPosition() : "N/A",
                emp.getJobTitle().getDisplayName(),
                currentDept.getDisplayName(),  //currentDept updated
                emp.getCompany(),
                emp.getEmail());
        }
        System.out.println("===============================================================================================================");
    
        /**System.out.println("\n=== Employees Sorted by Department ===");
        Department currentDept = null;
        for (Employee emp : sorted) {
            if (!emp.getDepartment().equals(currentDept)) {
                currentDept = emp.getDepartment();
                System.out.println("\n--- " + currentDept.getDisplayName() + " ---");
            }
            System.out.printf("%-15s %-15s | %-20s%n",
                emp.getFirstName(),
                emp.getLastName(),
                emp.getJobTitle().getDisplayName());
        }*/
    }

    
    /** Core Algorithm */
    /**
     * recursiveInsertionSort recursively sorts employees using insertion sort.
    * I compare elements and arrange them in order step-by-step.
     */
    public static void recursiveInsertionSort(List<Employee> employees, 
                                           Comparator<Employee> comparator, int n) {
        if (n <= 1) return;
        
        recursiveInsertionSort(employees, comparator, n-1);
        
        Employee last = employees.get(n-1);
        int j = n-2;
        
        while (j >= 0 && comparator.compare(employees.get(j), last) > 0) {
            employees.set(j+1, employees.get(j));
            j--;
        }
        employees.set(j+1, last);
    }

    /**
     * sortWithInsertionSort starts the recursive sorting process (Wrapper para o insertion sort).
    * I prepare the list for sorting with the given comparator
     */
    public static void sortWithInsertionSort(List<Employee> employees, 
                                          Comparator<Employee> comparator) {
        recursiveInsertionSort(employees, comparator, employees.size());
    }
    
    
    /** Employee Management */
    /**
     * addRecordsMenu manages the employee add/edit submenu.
     * I handle new entries, edits, and random generation .
     */
    public static void addRecordsMenu(List<Employee> employees, Scanner scanner) {
        boolean inAddMenu = true;
        while (inAddMenu) {
            SubMenuENUM.displaySubMenu(MenuENUM.ADD_RECORDS);
            int choice = InputValidator.validateSubMenuChoice(scanner, MenuENUM.ADD_RECORDS);
            SubMenuENUM addOption = SubMenuENUM.fromOptionNumber(choice, MenuENUM.ADD_RECORDS);

            switch (addOption) {
                case ADD_EMPLOYEE:
                    Employee newEmployee = createEmployeeFromInput(scanner);
                    employees.add(newEmployee);
                    //save the add employee on the file
                    if (FileHandler.saveToFile("Applicants_Form.txt", employees)) {
                        System.out.println("Employee added successfully!");
                    } else {
                        System.out.println("ERROR: Unable to save new employee to file!");
                    }
                    break;
                case EDIT_EMPLOYEE:
                    editEmployeeMenu(employees, scanner);
                    //save the EDIT employee on the file
                    if (FileHandler.saveToFile("Applicants_Form.txt", employees)) {
                        System.out.println("Employee edited successfully!");
                    } else {
                        System.out.println("ERROR: Unable to edit the employee to file!");
                    }
                    break;
                case GENERATE_ADD_RANDOM:
                    Employee randomEmployee = generateRandomEmployee();
                    employees.add(randomEmployee);
                    //System.out.println("Random employee generated: " + randomEmployee);
                    //save the RANDOM employee on the file
                    if (FileHandler.saveToFile("Applicants_Form.txt", employees)) {
                        System.out.println("Random employee generated successfully: " + randomEmployee);
                    } else {
                        System.out.println("ERROR: Random employee generated but unable to save to file!");
                    }                    
                    break;
                case ADD_RETURN:
                    inAddMenu = false;
                    break;
            }
        }
    }
    
    /**
     * createEmployeeFromInput creates new employees from user input
     * I validate all fields before creating records.
     */
    public static Employee createEmployeeFromInput(Scanner scanner) {
        System.out.println("\n=== Add New Employee ===");

        String firstName = InputValidator.validateName(scanner, "first name");
        String lastName = InputValidator.validateName(scanner, "last name");
        String gender = InputValidator.validateGender(scanner);
        String email = InputValidator.validateEmail(scanner);
        double salary = InputValidator.validateSalary(scanner);
        Department department = InputValidator.validateDepartment(scanner);
        String position = InputValidator.validatePosition(scanner);
        ManagerType jobTitle = InputValidator.validateManagerType(scanner);
        String company = InputValidator.validateCompany(scanner);
        
        //extra validation to ensure critical fields are not null
        if (position == null || position.isEmpty()) {
            position = "Not specified";
        }
        
        return new Employee(firstName, lastName, gender, email, salary, 
                          department, position, jobTitle, company);
    }

    /**
     * editEmployeeMenu guides through employee selection for editing
     * I help users find and choose which record to modify
     */
    private static void editEmployeeMenu(List<Employee> employees, Scanner scanner) {
        System.out.print("\nEnter employee first name to edit: ");
        String firstName = scanner.nextLine();
        
        List<Employee> found = EmployeeSearch.searchByFirstName(employees, firstName); 
        if (found.isEmpty()) {
            System.out.println("No employees found with that first name.");
            return;
        }
        
        System.out.println("\nFound Employees:");
        for (int i = 0; i < found.size(); i++) {
            System.out.printf("%d. %s%n", i+1, found.get(i));
        }
        
        System.out.print("Select employee to edit (1-" + found.size() + "): ");
        int selection = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        if (selection < 1 || selection > found.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        
        editEmployee(found.get(selection-1), scanner);
    }
    
    /**
     * generateRandomEmployee generates random employee data for testing
     * I create realistic mock data using predefined values
     */
    public static Employee generateRandomEmployee() {
        String[] firstNames = {"John", "Isa", "David", "Tainara", "Robert", "Emily", "Mark", "Sarah"};
        String[] lastNames = {"Parnell", "Johnson", "Cuddy", "Souza", "Williams", "OConnel", "Jones"};
        
        return new Employee(
            firstNames[random.nextInt(firstNames.length)],
            lastNames[random.nextInt(lastNames.length)],
            random.nextBoolean() ? "Male" : "Female",
            "email" + random.nextInt(1000) + "@company.com",
            30000 + random.nextInt(70000),
            Department.values()[random.nextInt(Department.values().length)],
            new String[]{"junior", "middle", "senior"}[random.nextInt(3)],
            ManagerType.values()[random.nextInt(ManagerType.values().length)],
            "TechInnovators"
        );
    }
    
    
    
    /** Editing Functions */
    /**
     * editEmployee Modifies existing employee details
     * I safely update only fields that users choose to change
     */
    private static void editEmployee(Employee emp, Scanner scanner) {
        System.out.println("\nEditing employee: " + emp.getFirstName() + " " + emp.getLastName());        
        //System.out.println("\nEditing employee: " + emp);
        System.out.println("Leave field blank to keep current value.");
        
        System.out.print("Gender [" + emp.getGender() + "]: ");
        String gender = scanner.nextLine();
        if (!gender.isEmpty()) emp.setGender(gender);
        
        System.out.print("Email [" + emp.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) emp.setEmail(email);
        
        System.out.print("Salary [" + emp.getSalary() + "]: ");
        String salaryStr = scanner.nextLine();
        if (!salaryStr.isEmpty()) emp.setSalary(Double.parseDouble(salaryStr));
        
        System.out.print("Position [" + emp.getPosition() + "]: ");
        String position = scanner.nextLine();
        if (!position.isEmpty()) emp.setPosition(position);
        
        System.out.println("Current department: " + emp.getDepartment());
        if (InputValidator.getYesNoInput(scanner, "Change department? (y/n): ")) {
            emp.setDepartment(InputValidator.validateDepartment(scanner));
        }
        
        System.out.println("Current job title: " + emp.getJobTitle());
        if (InputValidator.getYesNoInput(scanner, "Change job title? (y/n): ")) {
            emp.setJobTitle(InputValidator.validateManagerType(scanner));
        }
        
        System.out.print("Company [" + emp.getCompany() + "]: ");
        String company = scanner.nextLine();
        if (!company.isEmpty()) emp.setCompany(company);
        
        System.out.println("Employee updated successfully!");
    }

}
