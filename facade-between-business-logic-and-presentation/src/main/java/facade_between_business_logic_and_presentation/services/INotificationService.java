package facade_between_business_logic_and_presentation.services;

import utils.exceptions.CommonExceptions;

public interface INotificationService {

    void createNotification (Integer fingerprintId, String message) throws CommonExceptions.NotFound, CommonExceptions.InvalidParameters;
}
