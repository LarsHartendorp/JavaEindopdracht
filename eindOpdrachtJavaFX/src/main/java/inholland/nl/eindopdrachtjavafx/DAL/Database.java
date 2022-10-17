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
        items.add(new Item(1, true, "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Available", LocalDate.now()));
        items.add(new Item(2, true, "Harry Potter and the Chamber of Secrets", "J.K. Rowling", "Available", LocalDate.of(2021, 1, 1)));
        items.add(new Item(3, true, "Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", "Available", null));
        items.add(new Item(4, true, "Lord of the Rings", "J.R.R. Tolkien", "Available", null));
        items.add(new Item(5, true, "Rich Dad Poor Dad", "Robert T. Kiyosaki", "Available", null));
    }

    // return collection of users
    public List<Member> getUsers() {
        return members;
    }


    // make method to check if itemcode and member are in database
    public boolean checkItemCodeAndMember(int itemCode, String member) {
        // check if itemcode is in database
        for (Item item : items) {
            if (item.getItemCode() == itemCode) {
                // check if member is in database
                for (Member existingMember : members) {
                    if (existingMember.getMembername().equals(member)) {
                        return true;
                    }
                }
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
            }
        }
        // calculate overdue days
        LocalDate today = LocalDate.now();
        int overdueDays = today.getDayOfYear() - lendingDate.getDayOfYear();
        return overdueDays;
    }
    public void lentItem (int itemCode, String membername) {
        if (checkItemCodeAndMember(itemCode, membername)) {
            for (Item item : items) {
                if (item.getItemCode() == itemCode) {
                    item.setAvailability(false);
                    item.setLendingDate(LocalDate.now());
                    return;
                }
            }
        }
    }
    public void receivedItem(int itemcode){
        if (checkItemCodeAndMember(itemcode, null)) {
            for (Item item : items) {
                if (item.getItemCode() == itemcode) {
                    item.setAvailability(true);
                    item.setLendingDate(null);
                    return;
                }
            }
        }
    }

    public ObservableList getAllItems() {
        return (ObservableList) items;
    }
}
