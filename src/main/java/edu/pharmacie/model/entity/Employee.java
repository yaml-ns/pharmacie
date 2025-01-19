package edu.pharmacie.model.entity;

import java.util.Date;

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
}
