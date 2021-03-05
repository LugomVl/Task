package repository;

import model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class ProductRepository {
    private ArrayList<Product> products = new ArrayList<>();

    public ProductRepository() throws ParseException {
        ArrayList<String> orderss = reader("products.csv");
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
            products.add(new Product(splitted[0], splitted[1], Long.parseLong(splitted[2])));
        }
    }

    public long findPriceByProductId(String productId){
        for (Product product : products) {
            if (product.getId().equals(productId)){
                return product.getPrice();
            }
        }
        return 0;
    }

    public String findUserById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)){
                return product.getName();
            }
        }
        return null;
    }
}
