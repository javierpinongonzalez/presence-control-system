package facade_between_business_logic_and_presentation.services;

import org.springframework.core.io.ByteArrayResource;
import utils.exceptions.CommonExceptions;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IPresenceService {

    void setSigning (Integer fingerprintId) throws CommonExceptions.NotFound;

    Map getSignings (Integer fingerprintId, String startDate, String endDate) throws CommonExceptions.NotFound, CommonExceptions.InvalidParameters,  CommonExceptions.GenericError;

    void effectiveTimeSheet (String startDate, String endDate) throws CommonExceptions.NotFound, CommonExceptions.InvalidParameters, CommonExceptions.GenericError;
}
