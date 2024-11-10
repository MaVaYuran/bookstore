package by.mariayuran;

import java.math.BigDecimal;

public class Book {
    private String title;
    private BigDecimal price;

    public Book(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
               "title='" + title + '\'' +
               ", price=" + price +
               '}';
    }
}
