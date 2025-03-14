package models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String dni;
    private final List<Order> orders;

    public User(){
        orders = new ArrayList<>();
    }

    public User(String id, String name, String num) {
        this();
        this.id = id;
        this.name = name;
        this.dni = num;
    }

    public List<Order> orders() {
        return orders;
    }

    // Getters y setters
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return this.dni;
    }

    public void setNumber(String number) {
        this.dni = number;
    }
}
