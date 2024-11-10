package by.mariayuran;

import java.math.BigDecimal;
import java.util.*;

public class BookStore {
    private final List<Order> orders;
    private final List<Book> storeBooks;

    public BookStore(List<Order> orders) {
        this.orders = orders;
        this.storeBooks = storeLibrary();
        System.out.println(storeBooks);
    }

    public List<Order> getOrders() {
        return orders;
    }


    public Order createOrder() {
        Order newOrder = new Order(orders.size() + 1);
        createOrderList(newOrder);
        orders.add(newOrder);
        return newOrder;
    }


    public void cancelOrder(int orderId) {
        Order order = findOrder(orderId);
        if (order != null) {
            order.makeOrderCancelled();
        }
    }


    public void completeOrder(int orderId) {
        Order order = findOrder(orderId);
        if (order != null) {
            order.makeOrderCompleted();
        }
    }

    public Book getAnyBook() {
        Random random = new Random();
        int bookId = random.nextInt(storeBooks.size() - 1);
        return storeBooks.get(bookId);
    }

    private Order findOrder(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId)
                return order;
        }
        return null;
    }


    public void listSortedOrders(int page, int pageSize, String sortBy) {
        List<Order> sortedOrders = new ArrayList<>(orders);

        Comparator<Order> comparator = switch (sortBy) {
            case "id" -> Comparator.comparingInt(Order::getOrderId);
            case "totalPrice" -> Comparator.comparing(Order::getTotalPrice);
            case "openingTimestamp" -> Comparator.comparing(Order::getOpeningTimestamp);
            case "closingTimestamp" -> Comparator.comparing(Order::getClosingTimestamp);
            default -> Comparator.comparing(order -> order.getStatus().name());
        };
        Collections.sort(sortedOrders, comparator);

        int startIndex = page * pageSize;
        int endIndex = Math.min(startIndex + pageSize, sortedOrders.size());


        for (int i = startIndex; i < endIndex; i++) {
            System.out.println(sortedOrders.get(i));
        }
    }

    private void createOrderList(Order newOrder) {
        Scanner scanner = new Scanner(System.in);
        int orderSize = scanner.nextInt();
        for (int i = 0; i < orderSize; i++) {
            newOrder.addBook(getAnyBook());
        }
    }

    private static List<Book> storeLibrary() {
        List<Book> storeBooks = new ArrayList<>();
        storeBooks.add(new Book("The Lord of rings", new BigDecimal("22.55")));
        storeBooks.add(new Book("Hobbit", new BigDecimal("19.95")));
        storeBooks.add(new Book("Незнайка", new BigDecimal("11.95")));
        storeBooks.add(new Book("Мышонок пик", new BigDecimal("7.55")));
        storeBooks.add(new Book("Learn Java", new BigDecimal("22.95")));
        storeBooks.add(new Book("Harry Potter and the Philosopher's stone", new BigDecimal("15.99")));
        storeBooks.add(new Book("Harry Potter and the Chamber of Secrets", new BigDecimal("15.99")));
        storeBooks.add(new Book("Harry Potter and the Prisoner of Azkaban", new BigDecimal("15.99")));
        storeBooks.add(new Book("Harry Potter and the Order of the Phoenix", new BigDecimal("15.99")));
        storeBooks.add(new Book("Harry Potter and the Deathly Hallows",  new BigDecimal("15.99")));
        storeBooks.add(new Book("The Twilight",  new BigDecimal("30.95")));
        storeBooks.add(new Book("50 Shades of gray",  new BigDecimal("38.49")));
        storeBooks.add(new Book("Romeo & Juliette",  new BigDecimal("11.89")));
        storeBooks.add(new Book("Don KiHot",  new BigDecimal("14.55")));
        storeBooks.add(new Book("The Alchemist",  new BigDecimal("6.95")));
        storeBooks.add(new Book("Frida",  new BigDecimal("19.95")));
        storeBooks.add(new Book("The witch of Portobello",  new BigDecimal("19.95")));
        return storeBooks;
    }
}
