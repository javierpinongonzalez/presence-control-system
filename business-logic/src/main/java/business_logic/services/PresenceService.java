package business_logic.services;

import business_logic.entities.Employee;
import business_logic.entities.Signing;
import facade_between_business_logic_and_infrastructure.entities.IEmployee;
import facade_between_business_logic_and_infrastructure.entities.ISigning;
import facade_between_business_logic_and_infrastructure.repositories.IPresenceRepository;
import facade_between_business_logic_and_presentation.services.IPresenceService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utils.exceptions.CommonExceptions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PresenceService implements IPresenceService {

    @Autowired
    private IPresenceRepository iPresenceRepository;

    @Value("${files.time-sheets.path}")
    private String timeSheetsPath;


    @Override
    public void setSigning (Integer fingerprintId) throws CommonExceptions.NotFound {
        if(iPresenceRepository.getEmployeeByFingerprintId(fingerprintId) == null) {
            throw new CommonExceptions.NotFound("This fingerprint does not exist");
        }
        iPresenceRepository.saveSigning(new Signing(new Employee(fingerprintId),new Date()));


    }

    @Override
    public Map getSignings(Integer fingerprintId, String startDate, String endDate) throws CommonExceptions.NotFound, CommonExceptions.InvalidParameters, CommonExceptions.GenericError {

        if(iPresenceRepository.getEmployeeByFingerprintId(fingerprintId) == null) {
            throw new CommonExceptions.NotFound("This fingerprint does not exist");
        }

        List<? extends ISigning> signings = iPresenceRepository.getSigningsByFingerprintId(fingerprintId, getStartDateFormatted(startDate), getEndDateFormatted(endDate));

        if (signings == null) {
            throw new CommonExceptions.NotFound("No signings found");
        }

        Map data = getDateMap(signings);

        return data;
    }

    public void effectiveTimeSheet (String startDate, String endDate) throws CommonExceptions.NotFound, CommonExceptions.InvalidParameters, CommonExceptions.GenericError{
        List <? extends IEmployee> employees = iPresenceRepository.getAllEmployees();

        if (employees == null) {
            throw new CommonExceptions.NotFound("There are no employees");
        }

        createTimeSheet(employees, startDate, endDate);
    }


    /******************** private functions ********************/

    private Date getEndDateFormatted (String endDate) throws CommonExceptions.InvalidParameters{
        Date endDateParsed;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            endDateParsed = dateFormat.parse(endDate);
        } catch (ParseException e){
            throw new CommonExceptions.InvalidParameters(e.getMessage());
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDateParsed);
        calendar.add(Calendar.DATE, 1);

        return calendar.getTime();
    }

    private Date getStartDateFormatted (String startDate) throws CommonExceptions.InvalidParameters{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return dateFormat.parse(startDate);
        } catch (ParseException e){
            throw new CommonExceptions.InvalidParameters(e.getMessage());
        }
    }

    private Map getDateMap (List<? extends ISigning> signings) throws CommonExceptions.GenericError{
        LinkedHashMap<String, List<String>> data = new LinkedHashMap<>();

        String date;
        String time;

        date = getDateAsString(signings.get(0).getDate());
        time = getTimeAsString(signings.get(0).getDate());

        data.put(date, new ArrayList<>());
        data.get(date).add(time);

        for (int i = 1 ; i < signings.size() ; i++) {
            String lastDate = date;

            date = getDateAsString(signings.get(i).getDate());
            time = getTimeAsString(signings.get(i).getDate());

            if (!lastDate.equals(date)){
                data.put(date, new ArrayList<>());
            }

            data.get(date).add(time);
        }

        return data;
    }

    private String getDateAsString (Date date) throws CommonExceptions.GenericError{
        String string;

        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        string = calendar.get(Calendar.YEAR)+ "-" + calendar.get(Calendar.MONTH) + 1 + "-" + calendar.get(Calendar.DAY_OF_MONTH);

        try{
            string = simpleDateFormat.format(simpleDateFormat.parse(string));
        } catch (ParseException e){
            throw new CommonExceptions.GenericError(e.getMessage());
        }

        return string;
    }

    private String getTimeAsString (Date date) throws CommonExceptions.GenericError{
        String string;

        DateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        string = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);

        try{
            string = simpleTimeFormat.format(simpleTimeFormat.parse(string));
        } catch (ParseException e){
            throw new CommonExceptions.GenericError(e.getMessage());
        }

        return string;
    }

    private void createTimeSheet (List<? extends IEmployee> employees, String startDate, String endDate) throws CommonExceptions.GenericError, CommonExceptions.InvalidParameters {
        XSSFWorkbook workbook = new XSSFWorkbook();

        for (int i = 0 ; i < employees.size() ; i++) {
            List<? extends ISigning> signings = iPresenceRepository.getSigningsByFingerprintId(employees.get(i).getFingerprintId(), getStartDateFormatted(startDate), getEndDateFormatted(endDate));

            if (signings == null) {
                continue;
            }

            XSSFSheet sheet = workbook.createSheet(employees.get(i).getName() + " " + employees.get(i).getSurname() + "-" + employees.get(i).getFingerprintId());
            int rowNum = 0;
            int colNum = 0;

            Row row;
            Cell cell;

            row = sheet.createRow(rowNum++);
            cell = row.createCell(colNum++);
            cell.setCellValue("Day");
            cell = row.createCell(colNum++);
            cell.setCellValue("Time In");
            cell = row.createCell(colNum++);
            cell.setCellValue("Time Out");
            cell = row.createCell(colNum++);
            cell.setCellValue("Time In");
            cell = row.createCell(colNum++);
            cell.setCellValue("Time Out");
            cell = row.createCell(colNum++);
            cell.setCellValue("Total Hours");

            Map<String,List<String>> data = getDateMap(signings);
            Iterator iterator = data.entrySet().iterator();

            Long totalDiffMilliseconds = new Long(0);

            for (;iterator.hasNext();){

                Map.Entry<String,List<String>> pair = (Map.Entry)iterator.next();

                String key = pair.getKey();
                List<String> values = pair.getValue();

                row = sheet.createRow(rowNum++);
                colNum = 0;

                cell = row.createCell(colNum++);
                cell.setCellValue(key);

                int k;
                List<Long> timestampList = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

                for (k = 0 ; k < values.size() && k < 4; k ++){
                    cell = row.createCell(colNum++);
                    cell.setCellValue(values.get(k));
                    try {
                        timestampList.add(dateFormat.parse(values.get(k)).getTime());
                    }catch (ParseException e){
                        e.printStackTrace();
                        throw new CommonExceptions.GenericError(e.getMessage());
                    }
                }

                if (k % 2 == 0) {
                    if (k == 2) {
                        colNum+=2;
                    }

                    Long diffMilliseconds;

                    diffMilliseconds = timestampList.get(1) - timestampList.get(0);

                    if (k == 4) {
                        diffMilliseconds += timestampList.get(3) - timestampList.get(2);
                    }

                    totalDiffMilliseconds += diffMilliseconds;

                    Long diffSeconds = diffMilliseconds / 1000;

                    Long diffMinutes = diffSeconds / 60;

                    Long hours = diffMinutes / 60;
                    Long minutes = diffMinutes % 60;

                    cell = row.createCell(colNum++);
                    cell.setCellValue(hours + "h " + minutes + "m");
                }
                iterator.remove();
            }

            if (totalDiffMilliseconds > 0){
                colNum = 4;
                row = sheet.createRow(rowNum++);
                cell = row.createCell (colNum++);
                cell.setCellValue("Total");

                Long totalDiffSeconds = totalDiffMilliseconds / 1000;

                Long totalDiffMinutes = totalDiffSeconds / 60;

                Long hours = totalDiffMinutes / 60;
                Long minutes = totalDiffMinutes % 60;

                cell = row.createCell (colNum++);
                cell.setCellValue(hours + "h " + minutes + "m");
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(timeSheetsPath + "/time-sheet-from-" + startDate + "-to-" + endDate + ".xlsx");
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new CommonExceptions.GenericError(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonExceptions.GenericError(e.getMessage());
        }
    }
}
