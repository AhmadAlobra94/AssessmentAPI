package Common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Ahmad AlObra
 */
public abstract class DateTimeHelper {

    public static String getDateTime(String format, int plusDays){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, plusDays);
        return new SimpleDateFormat(format).format(c.getTime());

    }
}
