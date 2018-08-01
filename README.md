**What is this?**

This repository contains a challenge code. Challenge is about:

Design and implement the backend for a presence control system for the employees of a
company. You need to take into consideration that the employees must always clock in and
out by registering their fingerprint.
The “People” department will need:
 * Access to the employee’s presence and time control.
 * Generation of time sheets of effective worked hours.
 * Reception of asynchronous notifications regarding: total absence time, absence anomalies
(for example because of on-call duties), etc.

**What do I need?**
 
 You need to get installed and updated:
 * docker
 * docker-compose

**How do I run it?**

 *1* Clone or download the repository
 
 *2* Execute path-to-repo/docker/run-service.sh
 
 *3* Do API calls

**API calls**

* Sign with fingerprint

  * Path: http://localhost:8081/presence/{fingerprintId}
  * Method: POST
  * Query params: none
  * Response body: none
  
* Employee's time control

  * Path: http://localhost:8081/presence/{fingerprintId}
  * Method: GET
  * Query params:
    * startDate: yyyy-MM-dd
    * endDate: yyyy-MM-dd
  * Response body: json with employee's time control
  
* Employees' time sheet
  * Path: http://localhost:8081/presence
  * Method: GET
  * Query params:
    * startDate: yyyy-MM-dd
    * endDate: yyyy-MM-dd
  * Response body: none
  * Details: Time sheet is saved locally at /tmp
  
* Notification
  * Path: http://localhost:8081/notification/{fingerprintId}
  * Method: POST
  * Request body: notification message on plain text
  * Query params: none
  * Response body: none
    
    
**What is missing?**

* logger
* Improve time sheet design
* Add notifications to time sheet

