package by.mariayuran.library;

import by.mariayuran.Book;
import by.mariayuran.Order;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryRepositoryImplTest {

    private LibraryRepositoryImpl repository;

    @Test
    void loadLibraryFromFile() {
        repository = new LibraryRepositoryImpl("test_library.json");

        List<Book> books = repository.loadLibrary();

        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals(17, books.size());
    }

    @Test
    void loadLibraryThrowException() {
        repository = new LibraryRepositoryImpl("non_existing_file.json");

        assertThrows(RuntimeException.class, repository::loadLibrary);
    }

    @Test
    void writeOrderToFile() throws IOException {
        File tempFile = Files.createTempFile("test_orders", ".json").toFile();
        tempFile.deleteOnExit();
        repository = new LibraryRepositoryImpl(tempFile.getPath());
        repository.getObjectMapper().registerModule(new JavaTimeModule());

        List<Order> orders = List.of(new Order(1));

        repository.writeOrderToJson(orders);

        assertTrue(tempFile.exists());

    }
}