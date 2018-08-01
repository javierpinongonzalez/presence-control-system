package facade_between_business_logic_and_infrastructure.repositories;

import facade_between_business_logic_and_infrastructure.entities.INotification;

public interface INotificationRepository {
    INotification saveNotification (INotification notification);
}
