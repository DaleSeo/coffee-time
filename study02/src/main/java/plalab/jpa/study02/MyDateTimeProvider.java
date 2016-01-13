package plalab.jpa.study02;

import org.springframework.data.auditing.DateTimeProvider;

import java.util.Calendar;

public class MyDateTimeProvider implements DateTimeProvider {
    @Override
    public Calendar getNow() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        return calendar;
    }
}
