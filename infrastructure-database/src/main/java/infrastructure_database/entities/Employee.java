package infrastructure_database.entities;

import facade_between_business_logic_and_infrastructure.entities.IEmployee;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Employee implements IEmployee {
    @Id
    private Integer fingerprintId;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    public Employee() {
    }

    public Employee (IEmployee iEmployee) {
        this.fingerprintId = iEmployee.getFingerprintId();
        this.name = iEmployee.getName();
        this.surname = iEmployee.getSurname();
    }

    public Employee(Integer fingerprintId, @NotNull String name, @NotNull String surName) {
        this.fingerprintId = fingerprintId;
        this.name = name;
        this.surname = surName;
    }

    public Integer getFingerprintId() {
        return fingerprintId;
    }

    public void setFingerprintId(Integer fingerprintId) {
        this.fingerprintId = fingerprintId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surName) {
        this.surname = surName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "fingerprintId=" + fingerprintId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
