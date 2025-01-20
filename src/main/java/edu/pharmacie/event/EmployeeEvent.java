package edu.pharmacie.event;

import edu.pharmacie.model.entity.Employee;
import javafx.event.Event;
import javafx.event.EventType;

public class EmployeeEvent extends Event {

    public static final EventType<EmployeeEvent> ANY = new EventType<>(Event.ANY, "EMPLOYEE_EVENT");
    public static final EventType<EmployeeEvent> CREATE = new EventType<>(ANY, "EMPLOYEE_CREATE");
    public static final EventType<EmployeeEvent> UPDATE = new EventType<>(ANY, "EMPLOYEE_UPDATE");
    public static final EventType<EmployeeEvent> DELETE = new EventType<>(ANY, "EMPLOYEE_DELETE");

    private final Employee employee;


    public EmployeeEvent(EventType<? extends Event> eventType, Employee employee) {
        super(eventType);
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
