package inholland.nl.eindopdrachtjavafx.DAL;

import inholland.nl.eindopdrachtjavafx.Models.Item;
import inholland.nl.eindopdrachtjavafx.Models.Member;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database  {
    private List<Member> members;

    private List<Item> items;

    public Database() {
        this.members = new ArrayList<>();
        this.items = new ArrayList<>();
        try {
            loadDataForMembers();
            loadDataForItems();
        } catch (IOException e) {
            // add users to collection
            this.members.add(new Member(1, "Lars","Lars", "H", "Lars H", LocalDate.of(1990, 1, 1), "1234"));
            this.members.add(new Member(2, "Test","Jane", "Doe", "Jane Doe", LocalDate.of(1994, 6, 15), "0000"));
            this.members.add(new Member(3, "John","John", "Smith", "John Smith", LocalDate.of(1992, 3, 12), "1234"));
            this.members.add(new Member(4, "Jane2","Jane", "Smith", "Jane Smith", LocalDate.of(1995, 8, 23), "5678"));
            this.members.add(new Member(5, "John2","John", "Doe", "John Doe", LocalDate.of(1990, 1, 1), "2378"));

            // add items to collection
            this.items.add(new Item(1, true, "Harry Potter and the Philosopher's Stone", "J.K. Rowling", LocalDate.of(1997, 6, 26)));
            this.items.add(new Item(2, true, "Harry Potter and the Chamber of Secrets", "J.K. Rowling", LocalDate.of(1998, 7, 2)));
            this.items.add(new Item(3, true, "Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", LocalDate.of(1999, 7, 8)));
            this.items.add(new Item(4, true, "Harry Potter and the Goblet of Fire", "J.K. Rowling", LocalDate.of(2000, 7, 8)));
            this.items.add(new Item(5, true, "Harry Potter and the Order of the Phoenix", "J.K. Rowling", LocalDate.of(2003, 6, 21)));
            this.items.add(new Item(6, true, "Harry Potter and the Half-Blood Prince", "J.K. Rowling", LocalDate.of(2005, 7, 16)));
            this.items.add(new Item(7, true, "Harry Potter and the Deathly Hallows", "J.K. Rowling", LocalDate.of(2007, 7, 21)));
        }

    }

    // return collection of users
    public List<Member> getMember() {
        return members;
    }

    public List<Item> getAllItems() {
        return items;
    }

    public List<Member> getAllMembers(){
        return members;
    }

    public Item getItem(int itemCode) {
        for (Item item : items) {
            if (item.getItemCode() == itemCode) {
                return item;
            }
        }
        return null;
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
            }
        }
        // calculate overdue days
        LocalDate today = LocalDate.now();
        return today.getDayOfYear() - lendingDate.getDayOfYear();
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
    public boolean receivedItem(int itemCode) {
        if (checkItemCode(itemCode)) {
            for (Item item : items) {
                if (item.getItemCode() == itemCode) {
                    item.setAvailability(true);
/*                    item.setLendingDate(null);*/ // dit moet nog worden aangepast, als dat mogelijk is. Null veroorzaakt error.
                    return true;
                }
            }
        }
        return false;
    }

    //check if item is already lent
    public boolean checkIfItemIsAlreadyLent(int itemCode) {
        for (Item item : items) {
            if (item.getItemCode() == itemCode && !item.getAvailability()) {
                return true;
            }
        }
        return false;
    }

    // check if item is already received
    public boolean checkIfItemIsAlreadyReceived(int itemCode) {
        for (Item item : items) {
            if (item.getItemCode() == itemCode && item.getAvailability()) {
                return true;
            }
        }
        return false;
    }

    // add new item to database
    public void addItem(Item item) {
        item.setItemCode(this.generateItemCode());
        items.add(item);
    }
    public void addMember(Member member) {
        member.setMemberID(this.generateMemberID());
        members.add(member);
    }

    public void editItem(Item item) {
        for (Item existingItem : items) {
            if (existingItem.getItemCode() == item.getItemCode()) {
                existingItem.setTitle(item.getTitle());
                existingItem.setAuthor(item.getAuthor());
            }
        }
    }

    public void editMember(Member member) {
        for (Member existingMember : members) {
            if (existingMember.getMemberID() == member.getMemberID()) {
                existingMember.setFirstname(member.getFirstname());
                existingMember.setLastname(member.getLastname());
                existingMember.setDateOfBirth(member.getDateOfBirth());
            }
        }
    }

    // delete item from database
    public void deleteItem(Item item) {
        for (Item existingItem : items) {
            if (existingItem.getItemCode() == item.getItemCode()) {
                items.remove(existingItem);
                return;
            }
        }
    }

    public void deleteMember(Member member) {
        for (Member existingMember : members) {
            if (existingMember.getMemberID() == member.getMemberID()) {
                members.remove(existingMember);
                return;
            }
        }
    }

    // auto generated itemcode
    public int generateItemCode() {
        int itemCode = 0;
        for (Item item : items) {
            if (item.getItemCode() > itemCode) {
                itemCode = item.getItemCode();
            }
        }
        return itemCode + 1;
        //return items.size() + 1;
    }

    public int generateMemberID() {
        int memberID = 0;
        for (Member member : members) {
            if (member.getMemberID() > memberID) {
                memberID = member.getMemberID();
            }
        }
        return memberID + 1;
    }

   // save data for items
    private void saveDataForItems() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("items.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            // write loop for all items
            for (Item item : items) {
                objectOutputStream.writeObject(item);
            }
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // save data for members and serialize
    private void saveDataForMembers() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("members.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            // write loop for all members
            for (Member member : members) {
                objectOutputStream.writeObject(member);
            }
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        saveDataForItems();
        saveDataForMembers();
    }

    // load data for items
    public void loadDataForItems() throws IOException{
        try {
            FileInputStream fileInputStream = new FileInputStream("items.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            // read loop for all items
            while (true) {
                try {
                    Item item = (Item) objectInputStream.readObject();
                    items.add(item);
                } catch (EOFException e) {
                    break;
                }
            }
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // load data for members
    public void loadDataForMembers() throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream("members.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            // read loop for all members
            while (true) {
                try {
                    Member member = (Member) objectInputStream.readObject();
                    members.add(member);
                } catch (EOFException e) {
                    break;
                }
            }
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
