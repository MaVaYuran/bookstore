package by.mariayuran.library;

import by.mariayuran.Book;
import by.mariayuran.Order;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class LibraryRepositoryImpl implements LibraryRepository {

    private final ObjectMapper objectMapper;
    private final String filePath;

    public LibraryRepositoryImpl(String classpath) {
        this.objectMapper = new ObjectMapper();
        this.filePath = classpath;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Override
    public List<Book> loadLibrary() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IOException("File not found " + filePath);
            }
            return objectMapper.readValue(inputStream, new TypeReference<List<Book>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to load library from " + filePath, e);
        }
    }

    @Override
    public void writeOrderToJson(List<Order> orders) {
        String filePath = "src/main/resources/orders.json";
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writeValue(new File(filePath), orders);
            System.out.println("Orders were written to " + filePath);
        } catch (IOException e) {
            System.out.println("Orders weren't written to json");
            e.printStackTrace();
        }
    }
}
