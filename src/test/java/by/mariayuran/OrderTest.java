package by.mariayuran;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order;
    private Book book1;
    private Book book2;

    @BeforeEach
    public void setUp() {
        order = new Order(1);
        book1 = new Book("Book one", new BigDecimal("5.99"));
        book2 = new Book("Book two", new BigDecimal("10.99"));

    }

    @Test
    void books_size_when_book_added() {
        order.addBook(book1);
        order.addBook(book2);
        List<Book> books = order.getBooks();

        assertThat(books).hasSize(2);
    }

    @Test
    void books_contains_book_if_book_added() {
        order.addBook(book1);
        List<Book> books = order.getBooks();

        assertThat(books).contains(book1);
    }

    @Test
    void totalPrice_when_book_added() {
        order.addBook(book1);
        order.addBook(book2);

        assertThat(order.getTotalPrice()).isEqualTo(new BigDecimal("16.98"));
    }

    @Test
    void orderStatus_and_closingTimestamp_when_order_cancelled() {
        order.makeOrderCancelled();

        assertThat(order.getStatus()).isEqualByComparingTo(OrderStatus.CANCELLED);
        assertThat(order.getClosingTimestamp()).isNotNull();

    }

    @Test
    void orderStatus_and_closingTimestamp_when_order_completed() {
        order.makeOrderCompleted();

        assertThat(order.getStatus()).isEqualByComparingTo(OrderStatus.COMPLETED);
        assertThat(order.getClosingTimestamp()).isNotNull();
    }

    @Test
    void testToString() {
        order.addBook(book1);
        order.addBook(book2);
        System.out.println(order.toString());
        String expected = "Order details:\n" + "Total price: 16.98\n" +
                          "Book: Book one, Price: 5.99\n" +
                          "Book: Book two, Price: 10.99\n";

        assertThat(order.toString()).isEqualTo(expected);
    }
}