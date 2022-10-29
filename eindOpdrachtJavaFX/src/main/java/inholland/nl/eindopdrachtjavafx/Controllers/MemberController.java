package inholland.nl.eindopdrachtjavafx.Controllers;

import inholland.nl.eindopdrachtjavafx.DAL.Database;
import inholland.nl.eindopdrachtjavafx.Models.Member;
import inholland.nl.eindopdrachtjavafx.Models.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class MemberController implements Initializable {

    private final User user;
    private final Database database;
    @FXML private TableView<Member> tableViewMember;
    @FXML private TextField firstnameTextfield;
    @FXML private TextField lastnameTextfield;
    @FXML private DatePicker dateOfBirthPicker;
    @FXML private Label errorLabelMember;

    public MemberController(User user, Database database) {
        this.user = user;
        this.database = database;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setMemberInTable(this.database.getAllMembers());
        this.tableViewMember.setOnMouseClicked(MouseEvent -> {
            if (MouseEvent.getClickCount() == 1) {
                try {
                    Member member = tableViewMember.getSelectionModel().getSelectedItem();
                    if (member == null) {
                        return;
                    }
                    firstnameTextfield.setText(member.getFirstname());
                    lastnameTextfield.setText(member.getLastname());
                    dateOfBirthPicker.setValue(member.getDateOfBirth());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // herlaad de tabelView
    private void reloadTable() {
        setMemberInTable(this.database.getAllMembers());
        this.tableViewMember.refresh();
    }

    // set alle members in de tabelView
    private void setMemberInTable(List<Member> members){
        tableViewMember.setItems(FXCollections.observableArrayList(members));
    }

    // voeg een member toe aan de database
    public void addMember() {
        try {
            String firstname = firstnameTextfield.getText();
            String lastname = lastnameTextfield.getText();
            if (firstname.isEmpty() || lastname.isEmpty() || dateOfBirthPicker.getValue() == null) {
                throw new Exception("Please fill in all fields to add new member");
            }
            if (dateOfBirthPicker.getValue().isAfter(LocalDate.now())) {
                throw new Exception("Date of birth can't be in the future");
            }  // check if member is not too old, before 1920
            if (dateOfBirthPicker.getValue().isBefore(LocalDate.of(1920, 1, 1))) {
                throw new Exception("You are that old??? You can't even read anymore");
            } // days are not lower than 1 and not higher than 31
            else if (dateOfBirthPicker.getValue().getDayOfMonth() < 1 || dateOfBirthPicker.getValue().getDayOfMonth() > 31) {
                throw new Exception("Please enter a valid day");
            } // months are not lower than 1 and not higher than 12
            else if (dateOfBirthPicker.getValue().getMonthValue() < 1 || dateOfBirthPicker.getValue().getMonthValue() > 12) {
                throw new Exception("Please enter a valid month");
            } // years are not lower than 1920 and not higher than current year
            else if (dateOfBirthPicker.getValue().getYear() < 1920 || dateOfBirthPicker.getValue().getYear() > LocalDate.now().getYear()) {
                throw new Exception("Please enter a valid year");
            }
            // else is niet nodig omdat er een exception wordt gegooid als de velden niet zijn ingevuld. Flow control
            LocalDate birthdate = dateOfBirthPicker.getValue();
            Member member = new Member(firstname, lastname, birthdate);
            this.database.addMember(member);
            reloadTable();

        } catch (Exception e) {
            errorLabelMember.setText(e.getMessage());
        }
    }

    // bewerk een member in de database
    public void editMember() {
        try {
            Member member = tableViewMember.getSelectionModel().getSelectedItem();
            if(member == null){
                throw new Exception("Please select a member to edit");
            }
            if (firstnameTextfield.getText().isEmpty() || lastnameTextfield.getText().isEmpty() || dateOfBirthPicker.getValue() == null) {
                throw new Exception("Please fill in all fields to edit member");
            }
            String firstname = firstnameTextfield.getText();
            String lastname = lastnameTextfield.getText();
            LocalDate birthdate = dateOfBirthPicker.getValue();
            member.setFirstname(firstname);
            member.setLastname(lastname);
            member.setDateOfBirth(birthdate);
            this.database.editMember(member);
            reloadTable();
        } catch (Exception e) {
            errorLabelMember.setText(e.getMessage());
        }
    }

    // verwijder een member uit de database
    public void deleteMember() {
        try {
            Member member = tableViewMember.getSelectionModel().getSelectedItem();
            if (member == null) {
                throw new Exception("Please select a member to delete");
            }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to delete this item? " + member.getFirstname() + " " + member.getLastname());
        alert.showAndWait().ifPresent(response -> {
            if (response == alert.getButtonTypes().get(0)) {
                this.database.deleteMember(member);
                reloadTable();
            }
        });
        } catch (Exception e) {
            errorLabelMember.setText(e.getMessage());
        }
    }
}
