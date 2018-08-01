package presentation_rest_api.controllers;

import facade_between_business_logic_and_presentation.services.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import utils.exceptions.CommonExceptions;

@Controller
public class NotificationController {

    @Autowired
    private INotificationService iNotificationService;

    @RequestMapping(
            path = "/notification/{fingerprintId}",
            method = RequestMethod.POST
    )
    public ResponseEntity notification(@PathVariable Integer fingerprintId,
                                       @RequestBody String message) throws CommonExceptions.NotFound, CommonExceptions.InvalidParameters{

        //return something?
        iNotificationService.createNotification (fingerprintId, message);
        return new ResponseEntity(HttpStatus.OK);
    }
}
