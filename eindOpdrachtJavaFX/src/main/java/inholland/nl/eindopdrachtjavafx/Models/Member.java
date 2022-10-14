package inholland.nl.eindopdrachtjavafx.Models;

public class Member {
    //has username and password
    private String username;
    private String fullname;
    private String password;
    public Member(String username, String fullname ,String password) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
    }
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
