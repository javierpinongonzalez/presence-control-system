package infrastructure_database.entities;

import facade_between_business_logic_and_infrastructure.entities.INotification;
import facade_between_business_logic_and_infrastructure.entities.ISigning;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Notification implements INotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name= "fkFingerprintId")
    private Employee employee;

    @NotNull
    private Date date;

    @NotNull
    private String message;

    public Notification() {
    }

    public Notification(Employee employee, Date date, String message) {
        this.employee = employee;
        this.date = date;
        this.message = message;
    }

    public Notification(INotification iNotification) {
        this.id = iNotification.getId();
        this.employee = new Employee(iNotification.getEmployee());
        this.date = iNotification.getDate();
        this.message = iNotification.getMessage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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
