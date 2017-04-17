package one.kii.summer.datetime;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by WangYanJiong on 17/04/2017.
 */
public class Another {

    public Date day(int delta) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, delta);
        return calendar.getTime();
    }
}
