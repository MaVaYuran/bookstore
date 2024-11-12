package by.mariayuran;

public class Main {
    public static void main(String[] args) {
        BookStore bookStore = new BookStore();

        do {
            System.out.println(bookStore.createOrder());
        } while (bookStore.getOrders().size() != 8);

        System.out.println("```````````````````````````````````````````````````````````````");
        System.out.println("Sorted orders:");
        bookStore.listSortedOrders(0, 3, "to1talPrice");

        bookStore.cancelOrder(3);
        bookStore.completeOrder(5);

        Order.writeOrderToJson(bookStore.getOrders());
        System.out.println("```````````````````````````````````````````````````````````````");
        for (Order o : bookStore.getOrders()) {
            System.out.println(o.getStatus());

        }
    }
}
