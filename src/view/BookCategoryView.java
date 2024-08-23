package view;

import controller.BookCatagoryController;
import dto.BookCatagoryDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class BookCategoryView {

    private BookCatagoryController bookCatagoryController;

    public BookCategoryView() {
        bookCatagoryController = new BookCatagoryController();
    }

    @FXML
    private AnchorPane root;

    @FXML
    private TableColumn<BookCatagoryDto, String> colCategoryID;

    @FXML
    private TableColumn<BookCatagoryDto, String> colCategoryName;

    @FXML
    private TableView<BookCatagoryDto> tblCatogories;

    @FXML
    private TextField txtCategoryID;

    @FXML
    private TextField txtCategoryName;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        deleteCatagory();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        saveCategory();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        updateCatagory();
    }

    @FXML
    void btnLoadAllCategoryOnAction(ActionEvent event) throws Exception {
        loadTable();
    }

    @FXML
    private void initialize() throws Exception {
        colCategoryID.setCellValueFactory(new PropertyValueFactory<>("catagoryId"));
        colCategoryName.setCellValueFactory(new PropertyValueFactory<>("catagoryName"));
        
        // Load the table with data on initialization
        loadTable();
    }

    @FXML
    void searchCatagory(MouseEvent event) {
        searchCategory();
    }

    private void saveCategory() {
        try {
            BookCatagoryDto dto = new BookCatagoryDto(txtCategoryID.getText(), txtCategoryName.getText());
            String resp = bookCatagoryController.save(dto);

            // Display confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Category saved successfully.");
            alert.showAndWait();

            clearForm();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error while saving category").show();
        }
    }

    private void deleteCatagory() {
        try {
            String catagoryId = txtCategoryID.getText();
            bookCatagoryController.delete(catagoryId);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete?");
            alert.show();
            clearForm();
            loadTable();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error at delete Catagory!");
            alert.show();
        }
    }

    private void updateCatagory() {
        try {
            BookCatagoryDto dto = new BookCatagoryDto(txtCategoryID.getText(), txtCategoryName.getText());
            bookCatagoryController.update(dto);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to update?");
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error at update Catagory!");
            alert.show();
        }
    }

    private void loadTable() throws Exception {
        ObservableList<BookCatagoryDto> bookCatagoryDtos = FXCollections.observableArrayList(bookCatagoryController.getAll());
        tblCatogories.setItems(bookCatagoryDtos);
    }

    private void searchCategory() {
        try {
            BookCatagoryDto selectedCategory = tblCatogories.getSelectionModel().getSelectedItem();
            if (selectedCategory != null) {
                String catagoryId = selectedCategory.getCatagoryId();
                BookCatagoryDto dto = bookCatagoryController.get(catagoryId);

                if (dto != null) {
                    txtCategoryID.setText(dto.getCatagoryId());
                    txtCategoryName.setText(dto.getCatagoryName());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Category not found");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error loading category.");
            alert.showAndWait();
        }
    }

    private void clearForm() {
        txtCategoryID.clear();
        txtCategoryName.clear();
    }
}
