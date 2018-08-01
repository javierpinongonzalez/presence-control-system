package infrastructure_database.repositories.spring_data_jpa;

import infrastructure_database.entities.Employee;
import infrastructure_database.entities.Signing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface JpaSigningRepository extends JpaRepository<Signing, Long> {

    List<Signing> findByEmployee_FingerprintIdAndDateGreaterThanEqualAndDateLessThanEqual (Integer fingerprintId, Date start, Date end);
}
