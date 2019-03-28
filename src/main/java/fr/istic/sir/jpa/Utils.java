package fr.istic.sir.jpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static Date parseToDate(String dateString) throws ParseException {
        SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.FRANCE);
        String date = dateString.replaceAll("\\+0([0-9]){1}\\:00", "+0$100");

        return iso8601Format.parse(date);
    }
}
