package by.mariayuran;

import by.mariayuran.library.LibraryRepository;
import by.mariayuran.library.LibraryRepositoryImpl;

public class Main {
    public static void main(String[] args) {
         LibraryRepository libraryRepository = new LibraryRepositoryImpl("library.json");
        BookStore bookStore = new BookStore(libraryRepository);


        do {
            System.out.println(bookStore.createOrder());
        } while (bookStore.getOrders().size() != 8);

        System.out.println("```````````````````````````````````````````````````````````````");
        System.out.println("Sorted orders:");
        bookStore.listSortedOrders(0, 3, "totalPrice");

        bookStore.cancelOrder(3);
        bookStore.completeOrder(5);

        libraryRepository.writeOrderToJson(bookStore.getOrders());
        System.out.println("```````````````````````````````````````````````````````````````");
        for (Order o : bookStore.getOrders()) {
            System.out.println(o.getStatus());

        }
    }
}
