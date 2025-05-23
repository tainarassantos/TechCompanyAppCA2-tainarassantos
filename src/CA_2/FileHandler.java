package CA_2;

/**
 *
 * @author Tainara de Souza Santos 
 * @student 2024561
 * Manages all file operations for employee data.
 * I handle loading from and saving to CSV files while validating data integrity.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {       
    /**
     * Loads employees information from CSV file
     */
    public static List<Employee> loadFromFile(String filename) {
        List<Employee> employees = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;
            
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header
                }
                try {
                    Employee employee = parseEmployee(line);
                    if (employee != null) {
                        employees.add(employee);
                    }
                } catch (Exception e) {
                    System.err.println("Error details: " + e.getMessage());
                // Continue processing next lines
                }
            }        
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + filename);
            return null;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            return null;
        }        
        return employees;
    }
    
    /**
     * Saves all employees information to CSV 
     */
    public static boolean saveToFile(String filename, List<Employee> employees) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Write header
            writer.println("First name,Last name,Gender,Email,Salary,Department,Position,Job title,Company");
            
            // Write employee data
            for (Employee emp : employees) {
                try {
                    writer.println(empToCsvLine(emp));
                } catch (Exception e) {
                    System.err.println("\nError writing employee data: " + emp);
                    System.err.println("\nError details: " + e.getMessage());
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("Error to found file - " + filename);
            return false;
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Unexpected error while saving: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Converts CSV line to Employee object
     */
    private static Employee parseEmployee(String csvLine) {
        try {
            String[] data = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Regex validate the info separated per ","
            if (data.length < 9) return null;

            //validate the empty spaces of the registered lines (.txt file)
            String position = data[6].trim().isEmpty() ? null : data[6].trim();
            String company = data[8].trim().isEmpty() ? "TechInnovators" : data[8].trim();

            return new Employee(
                data[0].trim(), 
                data[1].trim(),
                data[2].trim().isEmpty() ? null : data[2].trim(),
                data[3].trim().isEmpty() ? null : data[3].trim(),
                Double.parseDouble(data[4].trim()),
                Department.fromDisplayName(data[5].trim()),
                position,
                ManagerType.fromDisplayName(data[7].trim()),
                company
            );
        } catch (Exception e) {
            System.err.println("Error parsing line: '" + csvLine + "'. Error: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Formats Employee for CSV output 
     */
    private static String empToCsvLine(Employee emp) {
        return emp.toCsvLine(); //now use the method CSV         
    }
}
