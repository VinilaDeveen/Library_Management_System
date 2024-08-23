package view;

import java.io.IOException;
import java.net.URL;

import controller.MemberController;
import dto.MemberDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateMemberView {
    private MemberController memberController;

    public CreateMemberView() {
        memberController = new MemberController();
    }

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtMemId;

    @FXML
    private TextField txtMemName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtUserName;

    @FXML
    void SaveMemberOnAction(ActionEvent event) {
        saveMember();
    }

    private void saveMember() {
        try {
            MemberDto dto = new MemberDto(txtMemId.getText(), txtMemName.getText(), txtPhone.getText(), txtUserName.getText(), txtPassword.getText());
            String resp;
            resp = memberController.save(dto);
            clearform();
            new Alert(Alert.AlertType.CONFIRMATION, "Do you want sava this member?");

        } catch (Exception e) {
           new Alert(Alert.AlertType.ERROR, "Error to save");
        }
    }

    private void clearform(){
        txtMemId.setText("");
        txtMemName.setText("");
        txtPhone.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
    }

}

