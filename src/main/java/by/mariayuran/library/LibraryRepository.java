package by.mariayuran.library;

import by.mariayuran.Book;
import by.mariayuran.Order;

import java.util.List;

public interface LibraryRepository {
    List<Book> loadLibrary();
   void writeOrderToJson(List<Order> orders);
}
