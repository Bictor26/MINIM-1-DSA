package models;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String userDni; // DNI del usuario que realiza el pedido
    private final List<OrderLine> orderLines; // Lista de líneas de pedido

    // Constructor
    public Order(String userDni) {
        this.userDni = userDni;
        this.orderLines = new ArrayList<>();
    }

    // Añadir Linea de Pedido
    public void addLP(int quantity, String productId) {
        orderLines.add(new OrderLine(quantity, productId));
    }

    // Obtener DNI del usuario que ha hecho el pedido
    public String getUser() {
        return this.userDni;
    }

    // Obtener lineas del pedido
    public List<OrderLine> getOrderLines() {
        return this.orderLines;
    }

    // Calcular precio total del pedido
    public double getTotalPrice(List<Product> productList) {
        double total = 0.0;
        for (OrderLine line : orderLines) {
            Product product = findProductById(productList, line.getProductId());
            if (product != null) {
                total += product.getPrice() * line.getQuantity();
            }
        }
        return total;
    }

    // Buscar producto por ID
    private Product findProductById(List<Product> productList, String productId) {
        for (Product product : productList) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    // Clase interna para representar una línea de pedido
    public static class OrderLine {
        private final int quantity; // Cantidad del producto
        private final String productId; // ID del producto

        // Constructor
        public OrderLine(int quantity, String productId) {
            this.quantity = quantity;
            this.productId = productId;
        }

        // Getters
        public int getQuantity() {
            return quantity;
        }

        public String getProductId() {
            return productId;
        }
    }
}

