package infrastructure_database.repositories.spring_data_jpa;

import infrastructure_database.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNotificationRepository extends JpaRepository<Notification, Long> {
}
