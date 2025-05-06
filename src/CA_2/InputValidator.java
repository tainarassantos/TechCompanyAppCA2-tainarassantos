package CA_2;

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
     * validateName Ensures names input:
     * - Does not accept numbers or special characters
     * - Converts to "First letter uppercase and remaining lowercase" format
     * - Ensures that the field is not empty
     */
    public static String validateName(Scanner scanner, String fieldName) {
        while (true) {
            System.out.print("Enter " + fieldName + ": ");
            String input = scanner.nextLine().trim();

            // Verifica se está vazio
            if (input.isEmpty()) {
                System.out.println(fieldName + " cannot be empty. Please try again.");
                continue;
            }

            // Verifica se contém apenas letras e espaços (para nomes compostos)
            if (!input.matches("^[\\p{L} .'-]+$")) {
                System.out.println("Invalid " + fieldName + ". Only letters and spaces are allowed.");
                continue;
            }

            //the formatName function format the name correctly before including it in the system (First letter capitalized, the rest lowercase)
            String formattedName = formatName(input);

            return formattedName;
        }
        /**while (true) {
            System.out.print("Enter " + fieldName + ": ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println(fieldName + " cannot be empty. Please try again.");
        }*/
    }
    
    /**
    * formatName - Helps with the "First letter capitalized and the rest lowercase" format
    * Correctly handles compound names (such as "Mary O'Connor")
    */
    private static String formatName(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }

        String[] parts = name.split("\\s+");
        StringBuilder formattedName = new StringBuilder();

        for (String part : parts) {
            if (!part.isEmpty()) {
                //Treats cases like O'Connor, D'Angelo (traditional in Ireland)
                if (part.contains("'") || part.contains("-")) {
                    String[] subParts = part.split("['-]");
                    StringBuilder tempPart = new StringBuilder();

                    for (int i = 0; i < subParts.length; i++) {
                        if (!subParts[i].isEmpty()) {
                            String formattedPart = subParts[i].substring(0, 1).toUpperCase() + 
                                                  subParts[i].substring(1).toLowerCase();
                            tempPart.append(formattedPart);
                            if (i < subParts.length - 1) {
                                tempPart.append(part.contains("'") ? "'" : "-");
                            }
                        }
                    }
                    part = tempPart.toString();
                } else {
                    part = part.substring(0, 1).toUpperCase() + 
                          part.substring(1).toLowerCase();
                }

                if (formattedName.length() > 0) {
                    formattedName.append(" ");
                }
                formattedName.append(part);
            }
        }
        return formattedName.toString();
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
    
    /**
    * validateEmail checks if the email has a valid format (contains @ and .)
    */
    public static String validateEmail(Scanner scanner) {
        while (true) {
            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();
            if (email.contains("@") && email.contains(".") && email.length() > 5) {
                return email;
            }
            System.out.println("Invalid email format. Please include '@' and '.' (e.g., user@example.com)");
            
            /**better regex to validate email
            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") && email.length() > 5) {
            * */
        }
    }
    
    /**
    * validateGender: Specifically validates the gender field
    */
    public static String validateGender(Scanner scanner) {
        while (true) {
            System.out.print("Enter gender (Male/Female/Other): ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Gender cannot be empty. Please enter Male, Female or Other.");
        }
    }

    /**
    * validateCompany: Specifically validates the company name (no accept empty info)
    */
    public static String validateCompany(Scanner scanner) {
        while (true) {
            System.out.print("Enter company name: ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Company name cannot be empty.");
        }
    }
}
