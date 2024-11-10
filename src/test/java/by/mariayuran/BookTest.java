package by.mariayuran;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    Book book;

    @BeforeEach
    void setUp() {
        book = new Book("Clean code", new BigDecimal("9.99"));
    }

    @Test
    void getTitle_getPrice() {
        assertAll(
                () -> assertEquals("Clean code", book.getTitle(),
                        "Title should match the expected title"),
                () -> assertEquals(new BigDecimal("9.99"), book.getPrice(),
                        "Price should match the expected price "));
    }


    @Test
    void testToString() {
        String actualString = book.toString();
        String expectedString = "Book{title='Clean code', price=9.99}";
        assertEquals(expectedString, actualString,
                "toString should return the expected string representation");
    }
}