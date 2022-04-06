/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.controller;

import hr.algebra.model.Book;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author HT-ICT
 */
public class BookDetailsController implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label lblId,lblAuthor,lblTitle;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initData(Book book){
        lblId.setText(String.valueOf(book.getId()));
        lblTitle.setText(book.getTitle());
        lblAuthor.setText(book.getAuthor());
    }
}
