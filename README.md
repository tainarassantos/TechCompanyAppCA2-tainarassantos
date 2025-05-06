These is my CA2 project and configuration progress of the classes for the Tech Company application.

Note: I changed on 6-5-25 the package name from techcompanyapp to CA_2 because the requeriment on the page 1 of these project.

The structure of my program is:
TechCompanyAppCA2/
├── src/
│   └── CA_2/
│       ├── Main.java
│       ├── FileHandler.java
│       ├── MenuENUM.java
│       ├── SubMenuENUM.java
│       ├── InputValidator.java
│       ├── EmployeeService.java
│       ├── EmployeeSearch.java
│       ├── ManagerType.java 
│       ├── Department.java
│       └── Employee.java
└── Applicants_Form.txt


* STEPS UPDATE:
1> I created and started the configuration of the class Main.java and MenuENUM.java;
2> I included the classes SubMenuENUM.java, FileHandler.java, InputValidator.java;
3> I initially created and configured the classes FileHandler.java and InputValidator.java;
4> I initially created and configured the classes Department.java, Employee.java, EmployeeService.java, and EmployeeSearch.java.
5> I initially created and configured the classes ManagerType.Java, and I modified the other classes for necessary adjustments. I havve some error to fix in the menu SORT and SEARCH.
6> I update the EmployeeSearch.java to use the correctly method Recursive Binary Search, according to shared resources for CA2, I adapted it for the Employee object.
7> I worked in all classes with the comments and organizing the structure. Have updates in all files.
8> I update the class inputvalidator.java functions to validate de insert data from the user correctly, and I also did some updates in the class EmployeeService.java to print for the user better data in SORT menu, and I am working in the functions to ADD/EDIT menu.
9> I update the classes Main.Java, SubMenuENUM.java and FileHandler.java with some small adjustments to process the menu navigation and inputs on the database .txt. 
The classes EmployeeSearch.java and EmployeeService.java have some updates about the methods to sort, search and add employess of the tech company.
10> I update the package name to CA_2 (required in the assignment description).
11> I create new function in inputValidator.java to validade the user selection to proceed with inclusion/edit/create random employee, and I update the other classes to work with these function (randomEmployee and edit employee).
12> I update the last adjustments in these classes and now my software is complete.
13> I update error handling in these classes (EmployeeSearch > binarySearchRecursive ; EmployeeService > createEmployeeFromInput, and editEmployee ; FileHandler > saveToFile, and loadFromFile)
