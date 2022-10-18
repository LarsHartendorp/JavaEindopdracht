package inholland.nl.eindopdrachtjavafx.DAL;

import inholland.nl.eindopdrachtjavafx.Models.Item;
import inholland.nl.eindopdrachtjavafx.Models.Member;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {
// create collection of users
    private List<Member> members;

    private List<Item> items;

    public Database() {
        this.members = new ArrayList<>();
        // add users to collection
        members.add(new Member(1, "admin", "admin", "1234"));
        members.add(new Member(2,"user", "user", "1234"));
        members.add(new Member(3, "test", "test", "1234"));
        members.add(new Member(4, "Lars", "Lars Hartendorp", "1234"));
        members.add(new Member(5, "Jeroen", "Jeroen van der Heijden", "1234"));

        this.items = new ArrayList<>();
        // add items to collection
        items.add(new Item(1, true, "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Available", LocalDate.of(1997, 6, 26)));
        items.add(new Item(2, true, "Harry Potter and the Chamber of Secrets", "J.K. Rowling", "Available", LocalDate.of(2021, 1, 1)));
        items.add(new Item(3, true, "Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", "Available", LocalDate.of(2021, 1, 1)));
            items.add(new Item(4, true, "Lord of the Rings", "J.R.R. Tolkien", "Available", LocalDate.of(2021, 1, 1)));
        items.add(new Item(5, true, "Rich Dad Poor Dad", "Robert T. Kiyosaki", "Available", LocalDate.of(2021, 1, 1)));
    }

    // return collection of users
    public List<Member> getUsers() {
        return members;
    }


    // make method to check if itemcode and member are in database
    public boolean checkItemCodeAndMember(int itemCode, int memberID) {
        // check if itemcode is in database
        for (Item item : items) {
            if (item.getItemCode() == itemCode) {
                // check if member is in database
                for (Member existingMember : members) {
                    if (existingMember.getMemberID() == memberID) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    // make method to check if itemcode is in database
    public boolean checkItemCode(int itemCode) {
        // check if itemcode is in database
        for (Item item : items) {
            if (item.getItemCode() == itemCode) {
                return true;
            }
        }
        return false;
    }

    // make method to calculate overdue days
    public int calculateOverdueDays(int itemCode) {
        // get lending date
        LocalDate lendingDate = null;
        for (Item item : items) {
            if (item.getItemCode() == itemCode) {
                lendingDate = item.getLendingDate();
                System.out.println(item.getLendingDate());
            }
        }
        // calculate overdue days
        LocalDate today = LocalDate.now();
        int overdueDays = today.getDayOfYear() - lendingDate.getDayOfYear();
        return overdueDays;
    }
    public void lentItem (int itemCode, int memberID) {
        if (checkItemCodeAndMember(itemCode, memberID)) {
            for (Item item : items) {
                if (item.getItemCode() == itemCode) {
                    item.setAvailability(false);
                    item.setLendingDate(LocalDate.now());
                    return;
                }
            }
        }
    }
    public boolean receivedItem(int itemcode) {
        if (checkItemCode(itemcode)) {
            for (Item item : items) {
                if (item.getItemCode() == itemcode) {
                    item.setAvailability(true);
/*                    item.setLendingDate(null);*/
                    return true;
                }
            }
        }
        return false;
    }

    //check if item is already lent
    public boolean checkIfItemIsAlreadyLent(int itemCode, int memberID) {
        for (Item item : items) {
            if (item.getItemCode() == itemCode) {
                if (!item.getAvailability()) {
                    return true;
                }
            }
        }
        return false;
    }

    // check if item is already received
    public boolean checkIfItemIsAlreadyReceived(int itemCode) {
        for (Item item : items) {
            if (item.getItemCode() == itemCode) {
                if (item.getAvailability()) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Item> getAllItems() {
        return items;
    }


    public Item getItem(int itemCode) {
        for (Item item : items) {
            if (item.getItemCode() == itemCode) {
                return item;
            }
        }
        return null;
    }
}
