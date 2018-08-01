package facade_between_business_logic_and_infrastructure.repositories;

import facade_between_business_logic_and_infrastructure.entities.IEmployee;
import facade_between_business_logic_and_infrastructure.entities.ISigning;

import java.util.Date;
import java.util.List;

public interface IPresenceRepository {
    IEmployee getEmployeeByFingerprintId(Integer fingerprintId);
    List<? extends IEmployee> getAllEmployees();
    ISigning saveSigning (ISigning signing);
    List<? extends ISigning> getSigningsByFingerprintId (Integer fingerprintId, Date startDate, Date endDate);
}
