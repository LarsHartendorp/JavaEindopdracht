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

    //getters
    public int getMemberID() {
        return memberID;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() { return username; }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPassword() {
        return password;
    }


}
