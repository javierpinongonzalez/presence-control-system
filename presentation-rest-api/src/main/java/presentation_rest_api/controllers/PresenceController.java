package presentation_rest_api.controllers;

import facade_between_business_logic_and_presentation.services.IPresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utils.exceptions.CommonExceptions;

@Controller
public class PresenceController {

    @Autowired
    private IPresenceService iPresenceService;

    @RequestMapping(
            path = "/presence/{fingerprintId}",
            method = RequestMethod.POST
    )
    public ResponseEntity fingerprintControl(@PathVariable Integer fingerprintId) throws CommonExceptions.NotFound {
        iPresenceService.setSigning(fingerprintId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            path = "/presence/{fingerprintId}",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity employeeTimeControl(@PathVariable Integer fingerprintId,
                                              @RequestParam String startDate,
                                              @RequestParam String endDate) throws CommonExceptions.NotFound, CommonExceptions.InvalidParameters,  CommonExceptions.GenericError{

        //return json with info
        iPresenceService.getSignings(fingerprintId, startDate, endDate);

        return new ResponseEntity(iPresenceService.getSignings(fingerprintId, startDate, endDate), HttpStatus.OK);
    }

    @RequestMapping(
            path = "/presence",
            method = RequestMethod.GET
    )
    public ResponseEntity effectiveTimeSheet(@RequestParam String startDate,
                                                       @RequestParam String endDate) throws CommonExceptions.NotFound, CommonExceptions.InvalidParameters, CommonExceptions.GenericError{
        iPresenceService.effectiveTimeSheet(startDate, endDate);

        return new ResponseEntity( HttpStatus.OK);
    }
}
