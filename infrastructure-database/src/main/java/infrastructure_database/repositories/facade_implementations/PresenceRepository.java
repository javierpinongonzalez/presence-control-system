package infrastructure_database.repositories.facade_implementations;

import facade_between_business_logic_and_infrastructure.entities.IEmployee;
import facade_between_business_logic_and_infrastructure.entities.ISigning;
import facade_between_business_logic_and_infrastructure.repositories.IPresenceRepository;
import infrastructure_database.entities.Signing;
import infrastructure_database.repositories.spring_data_jpa.JpaEmployeeRepository;
import infrastructure_database.repositories.spring_data_jpa.JpaSigningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class PresenceRepository implements IPresenceRepository {

    @Autowired
    private JpaEmployeeRepository jpaEmployeeRepository;

    @Autowired
    private JpaSigningRepository jpaSigningRepository;

    @Override
    public IEmployee getEmployeeByFingerprintId(Integer fingerprintId){
        return jpaEmployeeRepository.findByFingerprintId(fingerprintId);
    }

    @Override
    public List <? extends IEmployee> getAllEmployees() {
        return jpaEmployeeRepository.findAll();
    }

    @Override
    public ISigning saveSigning(ISigning signing) {
        return jpaSigningRepository.save(new Signing(signing));
    }

    @Override
    public List<? extends ISigning> getSigningsByFingerprintId(Integer fingerprintId, Date startDate, Date endDate) {
        return jpaSigningRepository.findByEmployee_FingerprintIdAndDateGreaterThanEqualAndDateLessThanEqual(fingerprintId, startDate, endDate);
    }
}
