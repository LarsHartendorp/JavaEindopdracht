package inholland.nl.eindopdrachtjavafx.Controllers;

import inholland.nl.eindopdrachtjavafx.DAL.Database;
import inholland.nl.eindopdrachtjavafx.Models.Item;
import javafx.event.ActionEvent;
import inholland.nl.eindopdrachtjavafx.Models.Member;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class MemberController implements Initializable {

    private Member member;
    private Database database;
    @FXML private TableView<Member> tableViewMember;
    @FXML private TextField firstnameTextfield;
    @FXML private TextField lastnameTextfield;
    @FXML private TextField birthdateTextfield;
    @FXML private Label errorLabelMember;

    public MemberController(Member member, Database database) {
        this.member = member;
        this.database = database;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setMemberInTable(this.database.getAllMembers());
        this.tableViewMember.setOnMouseClicked((MouseEvent) -> {
            if (MouseEvent.getClickCount() == 1) {
                try {
                    Member member = tableViewMember.getSelectionModel().getSelectedItem();
                    if (member == null) {
                        return;
                    }
                    firstnameTextfield.setText(member.getFirstname());
                    lastnameTextfield.setText(member.getLastname());
                    birthdateTextfield.setText(member.getDateOfBirth().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void reloadTable() {
        setMemberInTable(this.database.getAllMembers());
        this.tableViewMember.refresh();
    }

    private void setMemberInTable(List<Member> members){
        tableViewMember.setItems(FXCollections.observableArrayList(members));
    }

    public void addMember(ActionEvent actionEvent) {
        try {
            String firstname = firstnameTextfield.getText();
            String lastname = lastnameTextfield.getText();
            if (firstname.isEmpty() || lastname.isEmpty() || birthdateTextfield.getText().isEmpty()) {
                throw new Exception("Please fill in all fields to add new member");
            }
            LocalDate birthdate = LocalDate.parse(birthdateTextfield.getText());
            Member member = new Member(firstname, lastname, birthdate);
            this.database.addMember(member);
            reloadTable();

        } catch (Exception e) {
            errorLabelMember.setText(e.getMessage());
        }
    }

    public void editMember(ActionEvent event) throws Exception {
        try {
            Member member = tableViewMember.getSelectionModel().getSelectedItem();
            if(member == null){
                throw new Exception("Please select a member to edit");
            }
            if (firstnameTextfield.getText().isEmpty() || lastnameTextfield.getText().isEmpty() || birthdateTextfield.getText().isEmpty()) {
                throw new Exception("Please fill in all fields to edit member");
            }
            String firstname = firstnameTextfield.getText();
            String lastname = lastnameTextfield.getText();
            LocalDate birthdate = LocalDate.parse(birthdateTextfield.getText());
            member.setFirstname(firstname);
            member.setLastname(lastname);
            member.setDateOfBirth(birthdate);
            this.database.editMember(member);
            reloadTable();
    } catch (Exception e) {
            errorLabelMember.setText(e.getMessage());
        }
    }

    public void deleteMember(ActionEvent actionEvent) {
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
