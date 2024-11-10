package by.mariayuran;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class BookStoreTest {

    private BookStore bookStore;

    private List<Order> orders;

    @BeforeEach
    void setUp() {
        orders = new ArrayList<>();
        bookStore = new BookStore(orders);

    }

    @Test
    void orderNotNullWhenCreated() {
        Order createdOrder = bookStore.createOrder();
        assertThat(createdOrder).isNotNull();
    }

    @Test
    void checkOrderIdWhenOrderCreated() {
        Order createdOrder = bookStore.createOrder();
        assertThat(createdOrder.getOrderId()).isEqualTo(1);
    }
}