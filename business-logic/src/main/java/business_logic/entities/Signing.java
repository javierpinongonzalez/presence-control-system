package business_logic.entities;

import facade_between_business_logic_and_infrastructure.entities.ISigning;

import java.util.Date;

public class Signing implements ISigning {

    private Long id;
    private Employee employee;
    private Date date;

    public Signing() {
    }

    public Signing(Employee employee, Date date) {
        this.employee = employee;
        this.date = date;
    }

    public Signing(Long id, Employee employee, Date date) {
        this.id = id;
        this.employee = employee;
        this.date = date;
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

    @Override
    public String toString() {
        return "Signing{" +
                "id=" + id +
                ", employee=" + employee +
                ", date=" + date +
                '}';
    }
}
