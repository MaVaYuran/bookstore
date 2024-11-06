package by.mariayuran;

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

    public Book getAnyBook(List<Book> books) {
        Random random = new Random();
        int bookId = random.nextInt(books.size() - 1);
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
            case "totalPrice" -> Comparator.comparingDouble(Order::getTotalPrice);
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
            newOrder.addBook(getAnyBook(storeBooks));
        }
    }

    private static List<Book> storeLibrary() {
        List<Book> storeBooks = new ArrayList<>();
        storeBooks.add(new Book("The Lord of rings", 29.95));
        storeBooks.add(new Book("Hobbit", 19.95));
        storeBooks.add(new Book("Незнайка", 11.95));
        storeBooks.add(new Book("Мышонок пик", 7.55));
        storeBooks.add(new Book("Learn Java", 22.95));
        storeBooks.add(new Book("Harry Potter and the Philosopher's stone", 15.99));
        storeBooks.add(new Book("Harry Potter and the Chamber of Secrets", 15.99));
        storeBooks.add(new Book("Harry Potter and the Prisoner of Azkaban", 15.99));
        storeBooks.add(new Book("Harry Potter and the Order of the Phoenix", 15.99));
        storeBooks.add(new Book("Harry Potter and the Deathly Hallows", 15.99));
        storeBooks.add(new Book("The Twilight", 30.95));
        storeBooks.add(new Book("50 Shades of gray", 38.49));
        storeBooks.add(new Book("Romeo & Juliette", 11.85));
        storeBooks.add(new Book("Don KiHot", 14.95));
        storeBooks.add(new Book("The Alchemist", 6.95));
        storeBooks.add(new Book("Brida", 19.95));
        storeBooks.add(new Book("The witch of Portobello", 19.95));
        return storeBooks;
    }
}
