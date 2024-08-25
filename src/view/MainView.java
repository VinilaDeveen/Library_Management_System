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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainView {


    @FXML
    private Hyperlink hypCreateAcc;

    @FXML
    private AnchorPane root;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void CreateAccountOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/CreateMember.fxml");
        Parent root = FXMLLoader.load(resource);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Membership Form");
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        try {
            MemberController memberController = new MemberController();
            MemberDto member = memberController.login(username, password);

            if (member != null) {
                URL resource = this.getClass().getResource("/view/Book.fxml");
                Parent root = FXMLLoader.load(resource);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                stage.setTitle("Library Management System");

                Stage loginStage = (Stage) txtUserName.getScene().getWindow();
                loginStage.close();
            } else {
                showAlert("Login Failed", "Invalid username or password!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Something went wrong. Please try again.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}


