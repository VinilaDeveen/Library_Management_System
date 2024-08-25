package view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controller.BookController;
import controller.BorrowController;
import controller.MemberController;
import dto.BookDto;
import dto.BorrowDetailDto;
import dto.BorrowDto;
import dto.MemberDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class BorrowView {
    private BookController bookController;
    private MemberController memberController;
    private ArrayList<BorrowDetailDto> borrowDetailDtos;
    private BorrowController borrowController;

    public BorrowView() {
        bookController = new BookController();
        memberController = new MemberController();
        borrowDetailDtos = new ArrayList<>();
        borrowController = new BorrowController(); // Initialize borrowController
    }

    @FXML
    private TableColumn<BorrowDetailDto, String> colBookId;

    @FXML
    private TableColumn<BorrowDetailDto, String> colDueDate;

    @FXML
    private DatePicker dpDueDate;

    @FXML
    private Label lblBookDetail;

    @FXML
    private Label lblMemberDetail;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BorrowDetailDto> tblBorrowDetail;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBorrowId;

    @FXML
    private TextField txtMemId;

    @FXML
    void AddBorrowOnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    void SearchBookOnAction(ActionEvent event) {
        searchBook();
    }

    @FXML
    void SearchMemberOnAction(ActionEvent event) {
        searchMember();
    }

    @FXML
    void btnBorrowOnAction(ActionEvent event) {
        placeBorrow();
    }

    @FXML
    private void initialize() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        
        // Load the table with data on initialization
        
    }

    private void searchBook() {
        try {
            String bookId = txtBookId.getText();
            BookDto bookDto = bookController.get(bookId);
            if (bookDto != null) {
                lblBookDetail.setText("Book Title: " + bookDto.getTitle() + " | Availability: " + bookDto.isAvailability());
            } else {
                lblBookDetail.setText("Book not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error at search Book!");
            alert.show();
        }
    }

    private void searchMember() {
        try {
            String memId = txtMemId.getText();
            MemberDto memberDto = memberController.get(memId);
            if (memberDto != null) {
                lblMemberDetail.setText("Name: " + memberDto.getName() + " | Phone No: " + memberDto.getPhone());
            } else {
                lblMemberDetail.setText("Member not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error at search Member!");
            alert.show();
        }
    }

    private void loadTable() {
        String dueDate = dpDueDate.getValue() != null ? dpDueDate.getValue().toString() : "";
        BorrowDetailDto borrowDetailDto = new BorrowDetailDto(
            txtBorrowId.getText(),
            txtBookId.getText(),
            dueDate
        );
        borrowDetailDtos.add(borrowDetailDto);

        ObservableList<BorrowDetailDto> observableBorrowDetailDtos = FXCollections.observableArrayList(borrowDetailDtos);
        tblBorrowDetail.setItems(observableBorrowDetailDtos);
    }

    private void placeBorrow() {
        try {
            BorrowDto borrowDto = new BorrowDto();
            borrowDto.setBorrowId(txtBorrowId.getText());
            borrowDto.setDueDate(dpDueDate.getValue() != null ? dpDueDate.getValue().toString() : "");
            borrowDto.setMemId(txtMemId.getText());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(new Date());
            borrowDto.setBorrowDate(date);

            borrowDto.setBorrowDetailDtos(borrowDetailDtos);
            borrowController.placeBorrow(borrowDto);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to borrow ?");
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error at save borrow!");
            alert.show();
        }
    }
}

