package repository;

import model.Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderRepository {
    private ArrayList<Order> orders = new ArrayList<>();
    private Set<Date> dates = new HashSet<>();

    public OrderRepository() throws ParseException {
        ArrayList<String> orderss = reader("orders.csv");
        addMsg(orderss);
        arrayDates();
    }

    public ArrayList<String> reader(String fileName) {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                list.add(nextLine);
            }
            list.remove(0);
        } catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }

    public void addMsg(ArrayList<String> mess) throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        for(String str : mess){
            String[] splitted = str.split(",");
            String[] dateSplit = splitted[1].split("T");
            orders.add(new Order(splitted[0], formater.parse(dateSplit[0])));
        }
    }

    public void arrayDates(){
        for (Order order : this.orders){
            dates.add(order.getDate());
        }
    }

    public List<String> toArray(Date data){
        List<String> to = new ArrayList<>();
        for (Order order : this.orders) {
            if (order.getDate().equals(data)) {
                to.add(order.getId());
            }
        }
        return to;
    }

    public Set<Date> getDates() {
        return dates;
    }
}
