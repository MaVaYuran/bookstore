package by.mariayuran;

import by.mariayuran.library.LibraryRepository;
import java.util.*;

public class BookStore {
    private final List<Order> orders;
    private final LibraryRepository libraryRepository;

    public BookStore(LibraryRepository libraryRepository) {
        this.orders = new ArrayList<>();
        this.libraryRepository = libraryRepository;
    }


    public List<Order> getOrders() {
        return orders;
    }

    public Order createOrder() {
        Order newOrder = new Order(orders.size() + 1);
        newOrder.addBookToOrder(libraryRepository.loadLibrary());
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
}
