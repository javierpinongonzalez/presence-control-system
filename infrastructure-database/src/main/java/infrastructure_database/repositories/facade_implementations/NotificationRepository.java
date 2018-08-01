package infrastructure_database.repositories.facade_implementations;

import facade_between_business_logic_and_infrastructure.entities.INotification;
import facade_between_business_logic_and_infrastructure.repositories.INotificationRepository;
import infrastructure_database.entities.Notification;
import infrastructure_database.repositories.spring_data_jpa.JpaNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository implements INotificationRepository {

    @Autowired
    private JpaNotificationRepository jpaNotificationRepository;

    @Override
    public INotification saveNotification (INotification notification) {
        return jpaNotificationRepository.save(new Notification(notification));
    }

}
