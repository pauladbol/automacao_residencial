package com.example.pauladbol.houseautomation;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by pauladbol on 2017-06-12.
 */
public class TimeToolkit
{
    public static final long DAY_LENGTH = 86400000;
    public static final long HOUR_LENGTH = 3600000;
    public static final long MINUTE_LENGTH = 60000;
    public static final long SECOND_LENGTH = 1000;

    /**
     * Format: yyyy-MM-dd'T'HH:mm:ssXXX
     * Sample: 2015-05-21T19:57:58-03:00
     */
    public static final String UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";

    /**
     * Format: dd/MM/yyyy
     * Sample: 21/05/2015
     */
    public static final String STANDARD_DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Format: dd/MM/yyyy HH:mm:ss
     * Sample: 21/05/2015 19:57:58
     */
    public static final String STANDARD_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    /**
     * Format: HH:mm:ss
     * Sample: 19:57:58
     */
    public static final String STANDARD_TIME_FORMAT = "HH:mm:ss";

    /**
     * Format: EEEE, dd 'de' MMMM 'de' yyyy
     * Sample: Quinta-feira, 21 de Maio de 2015
     */
    public static final String FULL_DATE_FORMAT = "EEEE, dd 'de' MMMM 'de' yyyy";

    public static String toString(Date date)
    {
        return toString(date, STANDARD_DATE_FORMAT);
    }

    /*public static String toString(Date date, String format)
    {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        return formater.format(date);
    }*/

    /**
     * Returns the biggest date between dates 1 and 2
     * If one of them is null, returns the other
     * If both are null, returns null;
     *
     * @param date1 the first date to compare
     * @param date2 the second date to compare
     * <p>
     * @return the biggest date
     */
    public static Date max(Date date1, Date date2)
    {
        if (date1 != null && date2 != null)
        {
            return date1.after(date2) ? date1 : date2;
        }

        if (date1 != null)
        {
            return date1;
        }

        return date2;
    }

    public static Date min(Date date1, Date date2)
    {
        if (date1 != null && date2 != null)
        {
            return date1.before(date2) ? date1 : date2;
        }

        if (date1 != null)
        {
            return date1;
        }

        return date2;
    }

    public static Date getFirstDateOfMonth()
    {
        return getFirstDateOfMonth(Calendar.getInstance().get(Calendar.MONTH));
    }

