package facade_between_business_logic_and_infrastructure.entities;

import java.util.Date;

public interface INotification {
    Long getId();
    Date getDate();
    IEmployee getEmployee();
    String getMessage();
}
