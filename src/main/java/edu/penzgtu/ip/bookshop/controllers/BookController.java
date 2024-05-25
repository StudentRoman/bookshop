package edu.penzgtu.ip.bookshop.controllers;

import edu.penzgtu.ip.api.BookApi;
import edu.penzgtu.ip.bookshop.services.BookService;
import edu.penzgtu.ip.model.BookDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController implements BookApi {
    @Autowired
    private BookService bookService;

    @Override
    public ResponseEntity<BookDTO> getBookByName(String bookName) {
        return ResponseEntity.ok(bookService.getBookByName(bookName));
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BookDTO> addBook(BookDTO newBook) {
        return ResponseEntity.ok(bookService.addBook(newBook));
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BookDTO> updateBook(BookDTO updatedBook) {
        return ResponseEntity.ok(bookService.updateBook(updatedBook));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBook(String bookId) {
        bookService.deleteBook(bookId);

        return null;
    }
}
