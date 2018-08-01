package infrastructure_database.entities;

import facade_between_business_logic_and_infrastructure.entities.ISigning;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Signing implements ISigning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name= "fkFingerprintId")
    private Employee employee;

    @NotNull
    private Date date;

    public Signing() {
    }

    public Signing (ISigning iSigning) {
        this.id = iSigning.getId();
        this.employee = new Employee(iSigning.getEmployee());
        this.date = iSigning.getDate();
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
