package by.mariayuran;

import by.mariayuran.library.LibraryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookStoreTest {
    @InjectMocks
    BookStore bookStore;
    @Mock
    LibraryRepository libraryRepository;

    @BeforeEach
    void setUp() {
       System.setIn(new ByteArrayInputStream("2".getBytes()));

    }

    @Test
    void createOrder() {
        when(libraryRepository.loadLibrary()).thenReturn(List.of(new Book("Book1", new BigDecimal("2")),
                        new Book("Book2", new BigDecimal("3"))));
        bookStore.createOrder();
        List<Order> orders = bookStore.getOrders();
        Order order = orders.get(0);
        assertTrue(!orders.isEmpty());
        assertEquals(1, orders.size());
        assertNotNull(order);
    }

    @Test
    void cancelOrder() {
        when(libraryRepository.loadLibrary()).thenReturn(List.of(new Book("Book1", new BigDecimal("2")),
                new Book("Book2", new BigDecimal("3"))));
        int id = bookStore.createOrder().getOrderId();
        assertEquals(OrderStatus.OPEN, bookStore.getOrders().get(0).getStatus());
        bookStore.cancelOrder(id);
        assertEquals(OrderStatus.CANCELLED, bookStore.getOrders().get(0).getStatus());

    }

    @Test
    void completeOrder() {
        when(libraryRepository.loadLibrary()).thenReturn(List.of(new Book("Book1", new BigDecimal("2")),
                new Book("Book2", new BigDecimal("3"))));
        int id = bookStore.createOrder().getOrderId();
        assertEquals(OrderStatus.OPEN, bookStore.getOrders().get(0).getStatus());
        bookStore.cancelOrder(id);
        assertEquals(OrderStatus.CANCELLED, bookStore.getOrders().get(0).getStatus());
    }

    @Test
    void listSortedOrders() {

        Order order1 = new Order(1);
        order1.addBook(new Book("Horizont", new BigDecimal("2.99")));

        Order order2 = new Order(2);
        order2.addBook(new Book("Rainbow", new BigDecimal("7.95")));

        Order order3 = new Order(3);
        order3.addBook(new Book("Fifth mountain", new BigDecimal("5.25")));

        bookStore.listSortedOrders(0, 5, "totalPrice");
        bookStore.getOrders().add(order1);
        bookStore.getOrders().add(order2);
        bookStore.getOrders().add(order3);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        bookStore.listSortedOrders(0, 5, "totalPrice");

        String expectedOutput = "Order details:\n" + "Total price: 2.99\n" + "Book: Horizont, Price: 2.99\n\r\n" +
                                "Order details:\n" + "Total price: 5.25\n" + "Book: Fifth mountain, Price: 5.25\n\r\n" +
                                "Order details:\n" + "Total price: 7.95\n" + "Book: Rainbow, Price: 7.95\n\r\n";

        assertEquals(expectedOutput.trim(), outContent.toString().trim());

    }
}
