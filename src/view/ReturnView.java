package view;

import controller.BorrowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.custom.BorrowService;
import service.custom.impl.BorrowServiceImpl;

public class ReturnView {
    private BorrowController borrowController = new BorrowController();
    private BorrowService borrowService = new BorrowServiceImpl();

    @FXML
    private DatePicker dpReturnDate;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtBorrowId;

    @FXML
    private Label lblFine;

    @FXML
    void btnProcessReturnOnAction(ActionEvent event) {
        processReturn();
    }

    private void processReturn() {
        try {
            String borrowId = txtBorrowId.getText();
            String returnDate = dpReturnDate.getValue().toString();
            String result = borrowController.processReturn(borrowId, returnDate);

            if (result.equals("Return Processed Successfully!")) {
                double fines = borrowService.getFines(borrowId);
                lblFine.setText("Fine: LKR. " + fines);
            } else {
                lblFine.setText(result);
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, result);
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error processing return!");
            alert.show();
        }
    }



}

