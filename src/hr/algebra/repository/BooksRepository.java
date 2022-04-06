/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.repository;

import hr.algebra.model.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author HT-ICT
 */
public class BooksRepository {
    	private BooksRepository() {
    }
	
    private static final List<Book> BOOKS = Arrays.asList(
            new Book(1, "Čovjek bez sudbine", "Imre Kertesz"),
            new Book(2, "Svila", "Alessandro Baricco"),
            new Book(3, "Muke po influenserima", "Bibi Plastika")
    );
    
    public static List<Book> getBooks() {
        return new ArrayList(BOOKS);
    }
}
