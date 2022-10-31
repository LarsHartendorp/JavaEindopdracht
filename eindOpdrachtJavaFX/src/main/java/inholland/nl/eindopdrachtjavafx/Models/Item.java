package inholland.nl.eindopdrachtjavafx.Models;

import inholland.nl.eindopdrachtjavafx.DAL.Database;
import java.io.Serializable;
import java.time.LocalDate;

public class Item implements Serializable {
    // has item code, availability, title, author
    private int itemCode;
    String itemCodeCSV;
    private boolean availability;
    private String title;
    private String author;
    private LocalDate lendingDate;
    private Database database;
    private LocalDate expectedReturnDate;

    // constructor
    public Item(boolean availability, String title, String author, LocalDate lendingDate) {
        this.availability = availability;
        this.title = title;
        this.author = author;
        this.lendingDate = lendingDate;
    }
    public Item(String itemCodeCSV, boolean availability, String title, String author) {
        this.itemCodeCSV = itemCodeCSV;
        this.availability = availability;
        this.title = title;
        this.author = author;
    }

    public Item(int itemCode, boolean availability, String title, String author, LocalDate lendingDate) {
        this.itemCode = itemCode;
        this.availability = availability;
        this.title = title;
        this.author = author;
        this.lendingDate = lendingDate;
    }


    // getters and setters
    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
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

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }
}
