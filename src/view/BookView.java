package view;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import controller.BookController;
import dto.BookDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BookView {

    private BookController bookController;

    public BookView() throws Exception {
        bookController = new BookController();
    }

    @FXML
    private TableColumn<BookDto, String> colAuthor;

    @FXML
    private TableColumn<BookDto, String> colAvailability;

    @FXML
    private TableColumn<BookDto, String> colBookID;

    @FXML
    private TableColumn<BookDto, String> colBookName;

    @FXML
    private TableColumn<BookDto, String> colCategoryID;

    @FXML
    private TableColumn<BookDto, String> colIsbn;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BookDto> tblBooks;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookID;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtCategoryID;

    @FXML
    private TextField txtIsbn;

    @FXML
    void SearchBook(MouseEvent event) {
        searchBook();
    }

    @FXML
    void btnBookCategotyOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/BookCatagary.fxml");
        Parent root = FXMLLoader.load(resource);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Book Category");
    }

    @FXML
    void btnReturnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/Return.fxml");
        Parent root = FXMLLoader.load(resource);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Return Book");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        deleteBook();
    }

    @FXML
    void btnLoadAllCategoryOnAction(ActionEvent event) {
        try {
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        saveBook();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        updateBook();
    }

    @FXML
    void btnBorrowOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/Borrow.fxml");
        Parent root = FXMLLoader.load(resource);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Borrow Book");
    }

    @FXML
    private void initialize() throws Exception {
        colBookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colCategoryID.setCellValueFactory(new PropertyValueFactory<>("catagoryId"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));

        // Load the table with data on initialization
        loadTable();
    }

    private void saveBook() {
        try {
            BookDto dto = new BookDto(txtBookID.getText(), txtBookName.getText(), txtAuthor.getText(), txtIsbn.getText(), txtCategoryID.getText(), true);

            // Show confirmation dialog before saving
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save this book?", ButtonType.YES, ButtonType.NO);
            if (alert.showAndWait().get() == ButtonType.YES) {
                bookController.save(dto);
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Book saved successfully.");
                successAlert.showAndWait();
                clearForm();
                loadTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error while saving book").show();
        }
    }

    private void deleteBook() {
        try {
            String bookId = txtBookID.getText();
            // Show confirmation dialog before deleting
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this book?", ButtonType.YES, ButtonType.NO);
            if (alert.showAndWait().get() == ButtonType.YES) {
                bookController.delete(bookId);
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Book deleted successfully.");
                successAlert.showAndWait();
                clearForm();
                loadTable();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error while deleting book!");
            alert.show();
        }
    }

    private void updateBook() {
        try {
            BookDto dto = new BookDto(txtBookID.getText(), txtBookName.getText(), txtAuthor.getText(), txtIsbn.getText(), txtCategoryID.getText(), true);
            
            // Show confirmation dialog before updating
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to update this book?", ButtonType.YES, ButtonType.NO);
            if (alert.showAndWait().get() == ButtonType.YES) {
                bookController.update(dto);
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Book updated successfully.");
                successAlert.showAndWait();
                clearForm();
                loadTable();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error while updating book!");
            alert.show();
        }
    }
    

    private void loadTable() throws Exception {
        List<BookDto> books = bookController.getAll();
        if (books != null) {
            ObservableList<BookDto> bookDtos = FXCollections.observableArrayList(books);
            tblBooks.setItems(bookDtos);
        } else {
            tblBooks.setItems(FXCollections.observableArrayList());
        }
    }


    private void searchBook() {
        try {
            BookDto selectedBook = tblBooks.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                String bookId = selectedBook.getBookId();
                BookDto dto = bookController.get(bookId);

                if (dto != null) {
                    txtBookID.setText(dto.getBookId());
                    txtBookName.setText(dto.getTitle());
                    txtAuthor.setText(dto.getAuthor());
                    txtIsbn.setText(dto.getIsbn());
                    txtCategoryID.setText(dto.getCatagoryId());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Book not found");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a book to search");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error loading book!");
            alert.showAndWait();
        }
    }

    private void clearForm() {
        txtBookID.clear();
        txtBookName.clear();
        txtAuthor.clear();
        txtIsbn.clear();
        txtCategoryID.clear();
    }
}
