package by.mariayuran;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderId;
    private List<Book> books = new ArrayList<>();
    private double totalPrice;
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

    public double getTotalPrice() {
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
            totalPrice += book.getPrice();
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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order details:\n");
        DecimalFormat df = new DecimalFormat("#.##");
        sb.append("Total price: ").append(df.format(totalPrice)).append("\n");
        for (Book book : books) {
            sb.append("Book: ").append(book.getTitle()).append(", Price: ").append(book.getPrice()).append("\n");
        }
        return sb.toString();
    }
}
