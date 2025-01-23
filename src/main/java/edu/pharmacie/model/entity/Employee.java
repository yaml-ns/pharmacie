package edu.pharmacie.model.entity;

import java.util.Date;
import java.util.Objects;

public class Employee extends User {

    private Date dateOfHiring;
    private double salary;
    private Function function;
    private int hoursPerWeek;

    public Employee(Long id,
                    String firstname,
                    String lastname,
                    String address,
                    String phoneNumber,
                    Date dateOfBirth,
                    Date dateOfHiring, double salary, Function function, int hoursPerWeek) {
        super(id, firstname, lastname, address, phoneNumber, dateOfBirth);
        this.dateOfHiring = dateOfHiring;
        this.salary = salary;
        this.function = function;
        this.hoursPerWeek = hoursPerWeek;
    }

    public Date getDateOfHiring() {
        return dateOfHiring;
    }

    public void setDateOfHiring(Date dateOfHiring) {
        this.dateOfHiring = dateOfHiring;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "dateOfHiring=" + dateOfHiring +
                ", salary=" + salary +
                ", function='" + function + '\'' +
                ", hoursPerWeek=" + hoursPerWeek +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return (
                employee.getFirstname().equals(this.getFirstname()) &&
                employee.getLastname().equals(this.getLastname()) &&
                employee.getAddress().equals(this.getAddress()) &&
                employee.getPhoneNumber().equals(this.getPhoneNumber()) &&
                employee.getDateOfBirth().equals(this.getDateOfBirth()) &&
                employee.getDateOfHiring().equals(this.dateOfHiring) &&
                employee.getSalary() == this.salary &&
                employee.getFunction() == this.function &&
                employee.getHoursPerWeek() == this.hoursPerWeek
                );
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfHiring, salary, function, hoursPerWeek);
    }
}
