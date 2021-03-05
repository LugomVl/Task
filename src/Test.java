import service.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formater.parse("2021-01-21");
        Service service = new Service();
        System.out.println(service.findMax(date));

        for (Map.Entry<Date, String> mp : service.allDates().entrySet()) {
            System.out.println(formater.format(mp.getKey()) + " " + mp.getValue());
        }
    }
}
