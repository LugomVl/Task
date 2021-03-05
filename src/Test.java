import service.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formater.parse("2021-01-21");
        Service service = new Service();
        System.out.println(formater.format(date));
        System.out.println(service.findMax(date));
    }
}
