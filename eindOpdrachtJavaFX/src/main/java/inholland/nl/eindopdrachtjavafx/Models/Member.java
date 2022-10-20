package inholland.nl.eindopdrachtjavafx.Models;

import java.time.LocalDate;

public class Member {
    //has username and password
    private int memberID;
    private String username;
    private String firstname;
    private String lastname;
    private String fullname;
    private String password;
    private LocalDate dateOfBirth;
    public Member(int memberID, String username ,String firstname, String lastname, String fullname, LocalDate dateOfBirth ,String password) {
        this.memberID = memberID;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    public Member(String firstname, String lastname, LocalDate dateOfBirth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
    }

    //getters and setters
    public int getMemberID() {
        return memberID;
    }

    public int setMemberID(int memberID) {
        return this.memberID = memberID;
    }

    public String getFirstname() {
        return firstname;
    }

    public String setFirstname(String firstname) {
        return this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String setLastname(String lastname) {
        return this.lastname = lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public String setFullname(String fullname) {
        return this.fullname = fullname;
    }

    public String getUsername() { return username; }

    public String setUsername(String username) { return this.username = username; }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate setDateOfBirth(LocalDate dateOfBirth) {
        return this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        return this.password = password;
    }


}
