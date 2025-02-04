package edu.pharmacie.event;


import edu.pharmacie.model.entity.Employee;
import javafx.event.EventHandler;
import javafx.event.EventType;

public class EmployeeEventManager {
    private EventHandler<EmployeeEvent> createEventHandler;
    private EventHandler<EmployeeEvent> updateEventHandler;
    private EventHandler<EmployeeEvent> deleteEventHandler;
    private EventHandler<EmployeeEvent> showEventHandler;
    private EventHandler<EmployeeEvent> openDialogEventHandler;

    public void addCreateListener(EventHandler<EmployeeEvent> handler) {
        this.createEventHandler = handler;
    }

    public void addUpdateListener(EventHandler<EmployeeEvent> handler) {
        this.updateEventHandler = handler;
    }

    public void addDeleteListener(EventHandler<EmployeeEvent> handler) {
        this.deleteEventHandler = handler;
    }
    public void addShowListener(EventHandler<EmployeeEvent> handler) {
        this.showEventHandler = handler;
    }
    public void addOpenDialogListener(EventHandler<EmployeeEvent> handler) {
        this.openDialogEventHandler = handler;
    }

    public void fireEmployeeEvent(EventType<EmployeeEvent> eventType, Employee employee) {
        EmployeeEvent event = new EmployeeEvent(eventType, employee);
        switch (eventType.getName()) {
            case "EMPLOYEE_CREATE":
                if (createEventHandler != null) {
                    createEventHandler.handle(event);
                }
                break;
            case "EMPLOYEE_UPDATE":
                if (updateEventHandler != null) {
                    updateEventHandler.handle(event);
                }
                break;
            case "EMPLOYEE_SHOW":
                if (showEventHandler != null) {
                    showEventHandler.handle(event);
                }
                break;
            case "EMPLOYEE_DELETE":
                if (deleteEventHandler != null) {
                    deleteEventHandler.handle(event);
                }
                break;
            case "EMPLOYEE_OPEN_DIALOG":
                if (openDialogEventHandler != null) {
                    openDialogEventHandler.handle(event);
                }
                break;
        }
    }
}
