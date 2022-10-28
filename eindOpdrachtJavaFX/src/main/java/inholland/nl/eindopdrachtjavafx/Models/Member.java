package inholland.nl.eindopdrachtjavafx.Models;

import java.io.Serializable;
import java.time.LocalDate;

public class Member implements Serializable {
    //has memberID, firstname, lastname and date of birth
    private int memberID;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;

    // Constructor
    public Member(int memberID, String firstname, String lastname, LocalDate dateOfBirth) {
        this.memberID = memberID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
    }

    // constructor without memberID
    public Member(String firstname, String lastname, LocalDate dateOfBirth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
    }

    //getters and setters
    public int getMemberID() { return memberID;}
    public void setMemberID(int memberID) { this.memberID = memberID; }
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate setDateOfBirth(LocalDate dateOfBirth) {
        return this.dateOfBirth = dateOfBirth;
    }

}
