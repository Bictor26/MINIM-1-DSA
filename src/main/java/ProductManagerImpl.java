import models.Order;
import models.Product;
import models.User;
import java.util.*;

public class ProductManagerImpl implements ProductManager {
    private final List<Product> productList;
    private final Queue<Order> orderQueue;
    private final HashMap<String, User> users;



    public ProductManagerImpl() {
        productList = new ArrayList<>();
        orderQueue = new LinkedList<>();
        users = new HashMap<>();
    }

    public static ProductManager getInstance() {
        return new ProductManagerImpl();
    }

    @Override
    public void addProduct(String id, String name, double price) {

        productList.add(new Product(id, name, price));
    }

    @Override
    public void addUser(String id, String name, String dni) {
        if (id == null || id.trim().isEmpty() || name == null || name.trim().isEmpty() || dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID, nombre y DNI no pueden ser nulos o vacíos.");
        }
        User user = new User(id, name, dni);
        users.put(dni, user);
    }

    //ordenar la lista de mayor a menor precio
    @Override
    public List<Product> getProductsByPrice() {
        //copia para no modificar el orignal
        List<Product> sortedProducts = new ArrayList<>(productList);

        // ordenar de mayor a menor (hecho con chat gpt)
        Collections.sort(sortedProducts, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p2.getPrice(), p1.getPrice());
            }
        });

        return sortedProducts;

    }

    @Override
    public void addOrder(Order order) {
        String userDni = order.getUser();
        if (!users.containsKey(userDni)) {
            throw new IllegalArgumentException("El usuario no existe");
        }
        orderQueue.add(order);
    }

    @Override
    public int numOrders() {
        return orderQueue.size();
    }


    @Override
    public Order deliverOrder() {
        Order order = orderQueue.poll(); // Obtener la orden más antigua
        if (order != null) {
            User user = users.get(order.getUser());
            if (user != null) {
                user.orders().add(order); // Añadir la orden al usuario
            }

            // Incrementar las ventas de los productos
            for (Order.OrderLine line : order.getOrderLines()) {
                Product product = getProduct(line.getProductId());
                if (product != null) {
                    product.incrementSales(line.getQuantity());
                }
            }
        }
        return order;
    }

    @Override
    public Product getProduct(String productId) {
        for (Product product : productList) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null; //null si no se encuentra el producto
    }
    @Override
    public User getUser(String dni) {
        // Validar que el DNI no sea nulo o vacío
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede ser nulo o vacío.");
        }

        // Obtener el usuario desde el HashMap de usuarios
        User user = users.get(dni);

        // Verificar si el usuario existe
        if (user == null) {
            throw new RuntimeException("No se encontró ningún usuario con el DNI: " + dni);
        }

        // Retornar el usuario encontrado
        return user;
    }


}
