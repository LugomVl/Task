package model;

import java.util.Date;

public class Order {
    private String id;
    private Date date;

    public Order(String id, Date date) {
        this.id = id;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }
}
