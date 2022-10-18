package inholland.nl.eindopdrachtjavafx.Controllers;

import inholland.nl.eindopdrachtjavafx.DAL.Database;
import javafx.event.ActionEvent;
import inholland.nl.eindopdrachtjavafx.Models.Member;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.List;

public class MemberController {

    private Member member;
    private Database database;
    @FXML
    private TableView<Member> tableViewMember;

    public MemberController(Member member, Database database) {
        this.member = member;
        this.database = database;
    }
    @FXML public void initialize() {
        setMemberInTable(this.database.getAllMembers());
    }

    private void setMemberInTable(List<Member> members){
        tableViewMember.setItems(FXCollections.observableArrayList(members));
    }

    public void addMember(ActionEvent actionEvent) {
    }

    public void editMember(ActionEvent actionEvent) {
    }

    public void deleteMember(ActionEvent actionEvent) {
    }
}
