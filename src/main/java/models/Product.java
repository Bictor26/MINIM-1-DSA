package models;

public class Product {
    private  String id;
    private String name;
    private double price;
    private int salesCount; // contador de ventas

    public Product(){

    }

    public Product(String id, String name, double price) {
        this();
        this.id = id;
        this.name = name;
        this.price = price;
        this.salesCount = 0; // constructor numero de ventas a 0
    }
    //número total de veces que un producto ha sido vendido
    public int sales() {
        return this.salesCount;
    }

    public double getPrice() {

    return this.price;

    }
    public String getId() {

        return this.id;
    }
    public String getName() {

        return this.name;
    }
    // metodo para incrementar ventas
    public void incrementSales(int quantity) {
        this.salesCount += quantity;
    }


}
