package techcompanyapp;

/**
 *
 * @author Tainara
 */

//import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class EmployeeService {
    private static final Random random = new Random();

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

    
    public static void sortFirst20Names(List<Employee> employees) {
        int count = Math.min(employees.size(), 20);

        // Cria uma cópia dos primeiros 20 funcionários
        List<Employee> sorted = new ArrayList<>(employees.subList(0, count));

        // Ordena por primeiro nome
        recursiveMergeSort(sorted, Comparator.comparing(Employee::getFirstName));

        // Imprime os resultados ordenados
        System.out.println("First 20 Employees Sorted by Name:");
        for (Employee e : sorted) {
            System.out.printf("%s, %s, %s, %s, %s, %s%n",
                    e.getFirstName(), e.getLastName(), e.getEmail(),
                    e.getJobTitle(), e.getDepartment(), e.getCompany());
        }
    }

    
    /**
    private static void sortFirst20Names(List<Employee> employees) {
        List<Employee> sorted = employees.subList(0, Math.min(employees.size(), 20));
        recursiveMergeSort(sorted, Comparator.comparing(Employee::getFirstName)
                                          .thenComparing(Employee::getLastName));
        
        System.out.println("\nFirst 20 Employees Sorted by Name:");
        sorted.forEach(emp -> System.out.println(emp.getFirstName() + ", " + emp.getLastName() + ", " + emp.getEmail() + ", " + emp.getJobTitle() + ", " + emp.getDepartment() + ", " + emp.getCompany()));
        System.out.println("\n");
    }
    private static void sortFirst20Names(List<Employee> employees) {
        List<Employee> sorted = new ArrayList<>(employees.subList(0, Math.min(employees.size(), 20)));
        recursiveMergeSort(sorted, Comparator.comparing(Employee::getLastName)
                                          .thenComparing(Employee::getFirstName));

        System.out.println("\nFirst 20 Employees Sorted by Name:");
        System.out.println("====================================================================================================");
        System.out.printf("%-15s %-15s %-25s %-20s %-20s %-15s%n", 
                         "Last Name", "First Name", "Email", "Manager", "Department", "Company");
        System.out.println("----------------------------------------------------------------------------------------------------");

        sorted.forEach(emp -> System.out.printf("%-15s %-15s %-25s %-20s %-20s %-15s%n",
            emp.getLastName(),
            emp.getFirstName(),
            emp.getEmail(),
            emp.getJobTitle().getDisplayName(),
            emp.getDepartment().getDisplayName(),
            emp.getCompany()));

        System.out.println("====================================================================================================");
    }
 */
    
    
    private static void sortByDepartment(List<Employee> employees) {
        //create a temporary list "sortedEmployees" to do not modify the original list
        List<Employee> sortedEmployees = new ArrayList<>(employees);
        
        recursiveMergeSort(sortedEmployees, Comparator.comparing(Employee::getDepartment)
                           .thenComparing(Employee::getLastName));
        
        System.out.println("\nEmployees Sorted by Department:");
        Department currentDept = null;
        for (Employee emp : sortedEmployees) {
            if (!emp.getDepartment().equals(currentDept)) {
                currentDept = emp.getDepartment();
                System.out.println("\n=== " + currentDept.getDisplayName() + " ===");
            }
            //System.out.println(emp);
            //sortedEmployees.forEach(emp -> System.out.println(emp.getFirstName() + ", " + emp.getLastName() + ", " + emp.getEmail() + ", " + emp.getJobTitle() + ", " + emp.getDepartment() + ", " + emp.getCompany()));
            //System.out.println(emp.getFirstName() + ", " + emp.getLastName() + ", " + emp.getEmail() + ", " + emp.getJobTitle() + ", " + emp.getDepartment() + ", " + emp.getCompany());
            
            System.out.println("\n");
            
            /**
            System.out.printf("%-15s %-15s %-10s %-25s %-20s %-20s %-15s%n",
            emp.getFirstName(),
            emp.getLastName(),
            emp.getPosition() != null ? emp.getPosition() : "",
            emp.getJobTitle(),
            emp.getDepartment().getDisplayName(),
            emp.getCompany(),
            emp.getEmail()); */
        }
    }
 
    /**private static void sortByDepartment(List<Employee> employees) {   
        recursiveMergeSort(employees, Comparator.comparing(Employee::getDepartment)
                                             .thenComparing(Employee::getLastName));
        
        System.out.println("\nEmployees Sorted by Department:");
        Department currentDept = null;
        for (Employee emp : employees) {
            if (!emp.getDepartment().equals(currentDept)) {
                currentDept = emp.getDepartment();
                System.out.println("\n=== " + currentDept.getDisplayName() + " ===");
            }
            System.out.println(emp);
        }
    }
    
    private static void sortByDepartment(List<Employee> employees) {
        //create a temporary list to do not modify the original list
        List<Employee> sortedEmployees = new ArrayList<>(employees);

        //sord the copy
        recursiveMergeSort(sortedEmployees, 
            Comparator.comparing(Employee::getDepartment)
                     .thenComparing(Employee::getLastName)
                     .thenComparing(Employee::getFirstName));

        System.out.println("\nEmployees Sorted by Department:");
        System.out.println("====================================================================================================");
        System.out.printf("%-15s %-15s %-10s %-25s %-20s %-20s %-15s%n", 
                        "First Name", "Last Name", "Position", "Job Title", "Department", "Company", "Email");
        System.out.println("----------------------------------------------------------------------------------------------------");

        Department currentDept = null;
        for (Employee emp : sortedEmployees) {
            if (!emp.getDepartment().equals(currentDept)) {
                currentDept = emp.getDepartment();
                System.out.println("\n=== " + currentDept.getDisplayName() + " ===");
            }
            System.out.printf("%-15s %-15s %-10s %-25s %-20s %-20s %-15s%n",
                emp.getFirstName(),
                emp.getLastName(),
                emp.getPosition() != null ? emp.getPosition() : "",
                emp.getJobTitle().getDisplayName(),
                emp.getDepartment().getDisplayName(),
                emp.getCompany(),
                emp.getEmail());
        }
        System.out.println("====================================================================================================");
    }
    * 
    * */

    public static void recursiveMergeSort(List<Employee> employees, Comparator<Employee> comparator) {
        if (employees.size() <= 1) return;
        
        int mid = employees.size() / 2;
        List<Employee> left = employees.subList(0, mid);
        List<Employee> right = employees.subList(mid, employees.size());
        
        recursiveMergeSort(left, comparator);
        recursiveMergeSort(right, comparator);
        
        merge(employees, left, right, comparator);
    }

    private static void merge(List<Employee> employees, List<Employee> left, 
                            List<Employee> right, Comparator<Employee> comparator) {
        int i = 0, j = 0, k = 0;
        
        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                employees.set(k++, left.get(i++));
            } else {
                employees.set(k++, right.get(j++));
            }
        }
        
        while (i < left.size()) {
            employees.set(k++, left.get(i++));
        }
        
        while (j < right.size()) {
            employees.set(k++, right.get(j++));
        }
    }

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
                    System.out.println("Employee added successfully!");
                    break;
                case EDIT_EMPLOYEE:
                    editEmployeeMenu(employees, scanner);
                    break;
                case GENERATE_ADD_RANDOM:
                    Employee randomEmployee = generateRandomEmployee();
                    employees.add(randomEmployee);
                    System.out.println("Random employee generated: " + randomEmployee);
                    break;
                case ADD_RETURN:
                    inAddMenu = false;
                    break;
            }
        }
    }

    public static Employee createEmployeeFromInput(Scanner scanner) {
        System.out.println("\n=== Add New Employee ===");

        String firstName = InputValidator.validateName(scanner, "first name");
        String lastName = InputValidator.validateName(scanner, "last name");
        String gender = InputValidator.validateName(scanner, "gender (Male/Female/Other)");
        String email = InputValidator.validateEmail(scanner);
        double salary = InputValidator.validateSalary(scanner);
        Department department = InputValidator.validateDepartment(scanner);
        String position = InputValidator.validatePosition(scanner);
        ManagerType jobTitle = InputValidator.validateManagerType(scanner);
        String company = InputValidator.validateName(scanner, "company");

        return new Employee(firstName, lastName, gender, email, salary, 
                          department, position, jobTitle, company);
    }


    private static void editEmployeeMenu(List<Employee> employees, Scanner scanner) {
        System.out.print("\nEnter employee last name to edit: ");
        String lastName = scanner.nextLine();
        
        List<Employee> found = EmployeeSearch.searchByLastName(employees, lastName);
        if (found.isEmpty()) {
            System.out.println("No employees found with that last name.");
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
}
