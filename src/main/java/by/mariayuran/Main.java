package by.mariayuran;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        List<Order> orders = new ArrayList<>();
        BookStore bookStore = new BookStore();
        do {
            System.out.println(bookStore.createOrder());
        } while (bookStore.getOrders().size() != 8);

        System.out.println("```````````````````````````````````````````````````````````````");
        System.out.println("Sorted orders:");
        bookStore.listSortedOrders(0, 3, "totalPrice");

        bookStore.cancelOrder(3);
        bookStore.completeOrder(5);
        System.out.println("```````````````````````````````````````````````````````````````");
        for (Order o : bookStore.getOrders()) {
            System.out.println(o.getStatus());

        }
    }
}
