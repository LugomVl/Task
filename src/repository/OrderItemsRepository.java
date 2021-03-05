package repository;

import model.OrderItems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class OrderItemsRepository {
    private ArrayList<OrderItems> orderItems = new ArrayList<>();

    public OrderItemsRepository() throws ParseException {
        ArrayList<String> orderss = reader("order_items.csv");
        addMsg(orderss);
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

    public void addMsg(ArrayList<String> orders) throws ParseException {
        for(String str : orders){
            String[] splitted = str.split(",");
            orderItems.add(new OrderItems(splitted[0], splitted[1], Integer.parseInt(splitted[2])));
        }
    }

    public String productId(String orderId){
        for (OrderItems orderItem:orderItems){
            if (orderItem.getOrderId().equals(orderId)){
                return orderItem.getProductId();
            }
        }
        return null;
    }

    public int quantity(String orderId){
        for (OrderItems orderItem:orderItems){
            if (orderItem.getOrderId().equals(orderId)){
                return orderItem.getQuantity();
            }
        }
        return 0;
    }
}
