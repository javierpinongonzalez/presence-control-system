package business_logic.services;

import business_logic.entities.Employee;
import business_logic.entities.Notification;
import facade_between_business_logic_and_infrastructure.repositories.INotificationRepository;
import facade_between_business_logic_and_infrastructure.repositories.IPresenceRepository;
import facade_between_business_logic_and_presentation.services.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utils.exceptions.CommonExceptions;

import java.util.Date;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private IPresenceRepository iPresenceRepository;

    @Autowired
    private INotificationRepository iNotificationRepository;

    @Value("${notifications.length}")
    private Integer maxLength;

    @Override
    public void createNotification(Integer fingerprintId, String message) throws CommonExceptions.NotFound, CommonExceptions.InvalidParameters {
        if(iPresenceRepository.getEmployeeByFingerprintId(fingerprintId) == null) {
            throw new CommonExceptions.NotFound("This fingerprint does not exist");
        }
        if (message.length() > maxLength) {
            throw new CommonExceptions.InvalidParameters("Message size is too large");
        }

        iNotificationRepository.saveNotification(new Notification(new Employee(fingerprintId), new Date(), message));
    }
}
