package by.mariayuran;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookStore {
    private final List<Order> orders;
    private final List<Book> storeBooks;

    public BookStore() {
        this.orders = new ArrayList<>();
        this.storeBooks = storeLibrary();

    }

    public List<Book> getStoreBooks() {
        return storeBooks;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order createOrder() {
        Order newOrder = new Order(orders.size() + 1);
        newOrder.addBookToOrder(storeBooks);
        orders.add(newOrder);
        return newOrder;
    }

    public void cancelOrder(int orderId) {
        Optional.ofNullable(findOrder(orderId))
                .ifPresent(Order::makeOrderCancelled);

    }

    public void completeOrder(int orderId) {
        Optional.ofNullable(findOrder(orderId))
                .ifPresent(Order::makeOrderCompleted);

    }


    private Order findOrder(int orderId) {
        return orders.stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst()
                .orElse(null);

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

        sortedOrders.subList(startIndex, endIndex)
                .forEach(System.out::println);

    }


    private static List<Book> storeLibrary() {
        return Stream.of(
                new Book("The Lord of rings", new BigDecimal("22.55")),
                new Book("Hobbit", new BigDecimal("19.95")),
                new Book("Незнайка", new BigDecimal("11.95")),
                new Book("Мышонок пик", new BigDecimal("7.55")),
                new Book("Learn Java", new BigDecimal("22.95")),
                new Book("Harry Potter and the Philosopher's stone", new BigDecimal("15.99")),
                new Book("Harry Potter and the Chamber of Secrets", new BigDecimal("15.99")),
                new Book("Harry Potter and the Prisoner of Azkaban", new BigDecimal("15.99")),
                new Book("Harry Potter and the Order of the Phoenix", new BigDecimal("15.99")),
                new Book("Harry Potter and the Deathly Hallows", new BigDecimal("15.99")),
                new Book("The Twilight", new BigDecimal("30.95")),
                new Book("50 Shades of gray", new BigDecimal("38.49")),
                new Book("Romeo & Juliette", new BigDecimal("11.89")),
                new Book("Don KiHot", new BigDecimal("14.55")),
                new Book("The Alchemist", new BigDecimal("6.95")),
                new Book("Frida", new BigDecimal("19.95")),
                new Book("The witch of Portobello", new BigDecimal("19.95"))
        ).collect(Collectors.toList());

    }
}
