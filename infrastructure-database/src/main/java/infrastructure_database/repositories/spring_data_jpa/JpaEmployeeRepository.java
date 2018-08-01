package infrastructure_database.repositories.spring_data_jpa;

import infrastructure_database.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByFingerprintId(Integer fingerprintId);
}
