package view;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    void btnLoginOnAction(ActionEvent event) {

    }

}


