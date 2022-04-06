/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.controller;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import hr.algebra.model.Book;
import hr.algebra.repository.BooksRepository;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HT-ICT
 */
public class BooksController implements Initializable {

    @FXML
    private Pane pnlActions;
    @FXML
    private TextField tfFilter;
    @FXML
    private TableView<Book> tvBooks;
    @FXML
    private TableColumn<Book, String> tcId, tcTitle, tcAuthor;

    private ObservableList<Book> books;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initObservable();
        initTableCells();
        showBooks();
    }

    @FXML
    private void filterBooks(KeyEvent event) {
        showBooks();
    }

    @FXML
    private void deleteBook(ActionEvent event) {
        books.remove(tvBooks.getSelectionModel().getSelectedItem());
    }

    private void initObservable() {
        books = FXCollections.observableArrayList(BooksRepository.getBooks());
    }

    private void initTableCells() {
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));

        tcAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        tcAuthor.setCellFactory(TextFieldTableCell.forTableColumn());
        tcAuthor.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                Book book = tvBooks.getSelectionModel().getSelectedItem();
                book.setAuthor(event.getNewValue());
                showBooks();
            }
        });

        tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcTitle.setCellFactory(TextFieldTableCell.forTableColumn());
        tcTitle.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                Book book = tvBooks.getSelectionModel().getSelectedItem();
                book.setTitle(event.getNewValue());
                showBooks();
            }
        });
    }

    private void showBooks() {
        FilteredList<Book> filtered = books.filtered(book->book.getTitle().toLowerCase().startsWith(tfFilter.getText().toLowerCase()));
        SortedList<Book> sorted = filtered.sorted();
        tvBooks.setItems(sorted);
        sorted.comparatorProperty().bind(tvBooks.comparatorProperty());
    }

    @FXML
    private void showBookDetails(MouseEvent event) {
        if (event.getClickCount()!=2 || event.getTarget().getClass().equals(TableHeaderRow.class)) {
            return;
        }
        Book book=tvBooks.getSelectionModel().getSelectedItem();
        try {
            redirecrtToDetails(book);
        } catch (IOException ex) {
            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void redirecrtToDetails(Book book) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/hr/algebra/view/BookDetails.fxml"));
        Parent parent=loader.load();
        BookDetailsController bdc=loader.getController();
        
        Stage stage=new Stage();
        stage.setTitle("Book detail");
        stage.setScene(new Scene(parent));
        bdc.initData(book);
        
        stage.show();
    }

    @FXML
    private void ExitApp(ActionEvent event) {
        Platform.exit();
    }

}