    public static Date getFirstDateOfMonth(int month)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return calendar.getTime();
    }

    public static Date getFirstDateOfMonth(Date aDay)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(aDay);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return calendar.getTime();
    }

    public static Date getLastDateOfMonth()
    {
        return getLastDateOfMonth(Calendar.getInstance().get(Calendar.MONTH));
    }

    public static Date getLastDateOfMonth(int month)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        return calendar.getTime();
    }

    public static Date getLastDateOfMonth(Date aDay)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(aDay);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        return calendar.getTime();
    }

    public static Date getFirstDateOfYear(Date aDay)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(aDay);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return calendar.getTime();
    }

    public static Date getLastDateOfYear(Date aDay)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(aDay);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);

        return calendar.getTime();
    }

    public static Date addDays(int days)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    public static Date addDays(Date fromDate, int days)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    public static Date addTime(Time time)
    {
        return addTime(new Date(), time);
    }

    public static Date addTime(Date fromDate, Time time)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        calendar.add(Calendar.HOUR_OF_DAY, time.getHour());
        calendar.add(Calendar.MINUTE, time.getMinute());
        calendar.add(Calendar.SECOND, time.getSecond());

        return calendar.getTime();
    }

    public static boolean isSameDay(Date date1, Date date2)
    {
        if (date1 == null && date2 == null)
        {
            return true;
        }

        if (date1 != null && date2 != null)
        {
            return TimeToolkit.distDays(date1, date2) == 0;
        }

        return false;
    }

    public static boolean isSameDateTime(Date date1, Date date2)
    {
        boolean ok = false;

        if (date1 != null && date2 != null)
        {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            String f1 = format.format(date1);
            String f2 = format.format(date2);

            ok = f1.compareTo(f2) == 0;
        }

        return ok;
    }

    public static boolean isSameTime(Date date1, Date date2)
    {
        boolean ok = false;

        if (date1 != null && date2 != null)
        {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
            String f1 = format.format(date1);
            String f2 = format.format(date2);

            ok = f1.compareTo(f2) == 0;
        }

        return ok;
    }

    /*public static Date parseString(String string)
    {
        return parseString(string, STANDARD_DATE_FORMAT);
    }*/

    /**
     * Parses the given string with the given format. If it is not possible
     * to convert the string to date, returns null.
     *
     * @param string: a date as string
     * @param format: the date format
     * <p>
     * @return the date, or null, case is not possible to convert the string into date using the given format
     */
    /*public static Date parseString(String string, String format)
    {
        try
        {
            return new SimpleDateFormat(format).parse(string);
        } catch (Exception e)
        {
            return null;
        }
    }*/

    public static Date joinDateHour(Date date, Date hour)
    {
        if (date == hour)
        {
            return date;
        }

        Calendar dt = Calendar.getInstance();
        Calendar hr = Calendar.getInstance();
        Calendar dth = Calendar.getInstance();

        dt.setTime(date);
        hr.setTime(hour);

        dth.set(dt.get(Calendar.YEAR), dt.get(Calendar.MONTH), dt.get(Calendar.DAY_OF_MONTH),
                hr.get(Calendar.HOUR_OF_DAY), hr.get(Calendar.MINUTE), hr.get(Calendar.SECOND));

        return dth.getTime();
    }

    public static boolean timeAfter(Date after, Date time)
    {
        Calendar afterCalendar = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();

        afterCalendar.setTime(after);
        calendar.setTime(time);

        if (afterCalendar.get(Calendar.HOUR_OF_DAY) > calendar.get(Calendar.HOUR_OF_DAY))
        {
            return true;
        }

        if (afterCalendar.get(Calendar.HOUR_OF_DAY) < calendar.get(Calendar.HOUR_OF_DAY))
        {
            return false;
        }

        if (afterCalendar.get(Calendar.MINUTE) > calendar.get(Calendar.MINUTE))
        {
            return true;
        }

        if (afterCalendar.get(Calendar.MINUTE) < calendar.get(Calendar.MINUTE))
        {
            return false;
        }

        if (afterCalendar.get(Calendar.SECOND) > calendar.get(Calendar.SECOND))
        {
            return true;
        }

        return false;
    }

    public static boolean timeBefore(Date before, Date time)
    {
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();

        beforeCalendar.setTime(before);
        calendar.setTime(time);

        if (beforeCalendar.get(Calendar.HOUR_OF_DAY) < calendar.get(Calendar.HOUR_OF_DAY))
        {
            return true;
        }

        if (beforeCalendar.get(Calendar.HOUR_OF_DAY) > calendar.get(Calendar.HOUR_OF_DAY))
        {
            return false;
        }

        if (beforeCalendar.get(Calendar.MINUTE) < calendar.get(Calendar.MINUTE))
        {
            return true;
        }

        if (beforeCalendar.get(Calendar.MINUTE) > calendar.get(Calendar.MINUTE))
        {
            return false;
        }

        if (beforeCalendar.get(Calendar.SECOND) < calendar.get(Calendar.SECOND))
        {
            return true;
        }

        return false;
    }

    /**
     * Returns if "between" is between "firstDate" and "lastDate" inclusive.
     */
    public static boolean betweenTwoDates(Date firstDate, Date lastDate, Date between)
    {
        if (firstDate != null && lastDate != null)
        {
            if (isSameDay(firstDate, between))
            {
                return true;
            }

            if (isSameDay(lastDate, between))
            {
                return true;
            }

            if (between.after(firstDate) && between.before(lastDate))
            {
                return true;
            }

            return false;
        }

        if (firstDate != null)
        {
            if (isSameDay(firstDate, between))
            {
                return true;
            }

            if (between.after(firstDate))
            {
                return true;
            }

            return false;
        }

        if (lastDate != null)
        {
            if (isSameDay(lastDate, between))
            {
                return true;
            }

            if (between.before(lastDate))
            {
                return true;
            }

            return false;
        }

        return true;
    }

    public static boolean betweenTwoTimes(Date firstTime, Date lastTime, Date between)
    {
        if (firstTime != null && lastTime != null)
        {
            if (isSameTime(firstTime, between))
            {
                return true;
            }

            if (isSameTime(lastTime, between))
            {
                return true;
            }

            if (timeAfter(between, firstTime) && timeBefore(between, lastTime))
            {
                return true;
            }

            return false;
        }

        if (firstTime != null)
        {
            if (isSameTime(firstTime, between))
            {
                return true;
            }

            if (timeAfter(between, firstTime))
            {
                return true;
            }

            return false;
        }

        if (lastTime != null)
        {
            if (isSameTime(lastTime, between))
            {
                return true;
            }

            if (timeBefore(between, lastTime))
            {
                return true;
            }

            return false;
        }

        return true;
    }

    /**
     * Calculates how many days is date minus new Date()
     */
    public static int distDays(Date date)
    {
        return distDays(date, new Date());
    }

    /**
     * Calculates how many days is date minus target
     */
    public static int distDays(Date date, Date target)
    {
        long daysDate = date.getTime() - (date.getTime() % DAY_LENGTH);
        long daysTarget = target.getTime() - (target.getTime() % DAY_LENGTH);

        return (int) ((daysDate - daysTarget) / DAY_LENGTH);
    }

    /**
     * Returns if date1 is equals date2
     *
     * @param date1 the first date, could be null
     * @param date2 the second date, could be null
     *
     * @return true if date1 is the same as date2, false otherwise
     */
    public static boolean equals(Date date1, Date date2)
    {
        if (date1 == null && date2 == null)
        {
            return true;
        }

        if (date1 != null && date2 != null)
        {
            return TimeToolkit.distDays(date1, date2) == 0;
        }

        return false;
    }

    public static Date getDate(Date date)
    {
        try
        {
            SimpleDateFormat formater = new SimpleDateFormat("ddMMyyyy");
            String formated = formater.format(date);
            return formater.parse(formated);
        } catch (Exception e)
        {
            return null;
        }
    }

    public static boolean isWeekEnd(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayWeek == Calendar.SUNDAY || dayWeek == Calendar.SATURDAY;
    }

    /**
     * Returns if the given date is between (this time - timeBefore) and (this time + timeAfter).
     * If date is null, returns false. Field can't be null;
     *
     * @param date:       the date to be checked
     * @param timeBefore: the first margin
     * @param timeAfter:  the last margin
     * @param field:      the measurement unity of timeBefore and timeAfter
     *
     * @return if date is between the given interval of field, with base on the current time
     */
    public static boolean isInRange(Date date, int timeBefore, int timeAfter, DateField field)
    {
        if (date == null)
        {
            return false;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(field.getValue(), -timeBefore);

        int dist = TimeToolkit.distDays(date, calendar.getTime());

        if (dist < 0)
        {
            return false;
        }

        calendar = Calendar.getInstance();
        calendar.add(field.getValue(), timeAfter);

        dist = TimeToolkit.distDays(calendar.getTime(), date);
        return dist >= 0;
    }

    public static Pair<Date, Date> getMonthInterval()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        Date firstDate = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        Date lastDate = calendar.getTime();

        return new Pair<>(firstDate, lastDate);
    }

    /**
     * If the given date is null returns null else returns the given date at
     * 00:00:00
     *
     * @param date the date to ajust the time
     * <p>
     * @return The given date as it is, just seting the time to 00:00:00
     */
    public static Date toBegining(Date date)
    {
        if (date == null)
        {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    /**
     * If the given date is null returns null else returns the given date at
     * 23:59:59
     *
     * @param date the date to ajust the time
     * <p>
     * @return The given date as it is, just seting the time to 23:59:59
     */
    public static Date toEnding(Date date)
    {
        if (date == null)
        {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

    public static int calculateYearsOld(Date birthday)
    {
        return distYears(birthday, new Date());
    }

    /**
     * Calculates how many years is TO minus FROM
     * <p>
     * @param from: the smaller date
     * @param to:   the bigger date
     * <p>
     * @return (to - from) in years
     */
    public static int distYears(Date from, Date to)
    {
        return TimeToolkit.distDays(to, from) / 365;
    }

    public static int getCurrentYear()
    {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static Month getCurrentMonth()
    {
        return Month.forDate(new Date());
    }

    public static Date toDate(int day, Month month, int year, int hour, int minute, int second)
    {
        if (month == null)
        {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month.getValue(), day, hour, minute, second);

        return calendar.getTime();
    }

    public static Date toDate(int day, Month month, int year)
    {
        if (month == null)
        {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month.getValue(), day);

        return calendar.getTime();
    }

    public static Date toDate(LocalDateTime localDateTime)
    {
        if (localDateTime == null)
        {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(localDateTime.getYear(), localDateTime.getMonthValue() - 1,
                localDateTime.getDayOfMonth(), localDateTime.getHour(),
                localDateTime.getMinute(), localDateTime.getSecond());

        return calendar.getTime();
    }

    public static Date toDate(LocalDate localDate)
    {
        return localDate == null ? null
                : toDate(localDate.getDayOfMonth(),
                Month.forValue(localDate.getMonthValue() - 1),
                localDate.getYear());
    }

    public static LocalDate toDate(Date date)
    {
        if (date == null)
        {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        return LocalDate.of(year, month, day);
    }

    public static void main(String[] args)
    {
        Date now = new Date();
        System.out.println("now = " + now);
        System.out.println("after = " + addTime(new Time(0, 90, 0)));
    }
}

