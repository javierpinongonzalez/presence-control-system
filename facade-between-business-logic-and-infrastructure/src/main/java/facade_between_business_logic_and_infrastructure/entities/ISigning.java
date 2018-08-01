package facade_between_business_logic_and_infrastructure.entities;

import java.util.Date;

public interface ISigning {
    Long getId();
    Date getDate();
    IEmployee getEmployee();
}
