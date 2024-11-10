package by.mariayuran;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderTest {
    private Order order;
    private Book book1;
    private Book book2;
    BookStore bookStore;


    @BeforeEach
    public void setUp() {
        bookStore = new BookStore();
        order = new Order(1);
        book1 = new Book("Book one", new BigDecimal("5.99"));
        book2 = new Book("Book two", new BigDecimal("10.99"));

    }

    @Test
    void booksSizeWhenBookAdded() {
        order.addBook(book1);
        order.addBook(book2);
        List<Book> books = order.getBooks();

        assertThat(books).hasSize(2);
    }

    @Test
    void booksContainsBookIfBookAdded() {
        order.addBook(book1);
        List<Book> books = order.getBooks();

        assertThat(books).contains(book1);
    }

    @Test
    void totalPriceWhenBookAdded() {
        order.addBook(book1);
        order.addBook(book2);

        assertThat(order.getTotalPrice()).isEqualTo(new BigDecimal("16.98"));
    }

    @Test
    void orderStatusAndClosingTimestampWhenOrderCancelled() {
        order.makeOrderCancelled();

        assertThat(order.getStatus()).isEqualByComparingTo(OrderStatus.CANCELLED);
        assertThat(order.getClosingTimestamp()).isNotNull();
   }

    @Test
    void orderStatusAndClosingTimestampWhenOrderCompleted() {
        order.makeOrderCompleted();

        assertThat(order.getStatus()).isEqualByComparingTo(OrderStatus.COMPLETED);
        assertThat(order.getClosingTimestamp()).isNotNull();
    }

    @Test
    void getAnyBookTest() {
        Book book = order.getAnyBook(bookStore.getStoreBooks());
        assertNotNull(book);
    }

    @Test
    void addBookToOrderTest() {
        Order order = new Order(1);
        System.setIn(new ByteArrayInputStream("2".getBytes()));
        order.addBookToOrder(bookStore.getStoreBooks());
        assertEquals(2, order.getBooks().size());
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