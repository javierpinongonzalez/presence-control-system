package business_logic.entities;

import facade_between_business_logic_and_infrastructure.entities.IEmployee;

public class Employee implements IEmployee {

    private Integer fingerprintId;
    private String name;
    private String surname;

    public Employee() {
    }

    public Employee(Integer fingerprintId) {
        this.fingerprintId = fingerprintId;
    }

    public Employee(Integer fingerprintId, String name, String surname) {
        this.fingerprintId = fingerprintId;
        this.name = name;
        this.surname = surname;
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

    public void setSurname(String surname) {
        this.surname = surname;
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
