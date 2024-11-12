package by.mariayuran;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Order {

    private int orderId;
    private List<Book> books = new ArrayList<>();
    private BigDecimal totalPrice = new BigDecimal("0.00");
    private LocalDateTime openingTimestamp;
    private LocalDateTime closingTimestamp;
    private OrderStatus status;


    public Order(int orderId) {
        this.orderId = orderId;
        this.openingTimestamp = LocalDateTime.now();
        status = OrderStatus.OPEN;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Book> getBooks() {
        return books;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getOpeningTimestamp() {
        return openingTimestamp;
    }

    public LocalDateTime getClosingTimestamp() {
        return closingTimestamp;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void addBook(Book book) {
        if (this.getStatus() == OrderStatus.OPEN) {
            books.add(book);
            totalPrice = totalPrice.add(book.getPrice());
        }
    }

    public void makeOrderCancelled() {
        if (status == OrderStatus.OPEN) {
            this.setStatus(OrderStatus.CANCELLED);
            this.closingTimestamp = LocalDateTime.now();

        }
    }

    public void makeOrderCompleted() {
        if (status == OrderStatus.OPEN) {
            this.setStatus(OrderStatus.COMPLETED);
            this.closingTimestamp = LocalDateTime.now();
        }

    }

    public void addBookToOrder(List<Book> books) {
        Scanner scanner = new Scanner(System.in);
        int orderSize = scanner.nextInt();
        IntStream.range(0, orderSize)
                .forEach(i -> this.addBook(getAnyBook(books)));

    }

    public Book getAnyBook(List<Book> books) {
        Random random = new Random();
        int bookId = random.nextInt(books.size() - 1);
        return books.get(bookId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order details:\n");
        sb.append("Total price: ").append(totalPrice).append("\n");
        for (Book book : books) {
            sb.append("Book: ").append(book.getTitle()).append(", Price: ").append(book.getPrice()).append("\n");
        }
        return sb.toString();
    }
}
