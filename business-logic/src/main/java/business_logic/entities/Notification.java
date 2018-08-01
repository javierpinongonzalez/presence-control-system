package business_logic.entities;

import facade_between_business_logic_and_infrastructure.entities.INotification;

import java.util.Date;

public class Notification implements INotification {
    private Long id;
    private Employee employee;
    private Date date;
    private String message;

    public Notification() {
    }

    public Notification(Long id, Employee employee, Date date, String message) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.message = message;
    }

    public Notification(Employee employee, Date date, String message) {
        this.employee = employee;
        this.date = date;
        this.message = message;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", employee=" + employee +
                ", date=" + date +
                ", message='" + message + '\'' +
                '}';
    }
}
