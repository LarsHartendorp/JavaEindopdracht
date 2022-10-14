package inholland.nl.eindopdrachtjavafx.DAL;

import inholland.nl.eindopdrachtjavafx.Models.Item;
import inholland.nl.eindopdrachtjavafx.Models.Member;

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
        members.add(new Member("admin", "admin", "1234"));
        members.add(new Member("user", "user", "1234"));
        members.add(new Member("test", "test", "1234"));
        members.add(new Member("Lars", "Lars Hartendorp", "1234"));
        members.add(new Member("Jeroen", "Jeroen van der Heijden", "1234"));

        this.items = new ArrayList<>();
        // add items to collection
        items.add(new Item("1", true, "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Available", null));
        items.add(new Item("2", true, "Harry Potter and the Chamber of Secrets", "J.K. Rowling", "Available", null));
        items.add(new Item("3", true, "Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", "Available", null));
        items.add(new Item("4", true, "Lord of the Rings", "J.R.R. Tolkien", "Available", null));
        items.add(new Item("5", true, "Rich Dad Poor Dad", "Robert T. Kiyosaki", "Available", null));
    }

    // return collection of users
    public List<Member> getUsers() {
        return members;
    }

    // methode maken die item returned op basis van ID
    public void lentItem (String itemCode) {
        for (Item item : items) {
            if (item.getItemCode().equals(itemCode)) {
                item.setAvailability(false);
                item.setLendingDate(LocalDate.now());
                System.out.println("Item " + item.getAvailability() + " is uitgeleend");
                return;
            }
        }
    }
}
