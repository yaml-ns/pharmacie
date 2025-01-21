package edu.pharmacie.service;

import edu.pharmacie.model.entity.Employee;
import edu.pharmacie.model.entity.Connection;
import edu.pharmacie.model.entity.Function;
import edu.pharmacie.model.entity.Role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataFixtures {

    private static DataFixtures instance;
    private List<Employee> employees;
    public static DataFixtures getInstance() {
        if (instance == null) {
            synchronized (DataFixtures.class) {
                if (instance == null) {
                    instance = new DataFixtures();
                }
            }
        }
        return instance;
    }

    private  DataFixtures(){
        employees = new ArrayList<>();

        Role adminRole = Role.ADMIN;
        Role drugManagerRole = Role.DRUG_MANAGER;
        Role hrManagerRole = Role.HR_MANAGER;
        Role customerRole = Role.CUSTOMER;

        Function managerFunction = Function.MANAGER;
        Function pharmacistFunction = Function.PHARMACIST;
        Function sellerFunction = Function.SELLER;


        Connection conn1 = new Connection(1L, "admin@pharmacie.com", "admin123", adminRole, true);
        Connection conn2 = new Connection(2L, "drugmanager@pharmacie.com", "drug123", drugManagerRole, true);
        Connection conn3 = new Connection(3L, "hrmanager@pharmacie.com", "hr123", hrManagerRole, true);
        Connection conn4 = new Connection(4L, "customer@pharmacie.com", "customer123", customerRole, true);
        Connection conn5 = new Connection(5L, "manager1@pharmacie.com", "manager123", adminRole, true);
        Connection conn6 = new Connection(6L, "pharmacist1@pharmacie.com", "pharm123", drugManagerRole, true);
        Connection conn7 = new Connection(7L, "pharmacist2@pharmacie.com", "pharm456", drugManagerRole, true);
        Connection conn8 = new Connection(8L, "seller1@pharmacie.com", "seller123", customerRole, true);
        Connection conn9 = new Connection(9L, "seller2@pharmacie.com", "seller456", customerRole, true);
        Connection conn10 = new Connection(10L, "hrworker@pharmacie.com", "hrworker123", hrManagerRole, true);

        Employee emp1 = new Employee(1L, "Alice", "Durand", "123 rue de Paris", "0123456789", new Date(1990, 5, 1), new Date(2015, 3, 1), 3500, managerFunction, 40);
        emp1.setConnection(conn1);

        Employee emp2 = new Employee(2L, "Bob", "Dupont", "456 rue de Lyon", "0234567890", new Date(1985, 8, 15), new Date(2016, 7, 15), 3000, pharmacistFunction, 35);
        emp2.setConnection(conn2);

        Employee emp3 = new Employee(3L, "Carla", "Martinez", "789 rue de Marseille", "0345678901", new Date(1992, 11, 20), new Date(2017, 1, 10), 2800, pharmacistFunction, 38);
        emp3.setConnection(conn3);

        Employee emp4 = new Employee(4L, "David", "Lemoine", "135 rue de Lille", "0456789012", new Date(1980, 2, 5), new Date(2018, 5, 1), 2500, sellerFunction, 30);
        emp4.setConnection(conn4);

        Employee emp5 = new Employee(5L, "Eve", "Dufresne", "246 rue de Bordeaux", "0567890123", new Date(1995, 4, 25), new Date(2019, 6, 10), 2200, sellerFunction, 28);
        emp5.setConnection(conn5);

        Employee emp6 = new Employee(6L, "François", "Leclerc", "369 rue de Nantes", "0678901234", new Date(1988, 7, 13), new Date(2020, 3, 22), 3800, managerFunction, 40);
        emp6.setConnection(conn6);

        Employee emp7 = new Employee(7L, "Gina", "Bernard", "357 rue de Toulouse", "0789012345", new Date(1993, 1, 30), new Date(2019, 4, 8), 3200, pharmacistFunction, 37);
        emp7.setConnection(conn7);

        Employee emp8 = new Employee(8L, "Hugo", "Martin", "246 rue de Paris", "0890123456", new Date(1987, 10, 16), new Date(2021, 5, 13), 2600, sellerFunction, 34);
        emp8.setConnection(conn8);

        Employee emp9 = new Employee(9L, "Ingrid", "Benoit", "112 rue de Lyon", "0901234567", new Date(1990, 9, 12), new Date(2020, 2, 18), 2900, sellerFunction, 32);
        emp9.setConnection(conn9);

        Employee emp10 = new Employee(10L, "John", "Clerc", "567 rue de Marseille", "0112345678", new Date(1994, 12, 2), new Date(2022, 7, 30), 3100, managerFunction, 40);
        emp10.setConnection(conn10);


        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
        employees.add(emp6);
        employees.add(emp7);
        employees.add(emp8);
        employees.add(emp9);
        employees.add(emp10);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public  void removeEmployee(Long id){
        employees.removeIf(employee -> employee.getId().equals(id));
    }

}
