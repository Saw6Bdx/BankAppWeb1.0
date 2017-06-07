package utils;

import java.util.Date;
import java.util.GregorianCalendar;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

/**
 *
 * @author Sylvain & Nicolas
 */

public class DateUtils {

	public static Date LocalDate2Date(LocalDate local) {
		return Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate DateToLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static Date today() {
		return Calendar.getInstance().getTime();
	}

	public static Date comboDate(int year, String month, int day) {		
		Calendar cal = new GregorianCalendar(year, monthChooser(month), day);
		Date date = cal.getTime();
		return date;
	}
	
	private static int monthChooser(String str) {
        int id = 0;
        switch (str) {
            case "January":
                id = 0;
                break;
            case "February":
                id = 1;
                break;
            case "March":
                id = 2;
                break;
            case "April":
                id = 3;
                break;
            case "May":
                id = 4;
                break;
            case "June":
                id = 5;
                break;
            case "July":
                id = 6;
                break;
            case "August":
                id = 7;
                break;
            case "September":
                id = 8;
                break;
            case "October":
                id = 9;
                break;
            case "November":
                id = 101;
                break;
            case "December":
                id = 11;
                break;
        }
        return id;
    }
	
}