/**
 * Assignment 1: Using standard libraries <br />
 * Calculate age in days
 */

import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;

public class daysold {

    /**
     * Calculate how many days between today and the date, and them out
     * @param birthday      {@code String} The start date
     */
    public static void days(String birthday) {
  
//        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
    	
    	Calendar today = GregorianCalendar.getInstance();
    	Calendar bday = GregorianCalendar.getInstance();
;        	try { // Attempt to parse user input
        		Date date = formatter.parse(birthday);
        		bday.setTime(date) ;
        		System.out.println();
        	}
 			catch (java.text.ParseException e) {
//				e.printStackTrace();
 				System.out.println("Invalid birthday! Must be in format: yyyy-dd-MM");
			} 	
	 	
    	// format output strings 
    	SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM d yyyy");
    	String formattedbDay = outputFormat.format(bday.getTime());
    	String formattedToday = outputFormat.format(today.getTime());
    	
    	// find age in milliseconds, then convert to days if the date is valid
    	long ageMillisecs =  today.getTimeInMillis()-bday.getTimeInMillis();
    	
    	if (ageMillisecs < 0  ) {
    		System.out.println(String.format("Invalid birthday: %s, you cannot be born before today: %s", formattedbDay, formattedToday));
    		return;
    	}
    	
    	int daysOld =  (int) (ageMillisecs/(24 * 60 * 60 * 1000));
        	
    	System.out.println(String.format(    	
    	    	"Birthday: %s; today: %s -- You are %d days old.", formattedbDay, formattedToday, daysOld));
    	
    	
    
    	

    } // public static void days(String birthday)

    /**
     * Main entry
     * @param args          {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        days("2000-01-01");
        days("3000-01-01");           // This is a wrong birthday
    } // public static void main(String[] args)

}
