package by.mariayuran;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
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


    public Order() {
    }

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

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOpeningTimestamp(LocalDateTime openingTimestamp) {
        this.openingTimestamp = openingTimestamp;
    }

    public void setClosingTimestamp(LocalDateTime closingTimestamp) {
        this.closingTimestamp = closingTimestamp;
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

    public void addBookToOrder(List<Book> lib) {
        Scanner scanner = new Scanner(System.in);
        int orderSize = scanner.nextInt();
        IntStream.range(0, orderSize)
                .forEach(i -> this.addBook(getAnyBook(lib)));

    }

    public Book getAnyBook(List<Book> lib) {
        Random random = new Random();
        int bookId = random.nextInt(lib.size());
        return lib.get(bookId);
    }

//    static void writeOrderToJson(List<Order> orders, ObjectMapper objectMapper) {
//        String filePath = "src/main/resources/orders.json";
//        objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        try {
//            objectMapper.writeValue(new File(filePath), orders);
//            System.out.println("Orders were written to " + filePath);
//        } catch (IOException e) {
//            System.out.println("Orders weren't written to json");
//            e.printStackTrace();
//        }
//    }

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
