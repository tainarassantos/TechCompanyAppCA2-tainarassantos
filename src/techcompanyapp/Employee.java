package techcompanyapp;

/**
 *
 * @author Tainara
 */

import java.util.Objects;

public class Employee implements Comparable<Employee> {
    private final String firstName;    // First name
    private final String lastName;     // Last name
    private String gender;             // Gender
    private String email;              // Email
    private double salary;             // Salary
    private Department department;     // Department
    private String position;           // Position (senior/middle/junior/etc)
    private ManagerType jobTitle;      // Job title
    private String company;            // Company

    public Employee(String firstName, String lastName, String gender, String email,
                   double salary, Department department, String position,
                   ManagerType jobTitle, String company) {
        this.firstName = Objects.requireNonNull(firstName, "First name cannot be null");
        this.lastName = Objects.requireNonNull(lastName, "Last name cannot be null");
        this.gender = gender;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.position = position;
        this.jobTitle = jobTitle;
        this.company = company;
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGender() { return gender; }
    public String getEmail() { return email; }
    public double getSalary() { return salary; }
    public Department getDepartment() { return department; }
    public String getPosition() { return position; }  // Renomeado de getPositionLevel para getPosition
    public ManagerType getJobTitle() { return jobTitle; }
    public String getCompany() { return company; }

    // Setters
    public void setGender(String gender) { this.gender = gender; }
    public void setEmail(String email) { this.email = email; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setDepartment(Department department) { this.department = department; }
    public void setPosition(String position) { this.position = position; }  // Renomeado
    public void setJobTitle(ManagerType jobTitle) { this.jobTitle = jobTitle; }
    public void setCompany(String company) { this.company = company; }

    @Override
    public int compareTo(Employee other) {
        int lastNameCompare = this.lastName.compareToIgnoreCase(other.lastName);
        if (lastNameCompare != 0) return lastNameCompare;
        return this.firstName.compareToIgnoreCase(other.firstName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 &&
               firstName.equalsIgnoreCase(employee.firstName) &&
               lastName.equalsIgnoreCase(employee.lastName) &&
               Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName.toLowerCase(), lastName.toLowerCase(), email, salary);
    }

    @Override
    public String toString() {
        return String.format("%s %s | %s | %s | %s | %s | %s", 
                firstName, lastName, position, jobTitle, department, company, email);
    }

}
