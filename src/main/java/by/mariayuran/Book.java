package by.mariayuran;

import java.math.BigDecimal;

public class Book {
    private String title;
    private BigDecimal price;

    public Book() {
    }

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
               "title='" + title + '\'' +
               ", price=" + price +
               '}';
    }
}
