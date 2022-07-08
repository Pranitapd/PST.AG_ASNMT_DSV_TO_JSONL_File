import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ProcessDate class
 * @author Pranita Deshmukh
 */
public class ProcessDate {

    /**
     * isValid function checks if passed string is a valid date
     * @param indate
     * @return
     * @throws ParseException
     */
    public static boolean isValid(String indate) throws ParseException {
        Pattern p = Pattern.compile("(((20[012]\\d|1[0-9]\\d\\d)|(1\\d|2[0123]))-((0[0-9])|(1[012]))-((0[1-9])|([12][0-9])|(3[01])))|(((0[1-9])|([12][0-9])|(3[01]))-((0[0-9])|(1[012]))-((20[012]\\d|1[0-9]\\d\\d)|(1\\d|2[0123])))|(((20[012]\\d|1[0-9]\\d\\d)|(1\\d|2[0123]))\\/((0[0-9])|(1[012]))\\/((0[1-9])|([12][0-9])|(3[01])))|(((0[0-9])|(1[012]))\\/((0[1-9])|([12][0-9])|(3[01]))\\/((20[012]\\d|1[0-9]\\d\\d)|(1\\d|2[0123])))|(((0[1-9])|([12][0-9])|(3[01]))\\/((0[0-9])|(1[012]))\\/((20[012]\\d|1[0-9]\\d\\d)|(1\\d|2[0123])))|(((0[1-9])|([12][0-9])|(3[01]))\\.((0[0-9])|(1[012]))\\.((20[012]\\d|1[0-9]\\d\\d)|(1\\d|2[0123])))|(((20[012]\\d|1[0-9]\\d\\d)|(1\\d|2[0123]))\\.((0[0-9])|(1[012]))\\.((0[1-9])|([12][0-9])|(3[01])))\n");
        Matcher m = p.matcher(indate);
        boolean b = m.matches();
        return b;
    }

    /**
     * checkWhichDatePattern function recognises the date and transforms to YYYY-MM-dd format
     * @param date
     * @return
     * @throws ParseException
     */
    public static String checkWhichDatePattern(String date) throws ParseException {
        SimpleDateFormat targetDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate;
        //For "/" separator
        Pattern p1 = Pattern.compile("^((19|2[0-9])[0-9]{2})\\/(0[1-9]|1[012])\\/(0[1-9]|[12][0-9]|3[01])$"); //yyyy/MM/dd
        Pattern p2 = Pattern.compile("^(0[1-9]|1[012])\\/(0[1-9]|[12][0-9]|3[01])\\/((19|2[0-9])[0-9]{2})$"); //MM/dd/yyyy
        Pattern p3 = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[012])\\/((19|2[0-9])[0-9]{2})$"); //dd/MM/yyyy
        Matcher m1 = p1.matcher(date);
        Matcher m2 = p2.matcher(date);
        Matcher m3 = p3.matcher(date);

        //For "-" separator
        Pattern p4 = Pattern.compile("^(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-((19|2[0-9])[0-9]{2})$"); //MM/dd/yyyy
        Pattern p5 = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-((19|2[0-9])[0-9]{2})$"); //dd/MM/yyyy
        Matcher m4 = p4.matcher(date);
        Matcher m5 = p5.matcher(date);

        //For "." separator
        Pattern p6 = Pattern.compile("^((19|2[0-9])[0-9]{2})\\.(0[1-9]|1[012])\\.(0[1-9]|[12][0-9]|3[01])$"); //yyyy.MM.dd
        Pattern p7 = Pattern.compile("^(0[1-9]|1[012])\\.(0[1-9]|[12][0-9]|3[01])\\.((19|2[0-9])[0-9]{2})$"); //MM.dd.yyyy
        Pattern p8 = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[012])\\.((19|2[0-9])[0-9]{2})$"); //dd.MM.yyyy
        Matcher m6 = p6.matcher(date);
        Matcher m7 = p7.matcher(date);
        Matcher m8 = p8.matcher(date);

        if (m1.matches()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            newDate = formatter.parse(date);
            //System.out.println(targetDateFormat.format(newDate));
            date =  targetDateFormat.format(newDate);
        } else if (m2.matches()) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            newDate = formatter.parse(date);
            date =  targetDateFormat.format(newDate);
        } else if (m3.matches()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            newDate = formatter.parse(date);
            date =  targetDateFormat.format(newDate);
        } else if (m4.matches()) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
            newDate = formatter.parse(date);
            date =  targetDateFormat.format(newDate);
        } else if (m5.matches()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            newDate = formatter.parse(date);
            date =  targetDateFormat.format(newDate);
        } else if (m6.matches()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
            newDate = formatter.parse(date);
            date =  targetDateFormat.format(newDate);
        } else if (m7.matches()) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM.dd.yyyy");
            newDate = formatter.parse(date);
            date =  targetDateFormat.format(newDate);
        } else if (m8.matches()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            newDate = formatter.parse(date);
            date =  targetDateFormat.format(newDate);
        }
        return date;
    }
}
