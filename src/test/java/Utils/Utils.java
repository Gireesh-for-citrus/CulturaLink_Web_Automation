package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class Utils {

    public static String aMonthAgoTodayDate() {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");
        cal.add((GregorianCalendar.MONTH), -1);
        Date previousMonthDate = cal.getTime();

        return formatDate.format(previousMonthDate);
    }

    public static String today() {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        Date todayDate = cal.getTime();
        SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");

        return formatDate.format(todayDate);
    }

    public static String previousMonthYear() {

        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("MMMMMM yyyy");
        cal.add((GregorianCalendar.MONTH), -1);
        Date previousMonthDate = cal.getTime();

        return formatDate.format(previousMonthDate);

    }

    public static String currentMonthYear() {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        Date todayDate = cal.getTime();
        SimpleDateFormat formatDate = new SimpleDateFormat("MMMMMM yyyy");

        return formatDate.format(todayDate);
    }

    public static String aMonthAgoToday() {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("d");
        cal.add((GregorianCalendar.MONTH), -1);
        Date previousMonthDate = cal.getTime();

        return formatDate.format(previousMonthDate);
    }

    public static String todayDate() {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        Date todayDate = cal.getTime();
        SimpleDateFormat formatDate = new SimpleDateFormat("d");

        return formatDate.format(todayDate);
    }

    public static String previousMonthStartDate() {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("d");
        cal.add((GregorianCalendar.MONTH), -1);

        cal.set((GregorianCalendar.DATE), 1);
        Date prevMonthDayOne = cal.getTime();

        return formatDate.format(prevMonthDayOne);
    }

    public static String previousMonthEndDate() {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("d");
        cal.add((GregorianCalendar.MONTH), -1);

        cal.set(GregorianCalendar.DATE, cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
        Date prevMonthLastDay = cal.getTime();

        return formatDate.format(prevMonthLastDay);
    }

    public static String previousMonthStarts() {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");
        cal.add((GregorianCalendar.MONTH), -1);

        cal.set((GregorianCalendar.DATE), 1);
        Date prevMonthDayOne = cal.getTime();

        return formatDate.format(prevMonthDayOne);
    }

    public static String previousMonthEnds() {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");
        cal.add((GregorianCalendar.MONTH), -1);

        cal.set(GregorianCalendar.DATE, cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
        Date prevMonthLastDay = cal.getTime();

        return formatDate.format(prevMonthLastDay);
    }

    public static String nextMonthStartDate() {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("d");
        cal.add((GregorianCalendar.MONTH), 1);

        cal.set((GregorianCalendar.DATE), 1);
        Date nextMonthDayOne = cal.getTime();

        return formatDate.format(nextMonthDayOne);
    }

    public static String nextMonthEndDate() {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("d");
        cal.add((GregorianCalendar.MONTH), 1);

        cal.set(GregorianCalendar.DATE, cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
        Date prevMonthLastDay = cal.getTime();

        return formatDate.format(prevMonthLastDay);
    }

    public static String nextMonthYear() {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("MMMMMM yyyy");
        cal.add((GregorianCalendar.MONTH), 1);

        cal.set(GregorianCalendar.DATE, cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
        Date prevMonthLastDay = cal.getTime();

        return formatDate.format(prevMonthLastDay);
    }

    public static String nextMonthStarts() {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");
        cal.add((GregorianCalendar.MONTH), 1);

        cal.set((GregorianCalendar.DATE), 1);
        Date prevMonthDayOne = cal.getTime();

        return formatDate.format(prevMonthDayOne);
    }

    public static String nextMonthEnds() {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");
        cal.add((GregorianCalendar.MONTH), 1);

        cal.set(GregorianCalendar.DATE, cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
        Date prevMonthLastDay = cal.getTime();

        return formatDate.format(prevMonthLastDay);
    }
    public static String thisMonthStartDay(){
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("d");

        cal.set((GregorianCalendar.DATE), 1);
        Date thisMonthDayOne = cal.getTime();

        return formatDate.format(thisMonthDayOne);
    }
    public static String thisMonthStartDate(){
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");

        cal.set((GregorianCalendar.DATE), 1);
        Date thisMonthDayOne = cal.getTime();

        return formatDate.format(thisMonthDayOne);
    }
    public static String gen() {
        Random r = new Random( System.currentTimeMillis() );
        return "10" + r.nextInt(2000);
    }

}