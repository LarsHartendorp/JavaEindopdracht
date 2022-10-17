package inholland.nl.eindopdrachtjavafx.Models;

public class Member {
    //has username and password
    private int memberID;
    private String username;
    private String fullname;
    private String password;
    public Member(int memberID, String username, String fullname ,String password) {
        this.memberID = memberID;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
    }

    //getters
    public int getMemberID() { return memberID; }
    public String getMembername() {
        return username;
    }
    public String getFullname() {
        return fullname;
    }
    public String getPassword() {
        return password;
    }

}
