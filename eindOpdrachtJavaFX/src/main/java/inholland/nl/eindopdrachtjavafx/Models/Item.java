package inholland.nl.eindopdrachtjavafx.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Item {
    // has item code, availability, title, author,
    private String itemCode;
    private boolean availability;
    private String title;
    private String author;
    private LocalDate lendingDate;

    // constructor
    public Item(String itemCode, boolean availability, String title, String author, String status, LocalDate lendingDate) {
        this.itemCode = itemCode;
        this.availability = availability;
        this.title = title;
        this.author = author;
        this.lendingDate = lendingDate;
    }

    // getters and setters
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getLendingDate() {
        return lendingDate;
    }

    public void setLendingDate(LocalDate lendingDate) {
        this.lendingDate = lendingDate;
    }
}
