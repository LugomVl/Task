package service;

import repository.OrderItemsRepository;
import repository.OrderRepository;
import repository.ProductRepository;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Service {
    OrderItemsRepository orderItemsRepository;
    OrderRepository orderRepository;
    ProductRepository productRepository;
    Map<Date, String> finalResult = new HashMap<>();

    public Service() throws ParseException {
        this.orderItemsRepository = new OrderItemsRepository();
        this.orderRepository = new OrderRepository();
        this.productRepository = new ProductRepository();
    }

    public Map<Date, String> allDates(){
        for (Date date : orderRepository.getDates()){
            finalResult.put(date, findMax(date));
        }
        return this.finalResult;
    }

    public String findMax(Date data) {
        Map<String, Long> map = new HashMap<>();
        for (String orderId : orderRepository.toArray(data)) {
            String productId = orderItemsRepository.productId(orderId);
            if (map.containsKey(productId)) {
                map.put(productId, map.get(productId) + (orderItemsRepository.quantity(orderId)*productRepository.findPriceByProductId(productId)));
            } else {
                map.put(productId, orderItemsRepository.quantity(orderId)*productRepository.findPriceByProductId(productId));
            }
        }
        return maxCount(map);
    }

    private String maxCount(Map<String, Long> map) {
        String id = "";
        long max = 0;
        for (Map.Entry<String, Long> mp : map.entrySet()) {
            if (mp.getValue() > max) {
                id = mp.getKey();
                max = mp.getValue();
            }
        }
        return productRepository.findUserById(id) + " " + max;
    }
}
