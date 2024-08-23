package view;

import controller.MemberController;
import dto.MemberDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class UpdateMemberView {
    private MemberController memberController;

    public UpdateMemberView() {
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
    void DeleteMemberOnAction(ActionEvent event) throws Exception{
        deleteMember();
    }

    @FXML
    void SearchMemberOnAction(ActionEvent event) throws Exception{
        searchMember();
    }

    @FXML
    void UpdateMemberOnAction(ActionEvent event) throws Exception {
        updateMember();
    }

    private void updateMember() throws Exception {
        try {
            MemberDto dto = new MemberDto(txtMemId.getText(), txtMemName.getText(), txtPhone.getText(), txtUserName.getText(), txtPassword.getText());
            String resp = memberController.update(dto);
            new Alert(Alert.AlertType.CONFIRMATION, "Success");
            clearform();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error to update");
        }
    }

    private void deleteMember() {
        try {
            String memID = txtMemId.getText();
            String resp = memberController.delete(memID);
            new Alert(Alert.AlertType.CONFIRMATION, "Do you want delete?");
            clearform();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error at delete member");
        }
    }

    private void clearform(){
        txtMemId.setText("");
        txtMemName.setText("");
        txtPhone.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
    }

    private void searchMember() {
        try {
            String memID = txtMemId.getText();
            MemberDto dto = memberController.get(memID);

            if (dto != null) {
                txtMemName.setText(dto.getName());
                txtPhone.setText(dto.getPhone());
                txtUserName.setText(dto.getUserName());
                txtPassword.setText(dto.getPassword());
            } else {
                new Alert(Alert.AlertType.ERROR, "Member not found");
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error at lead mamber details");
        }
    }

}
